package com.today.tix.assign.lottery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.today.tix.assign.lottery.model.Slot;

@Repository
public interface SlotRepository  extends JpaRepository<Slot, Long>{
	
}
