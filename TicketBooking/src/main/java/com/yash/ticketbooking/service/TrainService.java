package com.yash.ticketbooking.service;

import java.util.List;

import com.yash.ticketbooking.dto.TrainDTO;
import com.yash.ticketbooking.dto.TrainResponseDTO;

/**
 * @author yash.ghawghawe
 *
 */
public interface TrainService {

	/**
	 * @param train
	 */
	void addTrain(TrainDTO trainDTO);

	/**
	 * @param source
	 * @param destination
	 * @param StartDate
	 * @return List<Train>
	 */
	List<TrainResponseDTO> getAllAvailableTrains(String source, String destination, String StartDate);

}
