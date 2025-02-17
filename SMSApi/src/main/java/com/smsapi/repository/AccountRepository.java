package com.smsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smsapi.model.entities.Account;


public interface AccountRepository extends JpaRepository<Account, Integer> {
	Account findByUsername(String username);
}
