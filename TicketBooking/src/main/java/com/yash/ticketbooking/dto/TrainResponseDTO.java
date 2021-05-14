package com.yash.ticketbooking.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;


/**
 * @author yash.ghawghawe
 *
 */
@Getter
@Setter
public class TrainResponseDTO {

	private String trainName;
	private String trainNumber;
	private String source;
	private String destination;

	private int availableSeats;
	private int duration;
	
	private Double fare;

	private LocalDate startDate;
	private LocalTime departureTime;

}
