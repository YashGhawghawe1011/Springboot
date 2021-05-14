package com.yash.ticketbooking.service;

import java.util.List;

import com.yash.ticketbooking.dto.TicketRequestDTO;
import com.yash.ticketbooking.dto.TicketResponseDTO;

/**
 * @author yash.ghawghawe
 *
 */
public interface TicketService {

	/**
	 * @param trainId
	 * @param userId 
	 * @param dto
	 * @return Ticket
	 */
	TicketResponseDTO bookTickets(TicketRequestDTO dto);

	/**
	 * @param ticketNumber
	 * @return Ticket
	 */
	TicketResponseDTO getTicketDetails(String ticketNumber);

	/**
	 * @param userId
	 * @return 	TicketResponseDTO
	 */
	List<TicketResponseDTO> getTicketHistory(long userId);

}
