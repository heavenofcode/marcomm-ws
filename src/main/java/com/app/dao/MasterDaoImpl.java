package com.app.dao;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.model.Company;
import com.app.model.Employee;
import com.app.model.Role;
import com.app.model.User;

@Repository
public class MasterDaoImpl implements MasterDao {

	@Autowired private SessionFactory sessionFactory;
	
	public void save(Company company) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(company);
	}

	public void update(Company company) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(company);
	}

	@SuppressWarnings("unchecked")
	public Collection<Company> getCompanyByParam(String param) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Company e where e.name like :param order by e.name asc";
		Query query = session.createQuery(hql);
		query.setParameter("param", "%"+param+"%");
		return query.list();
	}

	public Company getCompanyById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Company e where e.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		if(query.list().size() != 0) {
			return (Company) query.list().get(0);
		} else {
			return null;
		}
	}

	public void save(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(employee);
	}

	public void update(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(employee);
	}

	@SuppressWarnings("unchecked")
	public Collection<Employee> getEmployeeByParam(String param) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Employee e where e.firstName like :param or e.lastName like :param order by e.firstName asc";
		Query query = session.createQuery(hql);
		query.setParameter("param", "%"+param+"%");
		return query.list();
	}

	public Employee getEmployeeById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Employee e where e.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		if(query.list().size() != 0) {
			return (Employee) query.list().get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Employee> getAllEmployee() {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Employee e order by e.firstName asc";
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	public void save(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(user);
	}

	public void update(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(user);
	}

	@SuppressWarnings("unchecked")
	public Collection<User> getUserByParam(String param) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from User e where e.username like :param order by e.username asc";
		Query query = session.createQuery(hql);
		query.setParameter("param", "%"+param+"%");
		return query.list();
	}

	public User getUserById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from User e where e.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		if(query.list().size() != 0) {
			return (User) query.list().get(0);
		} else {
			return null;
		}
	}
	
	public User getUserByUsername(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from User e where e.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		if(query.list().size() != 0) {
			return (User) query.list().get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<User> getAllUser() {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from User e order by e.id asc";
		Query query = session.createQuery(hql);
		return query.list();
	}

	public void save(Role role) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(role);
	}

	public void update(Role role) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(role);
	}

	@SuppressWarnings("unchecked")
	public Collection<Role> getRoleByParam(String param) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Role e where e.name like :param order by e.name asc";
		Query query = session.createQuery(hql);
		query.setParameter("param", "%"+param+"%");
		return query.list();
	}

	public Role getRoleById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Role e where e.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		if(query.list().size() != 0) {
			return (Role) query.list().get(0);
		} else {
			return null;
		}
	}
}