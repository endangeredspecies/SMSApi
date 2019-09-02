package com.smsapi.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.smsapi.model.SMSApiResult;
import com.smsapi.model.SMSData;
@Service
public class OutboundService extends SMSApiService {

	@Override
	public SMSApiResult execute(SMSData data,Authentication authentication) {
		if (!validateInput(data.getTo(), data.getFrom())) {
			return new SMSApiResult("", "invalid input");
		}
		
		if (!isPresent(data.getFrom(),authentication)) {
			return new SMSApiResult("","from parameter not found");
		}
		String key=data.getTo()+data.getFrom();
		String value=(String)redisTemplate.opsForHash().get("TOANDFROM", key);
		if (value!=null && value.equals(data.getTo())) {
			return new SMSApiResult("", "sms from" +data.getFrom()+" to "+data.getTo()+" blocked by STOP request");
		}
		if (redisTemplate.opsForHash().hasKey("NOTALLOWED", data.getFrom())) {
			if ((int)redisTemplate.opsForHash().get("NOTALLOWED", data.getFrom())>50) {
				return new SMSApiResult("", "limit reached for from"+data.getFrom()); 
			}
		}
		return new SMSApiResult("outbound sms ok", "");
	}
	

}
