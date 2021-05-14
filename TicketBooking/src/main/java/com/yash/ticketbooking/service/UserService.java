package com.yash.ticketbooking.service;

import java.util.List;

import com.yash.ticketbooking.dto.UserDTO;
import com.yash.ticketbooking.dto.UserResponseDTO;
import com.yash.ticketbooking.entity.User;

/**
 * @author yash.ghawghawe
 *
 */
public interface UserService {

	/**
	 * @param user
	 * @return User
	 */
	UserResponseDTO saveUser(UserDTO userDTO);

	/**
	 * @param userid
	 * @param user
	 * @return
	 */
	UserDTO updateUser(long userid, UserDTO user);

	/**
	 * @param userid
	 * @return User
	 */
	User getUser(long userid);

	/**
	 * @param username
	 * @return User
	 */
	User findByUsername(String username);

	/**
	 * @return List<User>
	 */
	List<UserDTO> getAllUsers();

	/**
	 * @param userId
	 * @return User
	 */
	User getUserById(long userId);

	/**
	 * @param userid
	 * @return User
	 */
	UserDTO deleteUserById(long userid);

	/**
	 * @param userName
	 * @param password
	 * @return User
	 */
	User findByUsernameAndPassword(String userName, String password);

}
