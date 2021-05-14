package com.yash.ticketbooking.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ticketbooking.dto.LoginDTO;
import com.yash.ticketbooking.entity.User;
import com.yash.ticketbooking.response.MessageResponse;
import com.yash.ticketbooking.service.UserService;

/**
 * @author yash.ghawghawe
 *
 */
@RestController
public class LoginController {
	
	private final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService service;

	
	/**
	 * @param loginDTO
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
		User user = service.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
		if (!ObjectUtils.isEmpty(user)) {
			logger.info("Logged in Successfully");
			return ResponseEntity.ok(new MessageResponse("Logged in Successfully"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new MessageResponse("Wrong Credentials : username/password is wrong"));

	}

}
