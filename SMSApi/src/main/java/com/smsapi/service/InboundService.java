package com.smsapi.service;

import java.util.concurrent.TimeUnit;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.smsapi.model.SMSApiResult;
import com.smsapi.model.SMSData;

@Service
public class InboundService extends SMSApiService {

	@Override
	public SMSApiResult execute(SMSData data,Authentication authentication) {
		if (!validateInput(data.getTo(), data.getFrom())) {
			return new SMSApiResult("", "invalid input");
		}
		if (!isPresent(data.getTo(),authentication)) {
			return new SMSApiResult("","to parameter not found");
		}
		if(data.getText().trim().equals("STOP")) {
			redisTemplate.opsForHash().put("TOANDFROM",data.getTo()+data.getFrom(), data.getTo());
			redisTemplate.expire("TOANDFROM", 4, TimeUnit.HOURS);
		}
		
		if(!redisTemplate.opsForHash().hasKey("NOTALLOWED",data.getFrom() )) {
			redisTemplate.opsForHash().put("NOTALLOWED", data.getFrom(), 1);
		}
		else {
			int value=(int)redisTemplate.opsForHash().get("NOTALLOWED", data.getFrom());
			redisTemplate.opsForHash().put("NOTALLOWED", data.getFrom(), value+1);
		}
		return new SMSApiResult("inbound sms ok", "");
	}

}
