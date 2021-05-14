package com.yash.ticketbooking.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.yash.ticketbooking.dto.PassengerDTO;
import com.yash.ticketbooking.dto.TicketRequestDTO;
import com.yash.ticketbooking.dto.TicketResponseDTO;
import com.yash.ticketbooking.entity.Passenger;
import com.yash.ticketbooking.entity.Ticket;
import com.yash.ticketbooking.entity.Train;
import com.yash.ticketbooking.entity.User;
import com.yash.ticketbooking.repository.TicketRepository;
import com.yash.ticketbooking.repository.TrainRepository;
import com.yash.ticketbooking.service.TicketService;
import com.yash.ticketbooking.service.UserService;

/**
 * @author yash.ghawghawe
 *
 */
@Service
@Transactional
public class TicketServiceImpl implements TicketService {

	private final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	@Autowired
	private TrainRepository trainRepository;

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserService userService;

	/**
	 * @see com.yash.ticketbooking.service.TicketService#bookTickets(long,
	 *      com.yash.ticketbooking.dto.TicketRequestDTO)
	 */
	@Override
	public TicketResponseDTO bookTickets(TicketRequestDTO dto) {
		User user = userService.getUserById(Long.parseLong(dto.getUserId()));
		if (ObjectUtils.isEmpty(user)) {
			return null;
		}
		Optional<Train> train = trainRepository.findById(Long.parseLong(dto.getTrainId()));
		if (!ObjectUtils.isEmpty(train)) {
			logger.info("train exists with trainId : " + dto.getTrainId());
			Train savedTrain = train.get();
			if (dto.getPassengers().size() <= 6 && dto.getPassengers().size() < savedTrain.getAvailableSeats()) {
				logger.info("Number of seats are " + dto.getPassengers().size());
				Ticket ticket = new Ticket();
				List<PassengerDTO> passengerDTOs = dto.getPassengers();
				List<Passenger> passengers = new ArrayList<>();
				for (PassengerDTO passengerDTO : passengerDTOs) {
					Passenger passenger = new Passenger();
					BeanUtils.copyProperties(passengerDTO, passenger);
					passengers.add(passenger);
				}
				ticket.setUserId(Long.parseLong(dto.getUserId()));
				ticket.setTrainNumber(savedTrain.getTrainNumber());
				ticket.setTrainName(savedTrain.getTrainName());
				ticket.setDateOfJourney(savedTrain.getStartDate());
				ticket.setDateOfBooking(LocalDateTime.now());
				ticket.setStartTime(savedTrain.getDepartureTime());
				ticket.setCost(savedTrain.getFare() * dto.getPassengers().size());
				ticket.setDuration(savedTrain.getDuration());
				ticket.setFromStation(savedTrain.getSource());
				ticket.setToStation(savedTrain.getDestination());
				ticket.setTrainId(Long.parseLong(dto.getTrainId()));
				ticket.setTicketNumber(getAlphaNumericString(7));
				ticket.setPassengers(passengers);
				ticketRepository.save(ticket);
				int seats = savedTrain.getAvailableSeats() - dto.getPassengers().size();
				updateSeats(dto, seats);
				TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
				BeanUtils.copyProperties(ticket, ticketResponseDTO);
				return ticketResponseDTO;
			}
			logger.error("No of passesngers can`t be greater than available seats");
		}
		return null;
	}

	/**
	 * @param dto
	 * @param seats
	 */
	private void updateSeats(TicketRequestDTO dto, int seats) {
		trainRepository.updateTrain(seats, Long.parseLong(dto.getTrainId()));
	}

	/**
	 * @param n
	 * @return String
	 */
	static String getAlphaNumericString(int n) {

		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(10);
		sb.append("PNR");
		for (int i = 0; i < 10; i++) {

			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	/**
	 * @see com.yash.ticketbooking.service.TicketService#getTicketDetails(java.lang.String)
	 */
	@Override
	public TicketResponseDTO getTicketDetails(String ticketNumber) {
		Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber);
		if (!ObjectUtils.isEmpty(ticket)) {
			TicketResponseDTO responseDTO = new TicketResponseDTO();
			BeanUtils.copyProperties(ticket, responseDTO);
			return responseDTO;
		}
		return null;
	}

	/**
	 * 
	 * 
	 * @see com.yash.ticketbooking.service.TicketService#getTicketHistory(long)
	 */
	@Override
	public List<TicketResponseDTO> getTicketHistory(long userId) {
		List<Ticket> tickets = ticketRepository.findByUserId(userId);
		if (tickets.isEmpty()) {
			return null;
		}
		List<TicketResponseDTO> ticketResponseDTOs = new ArrayList<>();
		TicketResponseDTO responseDTO = new TicketResponseDTO();
		for (Ticket ticket : tickets) {
			BeanUtils.copyProperties(ticket, responseDTO);
			ticketResponseDTOs.add(responseDTO);
		}
		return ticketResponseDTOs;
	}

}
