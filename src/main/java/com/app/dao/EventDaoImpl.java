package com.app.dao;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.model.Event;

@Repository
public class EventDaoImpl implements EventDao {
	
	@Autowired private SessionFactory sessionFactory;

	public void save(Event event) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(event);
	}

	public void update(Event event) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(event);
	}

	@SuppressWarnings("unchecked")
	public Collection<Event> getByParam(String param) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Event e where e.code like :param order by e.id desc";
		Query query = session.createQuery(hql);
		query.setParameter("param", "%"+param+"%");
		return query.list();
	}

	public Event getById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Event e where e.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return (Event) query.list().get(0);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Event> getAllEvent() {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Event e order by e.id asc";
		Query query = session.createQuery(hql);
		return query.list();
	}
}