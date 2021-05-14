package com.yash.ticketbooking.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ticketbooking.dto.TrainDTO;
import com.yash.ticketbooking.dto.TrainResponseDTO;
import com.yash.ticketbooking.exception.TrainNotAvailableException;
import com.yash.ticketbooking.response.MessageResponse;
import com.yash.ticketbooking.service.TrainService;

/**
 * @author yash.ghawghawe
 *
 */
@RestController
@RequestMapping("/trains")
public class TrainController {

	private final Logger logger = LoggerFactory.getLogger(TrainController.class);

	@Autowired
	private TrainService trainService;

	/**
	 * @param source
	 * @param destination
	 * @param startDate
	 * @return ResponseEntity<List<TrainResponseDTO>>
	 * @throws ParseException
	 * @throws TrainNotAvailableException
	 */
	@GetMapping
	public ResponseEntity<?> getAllAvailableTrains(
			@Valid @Pattern(regexp = "^[a-zA-Z]*$", message = "Enter Alphabets only") @RequestParam String source,
			@Valid @Pattern(regexp = "^[a-zA-Z]*$", message = "Enter Alphabets only") @RequestParam String destination,
			@Valid @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "Enter date in YYYY-MM-DD format") @RequestParam(required = true) String startDate)
			throws ParseException, TrainNotAvailableException {

		LocalDate localDate = LocalDate.now();
		if (new SimpleDateFormat("yyyy-MM-dd").parse(startDate)
				.before(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
			logger.info("past date cannot be entered");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new MessageResponse("You cannot Enter Past Date"));
		}
		List<TrainResponseDTO> trains = trainService.getAllAvailableTrains(source, destination, startDate);
		if (trains.isEmpty()) {
			throw new TrainNotAvailableException("no train is available for the provided data");
		}
		logger.info("Available trains fetched for data provided");
		return new ResponseEntity<List<TrainResponseDTO>>(trains, HttpStatus.OK);
	}

	/**
	 * @param trainDTO
	 */
	@PostMapping
	public void addTrain(@RequestBody TrainDTO trainDTO) {
		trainService.addTrain(trainDTO);

	}

}
