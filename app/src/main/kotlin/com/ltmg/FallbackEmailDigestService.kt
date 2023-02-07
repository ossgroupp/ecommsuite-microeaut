package com.ltmg

import org.slf4j.LoggerFactory;

import jakarta.inject.Singleton;

@Singleton
class FallbackEmailDigestService: EmailDigestService {

    private val log = LoggerFactory.getLogger(javaClass);

    override fun sendEmail(email: String) {
        log.info("Sending email to $email");
    }

}