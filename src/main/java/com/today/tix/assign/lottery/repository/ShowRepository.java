package com.today.tix.assign.lottery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.today.tix.assign.lottery.model.Show;


@Repository
public interface ShowRepository extends JpaRepository<Show, Long>{
	Show findByName(String name);
}
