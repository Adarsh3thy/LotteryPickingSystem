package com.today.tix.assign.lottery.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "SLOT", catalog = "lottery")
public class Slot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SLOT_ID")
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "SHOW_TIME")
	@NotNull
	private Date showTime;
	
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "LOTTERY_DRAW_TIME")
	@NotNull
	private Date lotteryDrawTime;
	
	@Column(name = "LOTTERY_COUNT")
	@NotNull
	private int lotteryTickets;
	
	@Column(name="TICKET_LIM_PER_PERSON")
	@NotNull
	private int ticketLimitPerson;
	
	
	@Column(name="TICKET_COST")
	private double ticketCost;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="SHOW_ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","$$_hibernate_interceptor"})
	private Show show;
	 
	 @ManyToMany(mappedBy = "slots")
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","$$_hibernate_interceptor"})
	 private List<User> users;
	 
}
