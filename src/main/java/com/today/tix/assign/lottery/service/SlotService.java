package com.today.tix.assign.lottery.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
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
		
		Slot slot=slotRepository.getById(slotID);
		List<User> winners=new ArrayList<>();
		List<Long> winnersIds=new ArrayList<>();
		if(slot!=null) {
			List<User> users=slot.getUsers();
			//System.out.println("In slotService, users: "+users.size());
			LinkedList<User>  usersLinkedList=new LinkedList<>();
			usersLinkedList.addAll(users);
			//System.out.println("In slotService,linked list users: "+usersLinkedList.size());
			int lotteryTickets=slot.getLotteryTickets();
			//System.out.println("lotteryTickets: "+lotteryTickets);

			int max_range=users.size();
			for(int i=0;i<lotteryTickets;i++) {
				max_range=max_range-i;
				//System.out.println("max_range: "+max_range);
				int randWinner=ThreadLocalRandom.current().nextInt(0, max_range);
				//System.out.println("randWinner: "+randWinner);
				User temp=usersLinkedList.get(max_range-1);
				usersLinkedList.set(max_range-1, usersLinkedList.get(randWinner));
				usersLinkedList.set(randWinner, temp);
				max_range--;
			}
			List<User> usersList=new ArrayList<>(); 
			usersList.addAll(usersLinkedList);
			winners=usersList.subList(usersList.size()-lotteryTickets-1, usersList.size()-1);
			UserSlot userSlot=new UserSlot();
			for(User winner:winners) {
				userSlot=userSlotRepository.getById(new UserSlotIdentity(winner.getId(),slotID));
				userSlot.setStatus("WON");
				userSlotRepository.save(userSlot);
			}
			
			winnersIds=winners.stream().map(User::getId).collect(Collectors.toList());
			//System.out.println(winners.size());
		}
		
		return winnersIds;
	}
	
}
