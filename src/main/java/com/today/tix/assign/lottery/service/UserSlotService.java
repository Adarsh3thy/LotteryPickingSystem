package com.today.tix.assign.lottery.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.today.tix.assign.lottery.model.UserSlot;
import com.today.tix.assign.lottery.model.UserSlotIdentity;
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
