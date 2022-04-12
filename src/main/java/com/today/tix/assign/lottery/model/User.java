package com.today.tix.assign.lottery.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "USER",catalog = "lottery")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private long id;
	
	@Column(name = "USER_FIRST_NAME")
	@NotEmpty(message = "*Please provide your first name")
    private String firstname;
	
	@Column(name = "USER_LAST_NAME")
	@NotEmpty(message = "*Please provide your last name")
    private String lastname;
    
    @Column(name = "USER_EMAIL", unique=true)
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
    private String email;
    
    @Column(name = "PASSWORD")
    @NotEmpty(message = "*Please provide password")
    private String password;
  
    
    @ManyToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
    @JoinTable(name="USER_SLOTS",
	joinColumns={@JoinColumn(name="USER_ID")},
	inverseJoinColumns={@JoinColumn(name="SLOT_ID")})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","$$_hibernate_interceptor"})
	private Set<Slot> slots;
   
    
}
