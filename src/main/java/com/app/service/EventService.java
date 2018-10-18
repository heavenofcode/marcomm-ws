package com.app.service;

import java.util.Collection;

import com.app.model.Event;

public interface EventService {

	public void save(Event event);
	public void update(Event event);
	public void approve(Long eventId, Long assignTo);
	public void reject(Long eventId, String rejectReason);
	public void close(Long eventId);
	public Collection<Event> getByParam(String param);
	public Event getByEventId(Long eventId);
	public Collection<Event> getAllEvent();
}