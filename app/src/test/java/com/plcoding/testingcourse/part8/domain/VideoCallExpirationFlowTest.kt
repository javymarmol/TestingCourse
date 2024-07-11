package com.plcoding.testingcourse.part8.domain

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.doesNotContain
import com.plcoding.testingcourse.util.MainCoroutineExtension
import com.plcoding.testingcourse.util.MutableClock
import com.plcoding.testingcourse.util.advanceTimeBy
import com.plcoding.testingcourse.util.scheduleVideoCall
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.Clock
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalTime::class)
@ExtendWith(MainCoroutineExtension::class)
class VideoCallExpirationFlowTest {

    @Test
    fun `Test call expiration`() = runTest {
        val clock = MutableClock(Clock.systemDefaultZone())

        val now = LocalDateTime.now()
        val inFiveMinutes = now.plusMinutes(5)
        val inTenMinutes = now.plusMinutes(10)

        val scheduledVideoCalls = listOf(
//            scheduleVideoCall(now),
            scheduleVideoCall(time = inFiveMinutes),
            scheduleVideoCall(inTenMinutes)
        )

        VideoCallExpirationFlow(scheduledVideoCalls, clock).test {
            awaitItem() // Ignore empty emission

            advanceTimeBy(6.minutes, clock) // Expire first call
            val emission1 = awaitItem()
            assertThat(emission1).contains(scheduledVideoCalls[0])
            assertThat(emission1).doesNotContain(scheduledVideoCalls[1])

            advanceTimeBy(5.minutes, clock)  // Expire second call
            val emission2 = awaitItem()
            assertThat(emission2).contains(scheduledVideoCalls[1])
        }
    }

}