package com.today.tix.assign.lottery.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
public class SlotService {
	
	
	@Autowired
	private SlotRepository slotRepository;
	
	@Autowired
	private UserSlotRepository userSlotRepository;
	

	public Slot saveSlot(Slot slot) {
		  
		  return slotRepository.save(slot);
	  }
	
	public Slot getSlotByID(Long id) {
		 return slotRepository.getById(id);
	  }
	
	public List<Long> lottery(Long slotID){
		
		/*Get the details of the slot based on slot ID*/
		Slot slot=slotRepository.getById(slotID);

		List<Long> winnersIds=new ArrayList<>();
		if(slot!=null) {
			/*Get all users who have entered into lottery for the slot*/
			List<User> users=new ArrayList<>();
			 users= slot.getUsers();
			
			
			/*Get the count of winners to be drawn from the lottery*/
			int lotteryTickets=slot.getLotteryTickets();
			System.out.println("lotteryTickets "+lotteryTickets);
			
			/*if the count of winners to be drawn is greater or equal to
			 *  users in the lottery
			 * then return all users as winners
			 */
			if(users.size()>0) {
				
			/*Initialize max_range to size of users list*/
			int max_range=users.size();
			int randWinner=0, i=0, userCount=0;
			while(i<lotteryTickets) {
				max_range=max_range-i;
				
				/*Generate a random number within 0 to max_range*/
				randWinner=ThreadLocalRandom.current().nextInt(0, max_range);
				System.out.println("randWinner "+randWinner);
				/*Increment i with the guestCount*/
				i+=userSlotRepository.getById(new UserSlotIdentity(users.get(randWinner).getId(),slotID)).getGuestCount();
				
				/*Swap the user at position randWinner with user at position max_range*/
				User temp=users.get(max_range-1);
				users.set(max_range-1, users.get(randWinner));
				users.set(randWinner, temp);
				userCount++;
				System.out.println("i "+i);
				
			}

			/*extract the winners available from usersList.size()-lotteryTickets-1 to size of arrays*/
			List<User> winners=users.subList(users.size()-userCount-1, users.size()-1);
			UserSlot userSlot=new UserSlot();
			
			/*Update the status in database*/
			for(User winner:winners) {
				userSlot=userSlotRepository.getById(new UserSlotIdentity(winner.getId(),slotID));
				userSlot.setStatus("WON");
				userSlotRepository.save(userSlot);
			}
			
			/*Extract winner user ID's */
			winnersIds=winners.stream().map(User::getId).collect(Collectors.toList());
			}
			}
		
		return winnersIds;
	}
	
}
