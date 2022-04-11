package com.today.tix.assign.lottery.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Table(name = "SHOW", catalog = "lottery")
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SHOW_ID")
	private long id;

	@Column(name = "SHOW_NAME")
	@NotEmpty(message = "*Please provide your show name")
	private String name;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID", referencedColumnName = "address_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "$$_hibernate_interceptor" })
	private Address address;
	
	 @OneToMany(mappedBy = "show")
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","$$_hibernate_interceptor"})
	 private List<Slot> slots;
}
