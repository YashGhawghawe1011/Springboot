package com.yash.ticketbooking.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yash.ghawghawe
 *
 */
@Entity
@Getter
@Setter
public class Train {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long trainId;

	private String trainName;
	private String trainNumber;
	private String source;
	private String destination;
	private int availableSeats;
	private int duration;

	private Double fare;

	@Column(name = "deaparture_time", columnDefinition = "TIME")
	private LocalTime departureTime;

	@Column(name = "start_date", columnDefinition = "DATE")
	private LocalDate startDate;
}
