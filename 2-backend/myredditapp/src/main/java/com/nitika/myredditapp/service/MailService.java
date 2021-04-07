package com.nitika.myredditapp.service;

import com.nitika.myredditapp.entity.NotificationEmail;

public interface MailService {

	public void sendMail(NotificationEmail notificationEmail);
}
