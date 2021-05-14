package com.yash.ticketbooking.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author yash.ghawghawe
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {

	@NotEmpty(message = "Name is mandatory")
	private String name;
	@NotEmpty(message = "Age is mandatory")
	@Pattern(regexp="^[0-9]*$",message="must be a number")
	private String age;
	@NotEmpty(message = "SeatNumber is mandatory")
	private String SeatNumber;
}
