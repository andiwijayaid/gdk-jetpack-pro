package andi.gdk.jetpackpro.data.source

import andi.gdk.jetpackpro.data.source.remote.ApiResponse
import andi.gdk.jetpackpro.data.source.remote.StatusResponse
import andi.gdk.jetpackpro.utils.AppExecutors
import andi.gdk.jetpackpro.vo.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()
    private fun onFetchFailed() {}
    protected abstract fun loadFromDB(): LiveData<ResultType>
    protected abstract fun shouldFetch(data: ResultType?): Boolean
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>?
    protected abstract fun saveCallResult(data: RequestType?)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(
            dbSource
        ) { newData: ResultType -> result.setValue(Resource.loading(newData)) }
        apiResponse?.let {
            result.addSource(it) { response: ApiResponse<RequestType> ->
                result.removeSource(apiResponse)
                result.removeSource(dbSource)
                when (response.status) {
                    StatusResponse.SUCCESS -> mExecutors.diskIO().execute {
                        saveCallResult(response.body)
                        mExecutors.mainThread().execute {
                            result.addSource(
                                loadFromDB()
                            ) { newData: ResultType -> result.setValue(Resource.success(newData)) }
                        }
                    }
                    StatusResponse.EMPTY -> mExecutors.mainThread().execute {
                        result.addSource(
                            loadFromDB()
                        ) { newData: ResultType -> result.setValue(Resource.success(newData)) }
                    }
                    StatusResponse.ERROR -> {
                        onFetchFailed()
                        result.addSource(dbSource) { newData: ResultType ->
                            result.setValue(
                                Resource.error(
                                    response.message,
                                    newData
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

    init {
        result.setValue(Resource.loading(null))
        val dbSource = loadFromDB()
        result.addSource(dbSource) { data: ResultType ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData: ResultType ->
                    result.setValue(
                        Resource.success(
                            newData
                        )
                    )
                }
            }
        }
    }
}