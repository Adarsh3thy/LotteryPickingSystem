package com.today.tix.assign.lottery.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Table(name = "USER_SLOTS",catalog = "lottery")
public class UserSlot {
	
	@EmbeddedId
	private UserSlotIdentity userSlotIdentity;
	
	@Column(name="STATUS")
	String status;
	
	@Column(name="GUEST_COUNT")
	Integer guestCount;

}
