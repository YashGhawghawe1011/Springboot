package com.yash.ticketbooking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.yash.ticketbooking.dto.UserDTO;
import com.yash.ticketbooking.dto.UserResponseDTO;
import com.yash.ticketbooking.entity.User;
import com.yash.ticketbooking.repository.UserRepository;
import com.yash.ticketbooking.service.UserService;

/**
 * @author yash.ghawghawe
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	/**
	 * @see com.yash.ticketbooking.service.UserService#saveUser(com.yash.ticketbooking.dto.UserDTO)
	 */
	@Override
	public UserResponseDTO saveUser(UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		User savedUser = repository.save(user);
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		BeanUtils.copyProperties(savedUser, userResponseDTO);
		return userResponseDTO;
	}

	/**
	 * @see com.yash.ticketbooking.service.UserService#getUser(long)
	 */
	public User getUser(long playerid) {
		Optional<User> player = repository.findById(playerid);
		if (player.isPresent()) {
			return player.get();
		}
		return null;
	}

	/**
	 * @see com.yash.ticketbooking.service.UserService#updateUser(long,
	 *      com.yash.ticketbooking.dto.UserDTO)
	 */
	@Override
	public UserDTO updateUser(long userid, UserDTO userDTO) {
		User dbuser = getUser(userid);
		dbuser.setUsername(userDTO.getUsername());
		dbuser.setPassword(userDTO.getPassword());
		dbuser.setEmail(userDTO.getEmail());
		dbuser.setContactNo(userDTO.getContactNo());
		dbuser.setAge(userDTO.getAge());
		User savedUser = repository.save(dbuser);
		UserDTO DTO = new UserDTO();
		BeanUtils.copyProperties(savedUser, DTO);
		return DTO;
	}

	/**
	 * @see com.yash.ticketbooking.service.UserService#findByUsername(java.lang.String)
	 */
	@Override
	public User findByUsername(String username) {
		return repository.findByUsername(username);
	}

	/**
	 * @see com.yash.ticketbooking.service.UserService#getAllUsers()
	 */
	@Override
	public List<UserDTO> getAllUsers() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by(Direction.ASC, "age"));
		List<User> users = repository.findAll(pageable).getContent();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (User user : users) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}

	/**
	 * @see com.yash.ticketbooking.service.UserService#getUserById(long)
	 */
	@Override
	public User getUserById(long userId) {
		Optional<User> user = repository.findById(userId);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	/**
	 * @see com.yash.ticketbooking.service.UserService#deleteUserById(long)
	 */
	@Override
	public UserDTO deleteUserById(long userid) {
		User user = getUserById(userid);
		if (user != null) {
			repository.deleteById(userid);
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			return userDTO;
		}
		return null;
	}

	/**
	 * @see com.yash.ticketbooking.service.UserService#findByUsernameAndPassword(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public User findByUsernameAndPassword(String userName, String password) {
		return repository.findByUsernameAndPassword(userName, password);
	}

}
