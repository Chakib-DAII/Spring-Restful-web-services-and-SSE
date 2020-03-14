package com.DaiiChakib.Restfulwebservices.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DaiiChakib.Restfulwebservices.Models.HelloWorldBean;

@RestController
public class HelloWorldRestController {
	
	@Autowired
	private MessageSource messageSource; 

	@RequestMapping(method=RequestMethod.GET,path="hello-world")
	//@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
			return new HelloWorldBean("Hello World");	
	}
	
	@GetMapping(path="/hello-world/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable(name ="name") String name) {
			return new HelloWorldBean("Hello World, "+name);	
	}
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized(/*@RequestHeader(value="Accept-Language", required=false) Locale locale*/)
	{
		//return messageSource.getMessage("good.morning.message",null, locale);
		return messageSource.getMessage("good.morning.message", null, 
				LocaleContextHolder.getLocale());
	}
	
	
}
