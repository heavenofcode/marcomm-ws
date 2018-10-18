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

import com.app.model.Employee;
import com.app.service.MasterService;
import com.app.util.SecurityApps;

@RestController
@RequestMapping (value="/api")
public class EmployeeController extends SecurityApps {
	
	@Autowired private MasterService masterService;

	@RequestMapping(value="/employee/name/{employeeName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> search(@PathVariable String employeeName, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Collection<Employee> employeeList = null;
		
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			if (employeeName != null || "".equals(employeeName)) {
				employeeList = this.masterService.getEmployeeByParam(employeeName);
				if(employeeList.size() == 0) {
					result.put("message", "Data not found");
					result.put("data", employeeList);
					return ResponseEntity.status(HttpStatus.OK).body(result);
				} else {
					result.put("message", "Data found");
					result.put("employeeList", employeeList);
					return ResponseEntity.status(HttpStatus.OK).body(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/employee/create", method = RequestMethod.POST,
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> create(@RequestBody Employee employee, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
//		Integer counter = this.masterService.getAllEmployee().size() + 1;
//		String code = "C" + counter;
//		employee.setCode(code);
			employee.setIsDelete(false);
			employee.setCreatedBy(1L);
			employee.setCreatedDate(new Date());
			this.masterService.save(employee);
			
			result.put("message", "Data successfully added");
			result.put("data", employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@RequestMapping(value="/employee/update", method = RequestMethod.PUT,
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> update(@RequestBody Employee employee, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			Employee o = new Employee();
			o = this.masterService.getEmployeeById(employee.getId());
			o.setCode(employee.getCode());
			o.setEmail(employee.getEmail());
			o.setFirstName(employee.getFirstName());
			o.setLastName(employee.getLastName());
			o.setmCompanyId(employee.getmCompanyId());
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
	
	@RequestMapping(value="/employee/deactivate/{employeeId}", method = RequestMethod.PUT,
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> deactivate(@PathVariable Long employeeId, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			Employee o = new Employee();
			o = this.masterService.getEmployeeById(employeeId);
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
