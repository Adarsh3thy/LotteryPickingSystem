package com.today.tix.assign.lottery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.today.tix.assign.lottery.model.User;
import com.today.tix.assign.lottery.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	 private UserRepository userRepository;
	
	
	  public User findUserByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }
	  
	  
	  public User saveUser(User user) {
		  
		  return userRepository.save(user);
	  }
	  
	  public User getUserByID(Long id) {
		 return userRepository.getById(id);
	  }
}
