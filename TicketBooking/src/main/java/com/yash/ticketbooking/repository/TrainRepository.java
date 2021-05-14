package com.yash.ticketbooking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yash.ticketbooking.entity.Train;

/**
 * @author yash.ghawghawe
 *
 */
@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {

	/**
	 * @param source
	 * @param destination
	 * @param startDate
	 * @return List<Train>
	 */
	List<Train> findBySourceContainsAndDestinationContainsAndStartDate(String source, String destination,
			LocalDate startDate, Pageable pageable);

	/**
	 * @param availableSeats
	 * @param trainId
	 */
	@Modifying
	@Query("update Train t set t.availableSeats=:availableSeats where t.trainId =:trainId")
	void updateTrain(@Param("availableSeats") int availableSeats, @Param("trainId") long trainId);

}
