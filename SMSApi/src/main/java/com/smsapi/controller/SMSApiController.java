package com.smsapi.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smsapi.model.SMSApiResult;
import com.smsapi.model.SMSData;
import com.smsapi.service.InboundService;
import com.smsapi.service.OutboundService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags="SMS")
public class SMSApiController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	//@Autowired
	//private SMSApiService service;
	@Autowired
	private InboundService inboundService;
	
	@Autowired
	private OutboundService outboundService;
	
	@RequestMapping(value = "/inbound/sms", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(notes = "TODO",value="TODO")
	SMSApiResult smsInbound(
			@Valid @RequestBody @ApiParam(name = "body", required = true) SMSData inputData,Authentication authentication) {
		try {
			new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(inputData);
		} catch (Exception e) {
			logger.error("Error parsing JSON", e);
		}
		return inboundService.execute(inputData, authentication);
	}
	
	@RequestMapping(value = "/outbound/sms", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(notes = "TODO",value="TODO")
	SMSApiResult smsOutbound(
			@Valid @RequestBody @ApiParam(name = "body", required = true) SMSData inputData,Authentication authentication) {
		try {
			new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(inputData);
		} catch (Exception e) {
			logger.error("Error parsing JSON", e);
		}
		return outboundService.execute(inputData, authentication);
	}
	
//	@RequestMapping(method=RequestMethod.GET, value="/accounts", produces="application/json")
//	List<Account> getAllaccount() {
//		return service.getAllAccount(); 
//	}
	
//	@RequestMapping(value = "/username", method = RequestMethod.GET)
//	@ResponseBody
//	public String currentUserName(Authentication authentication) {
//	     return authentication.getName();
//	}

}
