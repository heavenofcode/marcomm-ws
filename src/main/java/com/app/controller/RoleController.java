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

import com.app.model.Role;
import com.app.service.MasterService;
import com.app.util.SecurityApps;

@RestController
@RequestMapping (value="/api")
public class RoleController extends SecurityApps {
	
	@Autowired private MasterService masterService;

	@RequestMapping(value="/role/name/{roleName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> search(@PathVariable String roleName, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Collection<Role> roleList = null;
		
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			if (roleName != null || "".equals(roleName)) {
				roleList = this.masterService.getRoleByParam(roleName);
				if(roleList.size() == 0) {
					result.put("message", "Data not found");
					result.put("data", roleList);
					return ResponseEntity.status(HttpStatus.OK).body(result);
				} else {
					result.put("message", "Data found");
					result.put("roleList", roleList);
					return ResponseEntity.status(HttpStatus.OK).body(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/role/create", method = RequestMethod.POST,
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> create(@RequestBody Role role, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			role.setIsDelete(false);
			role.setCreatedBy(1L);
			role.setCreatedDate(new Date());
			this.masterService.save(role);
			
			result.put("message", "Data successfully saved");
			result.put("data", role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@RequestMapping(value="/role/update", method = RequestMethod.PUT,
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> update(@RequestBody Role role, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			Role o = new Role();
			o = this.masterService.getRoleById(role.getId());
			o.setCode(role.getCode());
			o.setDescription(role.getDescription());
			o.setName(role.getName());
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
}
