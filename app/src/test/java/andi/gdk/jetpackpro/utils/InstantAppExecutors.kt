package andi.gdk.jetpackpro.utils

import java.util.concurrent.Executor

open class InstantAppExecutors : AppExecutors() {
    private val instant =
        Executor { obj: Runnable -> obj.run() }
}