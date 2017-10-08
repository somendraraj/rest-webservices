package com.restapi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Somendra.Raj5
 *
 */

@RestController
@RequestMapping("/rest")
public class MainController {

	private static final Logger log = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String get(HttpServletRequest request, HttpServletResponse response) throws JSONException {

		log.info("*************Inside get method**************");
		JSONObject json = new JSONObject();
		json.put("method", "GET");
		json.put("value", "Hello World");

		return json.toString();
	}
	
	
	
}
