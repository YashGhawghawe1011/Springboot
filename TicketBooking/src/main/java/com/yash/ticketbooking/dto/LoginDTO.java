package com.yash.ticketbooking.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
	
	@NotEmpty(message="Username cannot be empty")
	private String username;
	@NotEmpty(message="Password cannot be empty")
	private String password;
	

}
