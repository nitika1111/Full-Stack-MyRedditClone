package com.nitika.myredditapp.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilderServiceImpl implements MailContentBuilderService{

	private final TemplateEngine templateEngine;
	
	public MailContentBuilderServiceImpl(TemplateEngine templateEngine) {

		this.templateEngine= templateEngine;
	}
	
	@Override
	public String build(String message) {
		Context context= new Context();		
		context.setVariable("message", message);
		
		return templateEngine.process("mailTemplate", context); // create mailTemplate.html
	}
}
