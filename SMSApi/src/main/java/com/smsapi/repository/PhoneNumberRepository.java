package com.smsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smsapi.model.entities.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {

	List<PhoneNumber> findByAccountId(int accountId);

	PhoneNumber findByNumber(String number);

}
