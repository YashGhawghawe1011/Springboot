package com.yash.ticketbooking.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.Getter;
import lombok.Setter;

/**
 * @author yash.ghawghawe
 *
 */
@Getter
@Setter
public class UserDTO {

	@NotEmpty(message = "Username is mandatory")
	private String username;
	@NotEmpty(message = "password is mandatory")
	private String password;
	@NotEmpty(message = "Email is mandatory")
	@Email
	private String email;
	@NotNull(message = "Age is mandatory")
	@DecimalMax(value="100")
	@DecimalMin(value="18")
	private BigDecimal age;
	@NotEmpty(message = "Contact Number is mandatory")
	@Size(max=10,min=10,message="Contact Number should contain 10 digits")
	private String contactNo;
	@NotEmpty(message = "Role is mandatory")
	private String userRole;

}
