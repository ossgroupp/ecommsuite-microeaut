package com.ltmg

import com.agorapulse.worker.annotation.Cron
import jakarta.inject.Singleton

@Singleton
class EmailDigestSimpleJob(private val emailDigestService: EmailDigestService) {

    @Cron("45 6 * * *")
    fun sendDigestEmail() {
        emailDigestService.sendEmail("user@example.com")
    }

}