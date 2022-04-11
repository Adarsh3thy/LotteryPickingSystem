package com.today.tix.assign.lottery.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.today.tix.assign.lottery.model.UserSlot;
import com.today.tix.assign.lottery.model.UserSlotIdentity;

@Repository
public interface UserSlotRepository extends JpaRepository<UserSlot, UserSlotIdentity>{
	


}
