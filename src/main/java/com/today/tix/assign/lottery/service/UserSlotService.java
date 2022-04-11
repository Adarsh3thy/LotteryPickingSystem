package com.today.tix.assign.lottery.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.today.tix.assign.lottery.model.Slot;
import com.today.tix.assign.lottery.model.User;
import com.today.tix.assign.lottery.model.UserSlot;
import com.today.tix.assign.lottery.model.UserSlotIdentity;
import com.today.tix.assign.lottery.repository.SlotRepository;
import com.today.tix.assign.lottery.repository.UserSlotRepository;

@Service
public class UserSlotService {
	
	
	@Autowired
	private UserSlotRepository userSlotRepository;

	
	public UserSlot saveUserSlot(UserSlot userSlot) {
		if(userSlot.getStatus()==null) {
			userSlot.setStatus("LOTTERY_ENTERED");
		}
		return userSlotRepository.save(userSlot);
	}
	
	public UserSlot getUserSlotByID(Long userId,Long slotID) {
		
		return userSlotRepository.getById(new UserSlotIdentity(userId,slotID));
	}
	

}
