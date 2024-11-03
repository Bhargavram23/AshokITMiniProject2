package com.rama.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
	String fromEmail;

	public boolean sendConfirmationEmail(String toEmail, String subject,String content)
			throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		mimeMessageHelper.setFrom(fromEmail);
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(content);
		try {
			javaMailSender.send(mimeMessage);
			return true;
		} catch (Exception e) {
			throw e;
		}

	}

}
