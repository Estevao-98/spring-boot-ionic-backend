package com.example.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public class MockEMailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEMailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Simulando envio de e-mail...");
        LOG.info(msg.toString());
        LOG.info("E-mail enviado");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Simulando envio de e-mail html...");
        LOG.info(msg.toString());
        LOG.info("E-mail enviado");
    }


}
