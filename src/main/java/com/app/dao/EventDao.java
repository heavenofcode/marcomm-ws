package com.app.dao;

import java.util.Collection;

import com.app.model.Event;

public interface EventDao {

	public void save(Event event);
	public void update(Event event);
	public Collection<Event> getByParam(String param);
	public Event getById(Long id);
	public Collection<Event> getAllEvent();
}
