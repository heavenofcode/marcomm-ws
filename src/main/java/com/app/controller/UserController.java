package com.app.controller;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.User;
import com.app.service.MasterService;
import com.app.util.SecurityApps;

@RestController
@RequestMapping (value="/api")
public class UserController extends SecurityApps {
	
	@Autowired private MasterService masterService;

	@RequestMapping(value="/user/name/{userName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> search(@PathVariable String userName, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Collection<User> userList = null;
		
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			if (userName != null || "".equals(userName)) {
				userList = this.masterService.getUserByParam(userName);
				if(userList.size() == 0) {
					result.put("message", "Data not found");
					result.put("data", userList);
					return ResponseEntity.status(HttpStatus.OK).body(result);
				} else {
					result.put("message", "Data found");
					result.put("userList", userList);
					return ResponseEntity.status(HttpStatus.OK).body(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/user/create", method = RequestMethod.POST,
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> create(@RequestBody User user, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			user.setIsDelete(false);
			user.setCreatedBy(1L);
			user.setCreatedDate(new Date());
			this.masterService.save(user);
			
			result.put("message", "Data successfully added");
			result.put("data", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@RequestMapping(value="/user/update", method = RequestMethod.PUT,
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> update(@RequestBody User user, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			User o = new User();
			o = this.masterService.getUserById(user.getId());
			o.setmEmployeeId(user.getmEmployeeId());
			o.setmRoleId(user.getmRoleId());
			o.setPassword(user.getPassword());
			o.setUsername(user.getUsername());
			o.setUpdatedBy(1L);
			o.setUpdatedDate(new Date());
			this.masterService.update(o);
			
			result.put("message", "Data successfully updated");
			result.put("data", o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@RequestMapping(value="/user/deactivate/{userId}", method = RequestMethod.PUT,
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> deactivate(@PathVariable Long userId, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			User o = new User();
			o = this.masterService.getUserById(userId);
			o.setIsDelete(true);
			o.setUpdatedBy(1L);
			o.setUpdatedDate(new Date());
			this.masterService.update(o);
			
			result.put("message", "Data successfully deactivated");
			result.put("data", o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
