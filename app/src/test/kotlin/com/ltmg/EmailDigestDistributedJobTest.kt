package com.ltmg

import com.agorapulse.worker.JobManager
import io.micronaut.context.annotation.Property
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.awaitility.Awaitility.await
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.util.concurrent.TimeUnit

@MicronautTest
@Property(name = "worker.jobs.email-digest-distributed-job-send-email.enabled", value = "true")
@Property(name = "worker.jobs.email-digest-distributed-job-send-email.fixed-rate", value = "1ms")
internal class EmailDigestDistributedJobTest {

    @Inject
    lateinit var jobManager: JobManager

    @Inject
    lateinit var emailDigestService: EmailDigestService

    @MockBean(FallbackEmailDigestService::class)
    fun emailDigestServiceMock(): EmailDigestService {
        return mock(EmailDigestService::class.java)
    }

    @Test
    fun testSendEmailsDistributed() {
        jobManager.forceRun(EmailDigestDistributedJob::class.java, "generateEmailsForDigest")

        await().atMost(2, TimeUnit.SECONDS).untilAsserted {
            verify(emailDigestService, times(1)).sendEmail("user@example.com")
        }
    }

}