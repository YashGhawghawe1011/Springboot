package com.yash.ticketbooking.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
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
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequestDTO {

	@Pattern(regexp = "^[0-9]*$", message = "must be a number")
	@NotEmpty(message = "TrainId is Mandatory")
	private String trainId;
	@Pattern(regexp = "^[0-9]*$", message = "must be a number")
	@NotEmpty(message = "UserId is Mandatory")
	private String userId;
	@NotEmpty(message = "List of Passengers cannot be null")
	@Valid
	private List<PassengerDTO> passengers = new ArrayList<PassengerDTO>(6);

}
