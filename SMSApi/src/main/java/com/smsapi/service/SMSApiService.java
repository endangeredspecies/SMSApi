package com.smsapi.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

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
	
	
	public boolean validateInput(String to, String from) {
		PhoneNumber toNumber=phoneNumberRepository.findByNumber(to);
		if (toNumber!=null) {
			PhoneNumber fromNumber=phoneNumberRepository.findByNumber(from);
			if (fromNumber!=null) {
				return true;
			}
		}
		return false;
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
