package com.today.tix.assign.lottery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.today.tix.assign.lottery.model.Show;
import com.today.tix.assign.lottery.repository.ShowRepository;


@Service
public class ShowService {
	
	@Autowired
	private ShowRepository showRepository;
	
  public Show saveShow(Show show) {
		  
		  return showRepository.save(show);
	  }
  
  public Show findShowByName(String name) {
      return showRepository.findByName(name);
  }
  
  public Show getShowByID(Long id) {
		 return showRepository.getById(id);
	  }
  
}
