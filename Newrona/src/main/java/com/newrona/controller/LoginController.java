package com.newrona.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@org.springframework.web.bind.annotation.RestController
@PropertySource("classpath:application.properties")
public class LoginController
{

	@Resource
	private Environment env;

	

	@RequestMapping(value = "/api/getHello", method = RequestMethod.GET)
	public String searchUser()
	{
		
		return "helloPramod";

	}

	
	
}


