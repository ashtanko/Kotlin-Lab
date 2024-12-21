/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.api.contributors

import dev.shtanko.api.RequestData
import dev.shtanko.api.User
import dev.shtanko.api.contributors.Contributors.LoadingStatus.CANCELED
import dev.shtanko.api.contributors.Contributors.LoadingStatus.COMPLETED
import dev.shtanko.api.contributors.Contributors.LoadingStatus.IN_PROGRESS
import dev.shtanko.api.contributors.Variant.BACKGROUND
import dev.shtanko.api.contributors.Variant.BLOCKING
import dev.shtanko.api.contributors.Variant.CALLBACKS
import dev.shtanko.api.contributors.Variant.CHANNELS
import dev.shtanko.api.contributors.Variant.CONCURRENT
import dev.shtanko.api.contributors.Variant.NOT_CANCELLABLE
import dev.shtanko.api.contributors.Variant.PROGRESS
import dev.shtanko.api.contributors.Variant.SUSPEND
import dev.shtanko.api.createGitHubService
import dev.shtanko.api.tasks.loadContributorsBackground
import dev.shtanko.api.tasks.loadContributorsBlocking
import dev.shtanko.api.tasks.loadContributorsCallbacks
import dev.shtanko.api.tasks.loadContributorsChannels
import dev.shtanko.api.tasks.loadContributorsConcurrent
import dev.shtanko.api.tasks.loadContributorsNotCancellable
import dev.shtanko.api.tasks.loadContributorsProgress
import dev.shtanko.api.tasks.loadContributorsSuspend
import java.awt.event.ActionListener
import javax.swing.SwingUtilities
import kotlin.coroutines.CoroutineContext
import kotlin.system.exitProcess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class Variant(val description: String = "") {
    BLOCKING("Request1Blocking"),
    BACKGROUND("Request2Background"),
    CALLBACKS("Request3Callbacks"),
    SUSPEND("Request4Coroutine"),
    CONCURRENT("Request5Concurrent"),
    NOT_CANCELLABLE("Request6NotCancellable"),
    PROGRESS("Request6Progress"),
    CHANNELS("Request7Channels"),
}

private const val MILLIS = 1000
private const val H = 100

interface Contributors : CoroutineScope {

    val job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun init() {
        // Start a new loading on 'load' click
        addLoadListener {
            saveParams()
            loadContributors()
        }

        // Save preferences and exit on closing the window
        addOnWindowClosingListener {
            job.cancel()
            saveParams()
            exitProcess(0)
        }

        // Load stored params (user & password values)
        loadInitialParams()
    }

    fun loadContributors() {
        val (username, password, org, _) = getParams()
        val req = RequestData(username, password, org)

        clearResults()
        val service = createGitHubService(req.username, req.password)

        val startTime = System.currentTimeMillis()
        when (getSelectedVariant()) {
            BLOCKING -> { // Blocking UI thread
                val users = loadContributorsBlocking(service, req)
                updateResults(users, startTime)
            }

            BACKGROUND -> { // Blocking a background thread
                loadContributorsBackground(service, req)
            }

            CALLBACKS -> { // Using callbacks
                loadContributorsCallbacks(service, req) { users ->
                    SwingUtilities.invokeLater {
                        updateResults(users, startTime)
                    }
                }
            }

            SUSPEND -> { // Using coroutines
                launch {
                    val users = loadContributorsSuspend(service, req)
                    updateResults(users, startTime)
                }.setUpCancellation()
            }

            CONCURRENT -> { // Performing requests concurrently
                launch {
                    val users = loadContributorsConcurrent(service, req)
                    updateResults(users, startTime)
                }.setUpCancellation()
            }

            NOT_CANCELLABLE -> { // Performing requests in a non-cancellable way
                launch {
                    val users = loadContributorsNotCancellable(service, req)
                    updateResults(users, startTime)
                }.setUpCancellation()
            }

            PROGRESS -> { // Showing progress
                launch(Dispatchers.Default) {
                    loadContributorsProgress(service, req) { users, completed ->
                        withContext(Dispatchers.Main) {
                            updateResults(users, startTime, completed)
                        }
                    }
                }.setUpCancellation()
            }
            // Performing requests concurrently and showing progress
            CHANNELS -> {
                launch(Dispatchers.Default) {
                    loadContributorsChannels(service, req) { users, completed ->
                        withContext(Dispatchers.Main) {
                            updateResults(users, startTime, completed)
                        }
                    }
                }.setUpCancellation()
            }
        }
    }

    private enum class LoadingStatus { COMPLETED, CANCELED, IN_PROGRESS }

    private fun clearResults() {
        updateContributors(listOf())
        updateLoadingStatus(IN_PROGRESS)
        setActionsStatus(newLoadingEnabled = false)
    }

    private fun updateResults(
        users: List<User>,
        startTime: Long,
        completed: Boolean = true,
    ) {
        updateContributors(users)
        updateLoadingStatus(if (completed) COMPLETED else IN_PROGRESS, startTime)
        if (completed) {
            setActionsStatus(newLoadingEnabled = true)
        }
    }

    private fun updateLoadingStatus(
        status: LoadingStatus,
        startTime: Long? = null,
    ) {
        val time = if (startTime != null) {
            val time = System.currentTimeMillis() - startTime
            "${time / MILLIS}.${time % MILLIS / H} sec"
        } else {
            ""
        }

        val text = "Loading status: " +
            when (status) {
                COMPLETED -> "completed in $time"
                IN_PROGRESS -> "in progress $time"
                CANCELED -> "canceled"
            }
        setLoadingStatus(text, status == IN_PROGRESS)
    }

    private fun Job.setUpCancellation() {
        // make active the 'cancel' button
        setActionsStatus(newLoadingEnabled = false, cancellationEnabled = true)

        val loadingJob = this

        // cancel the loading job if the 'cancel' button was clicked
        val listener = ActionListener {
            loadingJob.cancel()
            updateLoadingStatus(CANCELED)
        }
        addCancelListener(listener)

        // update the status and remove the listener after the loading job is completed
        launch {
            loadingJob.join()
            setActionsStatus(newLoadingEnabled = true)
            removeCancelListener(listener)
        }
    }

    fun loadInitialParams() {
        setParams(loadStoredParams())
    }

    fun saveParams() {
        val params = getParams()
        if (params.username.isEmpty() && params.password.isEmpty()) {
            removeStoredParams()
        } else {
            saveParams(params)
        }
    }

    fun getSelectedVariant(): Variant

    fun updateContributors(users: List<User>)

    fun setLoadingStatus(text: String, inProgress: Boolean)

    fun setActionsStatus(newLoadingEnabled: Boolean, cancellationEnabled: Boolean = false)

    fun addCancelListener(listener: ActionListener)

    fun removeCancelListener(listener: ActionListener)

    fun addLoadListener(listener: () -> Unit)

    fun addOnWindowClosingListener(listener: () -> Unit)

    fun setParams(params: Params)

    fun getParams(): Params
}
