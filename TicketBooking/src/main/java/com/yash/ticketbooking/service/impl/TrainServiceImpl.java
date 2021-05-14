package com.yash.ticketbooking.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.yash.ticketbooking.dto.TrainDTO;
import com.yash.ticketbooking.dto.TrainResponseDTO;
import com.yash.ticketbooking.entity.Train;
import com.yash.ticketbooking.repository.TrainRepository;
import com.yash.ticketbooking.service.TrainService;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

	@Autowired
	private TrainRepository trainRepository;

	/**
	 * @see com.yash.ticketbooking.service.TrainService#addTrain(com.yash.ticketbooking.dto.TrainDTO)
	 */
	@Override
	public void addTrain(TrainDTO trainDTO) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime localTimeObj = LocalTime.parse(trainDTO.getDepartureTime(), formatter);

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(trainDTO.getStartDate(), dateTimeFormatter);

		Train train = new Train();
		BeanUtils.copyProperties(trainDTO, train);
		train.setDepartureTime(localTimeObj);
		train.setStartDate(localDate);
		trainRepository.save(train);
	}

	/**
	 * @see com.yash.ticketbooking.service.TrainService#getAllAvailableTrains(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<TrainResponseDTO> getAllAvailableTrains(String source, String destination, String starDate) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(starDate, dateTimeFormatter);
		
		Pageable pageable = PageRequest.of(0, 10, Sort.by(Direction.ASC, "duration"));
		List<Train> trains = trainRepository.findBySourceContainsAndDestinationContainsAndStartDate(source, destination,
				localDate,pageable);
		if (trains != null) {
			List<TrainResponseDTO> trainDTOs = new ArrayList<TrainResponseDTO>();

			for (Train train : trains) {
				TrainResponseDTO dto = new TrainResponseDTO();
				BeanUtils.copyProperties(train, dto);
				trainDTOs.add(dto);
			}
			return trainDTOs;
		}
		return null;
	}

}
