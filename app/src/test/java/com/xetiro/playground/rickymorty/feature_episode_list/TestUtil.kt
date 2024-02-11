package com.xetiro.playground.rickymorty.feature_episode_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * Utility for facilitating test setup.
 *
 * Created by xetiro (aka Ruben Geraldes) on 2024/02/10.
 */

// Reusable JUnit4 TestRule to override the Main dispatcher
@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}

/**
 * This function observes a LiveData until it receives a new value (via onChanged) and then it
 * removes the observer. If the LiveData already has a value, it returns it immediately.
 *
 * Additionally, if the value is never set, it will throw an exception after 2 seconds
 * (or whatever you set). This prevents tests that never finish when something goes wrong.
 *
 * Created by xetiro (aka Ruben Geraldes) on 2024/02/10.
 */
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}

/**
 * Observes a LiveData until the `block` is done executing.
 *
 */
suspend fun <T> LiveData<T>.observeForTesting(
    skipInitialState: Boolean = true,
    stateList: MutableList<T>,
    block: suspend () -> Unit
) {
    var numberOfUpdatesToSkip = if(skipInitialState) 1 else 0
    val observer = Observer<T> {
        if (numberOfUpdatesToSkip == 0)
            stateList.add(it)
        else
            numberOfUpdatesToSkip--
    }
    try {
        observeForever(observer)
        block()
    } finally {
        removeObserver(observer)
    }
}