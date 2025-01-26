/*
 * Copyright 2025 Oleksii Shtanko
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

package dev.shtanko.benchmark.executor

/**
 * A data object that implements the `TaskExecutor` interface by directly invoking the provided action on each item
 * in the list sequentially.
 *
 * This implementation does not use any concurrency mechanisms and directly executes the action on each item in the
 * list in a sequential manner. It is suitable for scenarios where tasks are simple, quick, and do not require parallel
 * execution or special scheduling.
 */
data object DirectInvoke : TaskExecutor {

    /**
     * Directly executes the provided action on each item in the list.
     *
     * This implementation uses a simple `forEach` loop to invoke the action for each item in the provided list. The
     * method is executed sequentially, meaning that each task is completed before moving on to the next one. This is
     * appropriate when tasks do not require concurrency or parallel execution.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        // Directly execute the action on each item in the list
        data.forEach { action(it) }
    }
}
