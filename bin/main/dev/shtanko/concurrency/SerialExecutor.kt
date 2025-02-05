/*
 * Copyright 2024 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.concurrency

import java.util.ArrayDeque
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * A SerialExecutor ensures that tasks are executed sequentially, one after another.
 * It wraps another Executor and schedules tasks to run in a serial order.
 */
class SerialExecutor : Executor {
    // Queue to hold the tasks to be executed
    private val mTasks = ArrayDeque<Runnable>()

    // Currently active task
    private var mActive: Runnable? = null

    /**
     * Adds a task to the queue and schedules it for execution if no other task is active.
     *
     * @param r The task to be executed.
     */
    @Synchronized
    override fun execute(r: Runnable) {
        mTasks.offer {
            try {
                r.run()
            } finally {
                scheduleNext()
            }
        }
        if (mActive == null) {
            scheduleNext()
        }
    }

    /**
     * Schedules the next task in the queue for execution.
     */
    @Synchronized
    private fun scheduleNext() {
        mActive = mTasks.poll()
        mActive?.let { THREAD_POOL_EXECUTOR.execute(it) }
    }

    companion object {
        // Thread pool executor to run the tasks
        private val THREAD_POOL_EXECUTOR = Executors.newCachedThreadPool()
    }
}
