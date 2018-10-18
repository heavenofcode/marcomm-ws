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

import com.app.model.Company;
import com.app.service.MasterService;
import com.app.util.SecurityApps;

@RestController
@RequestMapping (value="/api")
public class CompanyController extends SecurityApps {
	
	@Autowired private MasterService masterService;

	@RequestMapping(value="/company/name/{companyName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> search(@PathVariable String companyName, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			Collection<Company> companyList = null;
			if (companyName != null || "".equals(companyName)) {
				companyList = this.masterService.getCompanyByParam(companyName);
				if(companyList.size() == 0) {
					result.put("message", "Data not found");
					result.put("data", companyList);
					return ResponseEntity.status(HttpStatus.OK).body(result);
				} else {
					result.put("message", "Data found");
					result.put("companyList", companyList);
					return ResponseEntity.status(HttpStatus.OK).body(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/company/create", method = RequestMethod.POST,
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> create(@RequestBody Company company, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			company.setIsDelete(false);
			company.setCreatedBy(1L);
			company.setCreatedDate(new Date());
			this.masterService.save(company);
			
			result.put("message", "Data successfully saved");
			result.put("data", company);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@RequestMapping(value="/company/update", method = RequestMethod.PUT,
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> update(@RequestBody Company company, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			Company o = new Company();
			o = this.masterService.getCompanyById(company.getId());
			o.setAddress(company.getAddress());
			o.setCode(company.getCode());
			o.setEmail(company.getEmail());
			o.setName(company.getName());
			o.setPhone(company.getName());
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
