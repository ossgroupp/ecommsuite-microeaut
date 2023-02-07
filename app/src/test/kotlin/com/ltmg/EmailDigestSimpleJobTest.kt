package com.ltmg

import com.agorapulse.worker.JobManager
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.awaitility.Awaitility.await
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.util.concurrent.TimeUnit

@MicronautTest
internal class EmailDigestSimpleJobTest {

    @Inject
    lateinit var jobManager: JobManager

    @Inject
    lateinit var emailDigestService: EmailDigestService

    @MockBean(FallbackEmailDigestService::class)
    fun emailDigestServiceMock() : EmailDigestService {
        return mock(EmailDigestService::class.java)
    }

    @Test
    fun testSendEmailsDirect() {
        jobManager.forceRun(EmailDigestSimpleJob::class.java)

        await().atMost(2, TimeUnit.SECONDS).untilAsserted {
            verify(emailDigestService, times(1)).sendEmail("user@example.com")
        }
    }

}