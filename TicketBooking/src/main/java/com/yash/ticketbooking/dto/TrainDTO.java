package com.yash.ticketbooking.dto;

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
public class TrainDTO {

	private String trainName;
	private String trainNumber;
	private String source;
	private String destination;

	private int availableSeats;
	private int duration;

	private Double fare;
	private String departureTime;
	private String startDate;
}
