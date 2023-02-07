package com.ltmg

import com.agorapulse.worker.annotation.Cron
import com.agorapulse.worker.annotation.FixedRate
import jakarta.inject.Singleton

import java.util.Collections

@Singleton
class EmailDigestDistributedJob(private val emailDigestService: EmailDigestService) {

    @Cron("45 6 * * *")
    fun generateEmailsForDigest(): Iterable<String> {
        return listOf("user@example.com");
    }

    @FixedRate("1s")
    fun sendEmail(email: String) {
        emailDigestService.sendEmail(email);
    }

}