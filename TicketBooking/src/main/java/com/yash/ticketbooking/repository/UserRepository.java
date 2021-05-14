package com.yash.ticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.ticketbooking.entity.User;

/**
 * @author yash.ghawghawe
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 * @param username
	 * @return User
	 */
	User findByUsername(String username);

	/**
	 * @param userName
	 * @param password
	 * @return User
	 */
	User findByUsernameAndPassword(String userName, String password);

}
