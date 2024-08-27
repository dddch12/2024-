package com.cq.cd.service;

public interface EmailService {
	void sendMessageToEmail(String verifyCode, String email);
}
