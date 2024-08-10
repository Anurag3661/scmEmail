package com.scm.scm20;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scm.scm20.services.EmailService;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailService service;

	@Test
	void sendEmailTest(){
		service.sendEmail("sharmaanurag.as22@gmail.com", "hello from clouds", "testing our email service for scm");
	}
}
