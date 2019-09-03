package com.smsapi.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class SMSApiControllerTest {
	
	private MockMvc mockMVC;
	
	@InjectMocks
	private SMSApiController controller ;
	
	public void setUp() throws Exception {
		mockMVC=MockMvcBuilders.standaloneSetup(controller).build();

	}
	@Test
	public void testSmsInbound() {
		//TODO
	}

	@Test
	public void testSmsOutbound() {
		//TODO
	}

}
