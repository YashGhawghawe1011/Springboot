package com.yash.ticketbooking.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.yash.ticketbooking.entity.Passenger;

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
public class TicketResponseDTO {

	private String ticketNumber;
	private LocalDate dateOfJourney;
	private LocalDateTime dateOfBooking;
	private LocalTime startTime;
	private double cost;
	private int duration;
	private String trainNumber;
	private String trainName;
	private String fromStation;
	private String toStation;
	private List<Passenger> passengers = new ArrayList<>();
}
