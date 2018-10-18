package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

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

import com.app.model.Event;
import com.app.service.EventService;
import com.app.util.SecurityApps;

@RestController
@RequestMapping (value="/api")
public class EventController extends SecurityApps {
	
	@Autowired private EventService eventService;

	@RequestMapping(value="/event/code/{code}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> search(@PathVariable String code, HttpServletRequest request){
		
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			Collection<Event> eventList = null;
			if (code != null || "".equals(code)) {
				eventList = this.eventService.getByParam(code);
				if(eventList.size() == 0) {
					result.put("message", "Data not found");
					result.put("data", eventList);
					return ResponseEntity.status(HttpStatus.OK).body(result);
				} else {
					result.put("message", "Data found");
					result.put("eventList", eventList);
					return ResponseEntity.status(HttpStatus.OK).body(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/event/create", method = RequestMethod.POST,
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> create(@RequestBody Event event, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		
		SimpleDateFormat ddMMyy = new SimpleDateFormat("ddMMyy");
		ddMMyy.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		SimpleDateFormat ddMMyyyy = new SimpleDateFormat("ddMMyyyy");
		
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			String dateNow = ddMMyy.format(new Date());
			Integer counter = this.eventService.getAllEvent().size() + 1;
			String code = "TRWOEV".concat(dateNow) + counter;
			
			String startDate = ddMMyyyy.format(event.getStartDate());
			String endDate = ddMMyyyy.format(event.getEndDate());
			
			event.setStartDate(ddMMyyyy.parse(startDate));
			event.setEndDate(ddMMyyyy.parse(endDate));
			event.setCode(code);
			event.setStatus(1);
			event.setIsDelete(false);
			event.setRequestedBy(1L);
			event.setRequestedDate(new Date());
			event.setCreatedBy(1L);
			event.setCreatedDate(new Date());
			this.eventService.save(event);
			
			result.put("message", "Request added");
			result.put("data", event);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@RequestMapping(value="/event/update", method = RequestMethod.PUT,
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> update(@RequestBody Event event, HttpServletRequest request){
		SimpleDateFormat ddMMyyyy = new SimpleDateFormat("ddMMyyyy");
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			String startDate = ddMMyyyy.format(event.getStartDate());
			String endDate = ddMMyyyy.format(event.getEndDate());
			
			event.setStartDate(ddMMyyyy.parse(startDate));
			event.setEndDate(ddMMyyyy.parse(endDate));
			
			Event o = new Event();
			o = this.eventService.getByEventId(event.getId());
			o.setBudget(event.getBudget());
			o.setEndDate(ddMMyyyy.parse(endDate));
			o.setEventName(event.getEventName());
			o.setNotes(event.getNotes());
			o.setPlace(event.getPlace());
			o.setStartDate(ddMMyyyy.parse(startDate));
			o.setUpdatedBy(1L);
			o.setUpdatedDate(new Date());
			this.eventService.update(o);
			
			result.put("message", "Request updated");
			result.put("data", o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@RequestMapping(value="/event/close/{eventId}", method = RequestMethod.PUT,
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> close(@PathVariable Long eventId, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			Event o = new Event();
			o = this.eventService.getByEventId(eventId);
			o.setClosedDate(new Date());
			o.setStatus(3);
			o.setUpdatedBy(1L);
			o.setUpdatedDate(new Date());
			this.eventService.update(o);
			
			result.put("message", "Request closed");
			result.put("data", o);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@RequestMapping(value="/event/approve/{eventId}", method = RequestMethod.PUT,
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> approve(@PathVariable Long eventId, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			Event o = new Event();
			o = this.eventService.getByEventId(eventId);
			o.setApprovedDate(new Date());
			o.setApprovedBy(1L);
			o.setStatus(2);
			o.setUpdatedBy(1L);
			o.setUpdatedDate(new Date());
			this.eventService.update(o);
			
			result.put("message", "Request closed");
			result.put("data", o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@RequestMapping(value="/event/reject/{eventId}/{reason}", method = RequestMethod.PUT,
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> reject(@PathVariable Long eventId, @PathVariable String reason, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(!this.checkToken(request)) {
				result.put("message", "Session closed");
				return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(result);
			}
			
			Event o = new Event();
			o = this.eventService.getByEventId(eventId);
			o.setRejectReason(reason);
			o.setStatus(0);
			o.setUpdatedBy(1L);
			o.setUpdatedDate(new Date());
			this.eventService.update(o);
			
			result.put("message", "Request rejected");
			result.put("data", o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}