package com.yash.ticketbooking.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yash.ghawghawe
 *
 */
@Getter
@Setter
public class UserResponseDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String email;
	private BigDecimal age;
	private String contactNo;
	private String userRole;

}
