package com.app.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.EventDao;
import com.app.model.Event;

@Service
@Transactional
public class EventServiceImpl implements EventService {
	
	@Autowired private EventDao eventDao;

	public void save(Event event) {
		this.eventDao.save(event);
	}

	public void update(Event event) {
		this.eventDao.update(event);
	}

	public void approve(Long eventId, Long assignTo) {
		Event event = new Event();
		this.eventDao.update(event);
	}

	public void reject(Long eventId, String rejectReason) {
		Event event = new Event();
		this.eventDao.update(event);
	}

	public void close(Long eventId) {
		Event event = new Event();
		this.eventDao.update(event);
	}

	public Collection<Event> getByParam(String param) {
		return this.eventDao.getByParam(param);
	}

	public Event getByEventId(Long eventId) {
		return this.eventDao.getById(eventId);
	}
	
	public Collection<Event> getAllEvent() {
		return this.eventDao.getAllEvent();
	}
}