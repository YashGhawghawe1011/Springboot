package com.yash.ticketbooking.entity;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yash.ghawghawe
 *
 */
@Getter
@Setter
@ToString
public class Login {

	@NotEmpty(message = "enter username")
	private String username;

	@NotEmpty(message = "enter password")
	private String password;

}
