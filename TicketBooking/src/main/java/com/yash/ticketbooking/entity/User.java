package com.yash.ticketbooking.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yash.ghawghawe
 *
 */
@Entity
@Getter
@Setter
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	private String username;
	private String password;
	private String email;
	private BigDecimal age;
	private String contactNo;
	private String userRole;

}
