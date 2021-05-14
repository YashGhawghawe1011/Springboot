package com.yash.ticketbooking.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ticketbooking.dto.TicketRequestDTO;
import com.yash.ticketbooking.dto.TicketResponseDTO;
import com.yash.ticketbooking.response.MessageResponse;
import com.yash.ticketbooking.service.TicketService;

/**
 * @author yash.ghawghawe
 *
 */
@RestController
@RequestMapping("/tickets")
public class TicketController {

	private final Logger logger = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	private TicketService service;

	/**
	 * @param trainId
	 * @param dto
	 * @return ResponseEntity
	 */
	@PostMapping
	public ResponseEntity<?> bookTickets(@RequestBody @Valid TicketRequestDTO dto) {
		if (dto.getPassengers().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new MessageResponse("Please Enter The Passesnger Details in order to procedd furthur"));
		}
		TicketResponseDTO ticketResponseDTO = service.bookTickets(dto);
		if (!ObjectUtils.isEmpty(ticketResponseDTO)) {
			logger.info("Tickets Booked Successfully with ticket number : " + ticketResponseDTO.getTicketNumber());
			return ResponseEntity.ok(new MessageResponse(
					"Tickets Booked Successfully with ticket number : " + ticketResponseDTO.getTicketNumber()));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new MessageResponse("Please Enter The Valid TrainID and UserID"));
	}

	/**
	 * @param ticketNumber
	 * @return ResponseEntity
	 */
	@GetMapping
	public ResponseEntity<?> getTicketDetails(@RequestParam String ticketNumber) {
		if (ticketNumber.startsWith("PNR") && ticketNumber.length() == 13) {
			TicketResponseDTO ticketResponseDTO = service.getTicketDetails(ticketNumber);
			if (!ObjectUtils.isEmpty(ticketResponseDTO)) {
				return new ResponseEntity<TicketResponseDTO>(ticketResponseDTO, HttpStatus.OK);
			}
		}
		logger.info("TicketNumber is not Valid");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("TicketNumber is not Valid"));

	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getTicketHistory(@Valid @Pattern(regexp = "^[0-9]*$") @PathVariable long userId) {
		List<TicketResponseDTO> responseDTO = service.getTicketHistory(userId);
		if (ObjectUtils.isEmpty(responseDTO)) {
			logger.info("UserId is not Valid");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid userId"));
		}
		return new ResponseEntity<List<TicketResponseDTO>>(responseDTO, HttpStatus.OK);
	}
}
