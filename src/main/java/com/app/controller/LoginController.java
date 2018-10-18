package com.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.LoginVM;
import com.app.model.User;
import com.app.service.MasterService;

@RestController
@RequestMapping (value="/api")
public class LoginController {
	
	@Autowired private MasterService masterService;
	
	@RequestMapping(value="/login", method = RequestMethod.POST,
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginVM loginVM, HttpServletRequest request){

		User user = null;
		Map<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();

		if(loginVM != null) {
			user = this.masterService.getUserByUsername(loginVM.getUsername());
			if(user != null) {
				if(user.getPassword().equals(loginVM.getPassword())) {
					String salt = "Xsis123Winners@@@XXxx";
					String unGenerateToken = user.getUsername().concat(salt).concat(user.getPassword());
					String generatedToken = RandomStringUtils.randomAlphanumeric(128).toUpperCase();
					result.put("message", "Login Successfull");
					//result.put("generatedToken", unGenerateToken);
					result.put("generatedToken", generatedToken);
					session.setAttribute("token", salt.concat(generatedToken));
					return ResponseEntity.status(HttpStatus.OK).body(result);
				} else {
					result.put("message", "Invalid username/password");
					result.put("data", null);
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
				}
			} else {
				result.put("message", "Invalid username/password");
				result.put("data", null);
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
			}
		} else {
			result.put("message", "Invalid username/password");
			result.put("data", null);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
		}
	}
}