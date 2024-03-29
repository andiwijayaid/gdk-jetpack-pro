package andi.gdk.jetpackpro.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

open class AppExecutors @VisibleForTesting constructor(
    private val diskIO: Executor = DiskIOThreadExecutor(),
    private val networkIO: Executor = Executors.newFixedThreadPool(THREAD_COUNT),
    private val mainThread: Executor = MainThreadExecutor()
) {

    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(
            Looper.getMainLooper()
        )

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    companion object {
        private const val THREAD_COUNT = 3
    }

}