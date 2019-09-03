package com.smsapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;

import com.smsapi.model.SMSApiResult;
import com.smsapi.model.SMSData;
import com.smsapi.model.entities.Account;
import com.smsapi.model.entities.PhoneNumber;
import com.smsapi.repository.AccountRepository;
import com.smsapi.repository.PhoneNumberRepository;

//@Service
public abstract class SMSApiService {
	@Autowired
	protected AccountRepository accountRepository;
	@Autowired
	protected PhoneNumberRepository phoneNumberRepository; 
	@Autowired 
	protected RedisTemplate<String, Object> redisTemplate;
	
	public List<Account> getAllAccount(){
		return accountRepository.findAll();
	}
	
	public abstract SMSApiResult execute(SMSData data,Authentication authentication);
	
	
	public String validateInput(String to, String from, String text) {
		
		if (to.length()<6 || to.length()>16) {
			return "to length should be between 6 and 16";
		}
		if (from.length()<6 || from.length()>16) {
			return "from length should be between 6 and 16";
		}
		if (text.length()<1 || from.length()>120) {
			return "text should have min 1 and max 120 characters";
		}
		
		PhoneNumber toNumber=phoneNumberRepository.findByNumber(to);
		if (toNumber!=null) {
			PhoneNumber fromNumber=phoneNumberRepository.findByNumber(from);
			if (fromNumber==null) {
				return "from parameter is invalid";
			}
			else {
				return null;
			}
			
		}
		else {
			return "to parameter is invalid";
		}
		
	}
	public boolean isPresent(String number, Authentication authentication) {
		
		int accountId;
		String username=authentication.getName();
		accountId=accountRepository.findByUsername(username).getId();
		List<PhoneNumber> numbers=phoneNumberRepository.findByAccountId(accountId);
		for (PhoneNumber num:numbers) {
			if (num.getNumber().equals(number)) {
				return true;
			}
		}
		return false;
	}

}
