package com.today.tix.assign.lottery.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.today.tix.assign.lottery.model.Show;
import com.today.tix.assign.lottery.model.Slot;
import com.today.tix.assign.lottery.model.User;
import com.today.tix.assign.lottery.model.UserSlot;

import com.today.tix.assign.lottery.service.ShowService;
import com.today.tix.assign.lottery.service.SlotService;
import com.today.tix.assign.lottery.service.UserService;
import com.today.tix.assign.lottery.service.UserSlotService;

@RestController
public class LotteryController {

	@Autowired
	private UserService userService;

	@Autowired
	private ShowService showService;
	
	@Autowired
	private SlotService slotService;
	
	@Autowired
	private UserSlotService userSlotService;
	

	@RequestMapping(value = "/registeruser", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Long createUser(@RequestBody @Valid User user,

			BindingResult bindingResult) {
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user",
					"There is already a user registered with the user name provided");
		}
		if (bindingResult.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {

			User newUser=userService.saveUser(user);
			return newUser.getId();
		}
	}
	
		
	@RequestMapping(value = "/updateruser/{id}", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public String updateUser(@RequestBody @Valid User user,@PathVariable("id") String id,
			BindingResult bindingResult) {
		User userExists = userService.getUserByID(Long.parseLong(id));
		if (userExists == null) {
			bindingResult.rejectValue("id", "error.user",
					"There is no user registered with the user id provided");
		}
		if (bindingResult.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {

			userService.saveUser(user);
			return "Success";
		}
	}
	
	
	@RequestMapping(value = "/enterlottery/{userid}/{slotid}", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public String enterLottery(@PathVariable("userid") String userId,
			@PathVariable("slotid") String slotID
			) {
		try {
		User userExists = userService.getUserByID(Long.parseLong(userId));
		System.out.println("User :"+userExists.getFirstname());
		Slot slotExists = slotService.getSlotByID(Long.parseLong(slotID));
		System.out.println("Slot :"+slotExists.getShowTime());
		
		if (userExists == null||slotExists==null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		else {
	        Set<Slot> slots = new HashSet<Slot>();
	        slots.add(slotExists);
			userExists.setSlots(slots);
			userService.saveUser(userExists);
			UserSlot userSlot=userSlotService.getUserSlotByID(Long.parseLong(userId), Long.parseLong(slotID));
			if(userSlot==null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
			userSlotService.saveUserSlot(userSlot);
			return "Success";
		}
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/registershow", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Long createShow(@RequestBody @Valid Show show,

			BindingResult bindingResult) {

		Show showExists = showService.findShowByName(show.getName());
		if (showExists != null) {
			bindingResult.rejectValue("name", "error.show",
					"There is already a show registered with the name provided");
		}
		if (bindingResult.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {

			Show newShow=showService.saveShow(show);
			return newShow.getId();
		}

	}
	
	@RequestMapping(value = "/drawlottery/{slotid}", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<Long> drawLottery(
			@PathVariable("slotid") String slotId) {
		try {
		Slot slotExists = slotService.getSlotByID(Long.parseLong(slotId));
		
		if (slotExists == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		 else {
			 System.out.println("Inside drawlotter: "+slotExists.getId());
			 return slotService.lottery(Long.parseLong(slotId));
			 
		
		}
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}
	
	

	@RequestMapping(value = "/createslot/{showid}", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Long createSlot(@RequestBody @Valid Slot slot,
			@PathVariable("showid") String showId,
			BindingResult bindingResult) {
		try {
		Show showExists = showService.getShowByID(Long.parseLong(showId));
		if (showExists == null ||slot.getShowTime()==null) {
			bindingResult.rejectValue("id", "error.show",
					"Missing data");
		}
		if (bindingResult.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {
		 slot.setShow(showExists);
		 System.out.println(slot.getShowTime());
		 Slot newSlot=slotService.saveSlot(slot);
		 return newSlot.getId();
		}
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	


}
