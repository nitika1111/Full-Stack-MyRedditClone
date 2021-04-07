package com.nitika.myredditapp.service;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.nitika.myredditapp.entity.NotificationEmail;
import com.nitika.myredditapp.exception.MyRedditException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailServiceImpl implements MailService{

	private final JavaMailSender mailSender;
	private final MailContentBuilderServiceImpl mailContentBuilder;
	
	public MailServiceImpl(JavaMailSender mailSender,
			MailContentBuilderServiceImpl mailContentBuilder) {
		this.mailContentBuilder= mailContentBuilder;
		this.mailSender= mailSender;
	}
	
	@Async
	public void sendMail(NotificationEmail notificationEmail) {
		MimeMessagePreparator messagePreparator= 
				mimeMessage -> {
					MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage);
					messageHelper.setFrom("myredditapp@email.com");
					messageHelper.setTo(notificationEmail.getRecipient());
					messageHelper.setSubject(notificationEmail.getSubject());
					//messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
					messageHelper.setText(notificationEmail.getBody());
				};
				
				try {
					mailSender.send(messagePreparator);
					log.info("Email sent!");
				} catch (MailException e) {
					throw new MyRedditException("Exception occored when sending email to "+
												notificationEmail.getRecipient());
				}
	}
}


















