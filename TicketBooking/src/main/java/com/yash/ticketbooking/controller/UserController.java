package com.yash.ticketbooking.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ticketbooking.dto.UserDTO;
import com.yash.ticketbooking.dto.UserResponseDTO;
import com.yash.ticketbooking.entity.User;
import com.yash.ticketbooking.response.MessageResponse;
import com.yash.ticketbooking.service.UserService;

/**
 * @author yash.ghawghawe
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * @param userDTO
	 * @return ResponseEntity
	 */
	@PostMapping
	public ResponseEntity<?> register(@RequestBody @Valid UserDTO userDTO) {
		User existingUser = userService.findByUsername(userDTO.getUsername());
		if (ObjectUtils.isEmpty(existingUser)) {
			if (userDTO.getContactNo().matches("^\\d{10}$")) {
				UserResponseDTO userResponseDTO = userService.saveUser(userDTO);
				logger.info("User Registered Successfully");
				return new ResponseEntity<UserResponseDTO>(userResponseDTO, HttpStatus.CREATED);
			}
			logger.info("Number should only contain digits");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new MessageResponse("Number should only contain digits"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("User Already Exists"));

	}

	/**
	 * @return ResponseEntity<List<UserDTO>>
	 */
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> users = userService.getAllUsers();
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);

	}

	/**
	 * @param userId
	 * @return ResponseEntity
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserById(@Valid @Pattern(regexp = "^[0-9]*$") @PathVariable long userId) {
		User user = userService.getUserById(userId);
		if (!ObjectUtils.isEmpty(user)) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
		}
		logger.info("User Doesn`t Exist for the userId : " + String.valueOf(userId));
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new MessageResponse("User Doesn`t Exist for the userId : " + String.valueOf(userId)));
	}

	/**
	 * @param userid
	 * @param userDTO
	 * @return ResponseEntity
	 */
	@PutMapping("/{userid}")
	public ResponseEntity<?> updateUser(@Valid @Pattern(regexp = "^[0-9]*$") @PathVariable("userid") long userid,
			@RequestBody @Valid UserDTO userDTO) {
		User savedUser = userService.getUserById(userid);
		if (!ObjectUtils.isEmpty(savedUser)) {
			if (userDTO.getContactNo().matches("^\\d{10}$")) {
				UserDTO dto = userService.updateUser(userid, userDTO);
				return new ResponseEntity<UserDTO>(dto, HttpStatus.CREATED);
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new MessageResponse("Number should only contain digits and please enter a valid email Address"));
		}
		logger.info("User Doesn`t Exist for the userId : " + String.valueOf(userid));
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new MessageResponse("User Doesn`t Exist for the userId : " + String.valueOf(userid)));
	}

	/**
	 * @param userid
	 * @return ResponseEntity
	 */
	@DeleteMapping("/{userid}")
	public ResponseEntity<?> deleteUser(@Valid @Pattern(regexp = "^[0-9]*$") @PathVariable("userid") long userid) {
		UserDTO userDTO = userService.deleteUserById(userid);
		if (!ObjectUtils.isEmpty(userDTO)) {
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
		}
		logger.info("User Doesn`t Exist for the userId : " + String.valueOf(userid));
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new MessageResponse("User Doesn`t Exist for the userId : " + String.valueOf(userid)));

	}

}
