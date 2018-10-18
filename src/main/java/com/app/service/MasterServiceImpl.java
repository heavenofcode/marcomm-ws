package com.app.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.MasterDao;
import com.app.model.Company;
import com.app.model.Employee;
import com.app.model.Role;
import com.app.model.User;

@Service
@Transactional	
public class MasterServiceImpl implements MasterService {
	
	@Autowired private MasterDao masterDao;

	public void save(Company company) {
		this.masterDao.save(company);
	}

	public void update(Company company) {
		this.masterDao.update(company);
	}

	public Collection<Company> getCompanyByParam(String param) {
		return this.masterDao.getCompanyByParam(param);
	}

	public Company getCompanyById(Long id) {
		Company company = null;
		company = this.masterDao.getCompanyById(id);
		return company;
	}
	
	public void save(Employee employee) {
		this.masterDao.save(employee);
	}

	public void update(Employee employee) {
		this.masterDao.update(employee);
	}

	public Collection<Employee> getEmployeeByParam(String param) {
		return this.masterDao.getEmployeeByParam(param);
	}

	public Employee getEmployeeById(Long id) {
		Employee employee = null;
		employee = this.masterDao.getEmployeeById(id);
		return employee;
	}
	
	public Collection<Employee> getAllEmployee() {
		return this.masterDao.getAllEmployee();
	}

	public void save(User user) {
		this.masterDao.save(user);
	}

	public void update(User user) {
		this.masterDao.update(user);
	}

	public Collection<User> getUserByParam(String param) {
		return this.masterDao.getUserByParam(param);
	}

	public User getUserById(Long id) {
		User user = null;
		user = this.masterDao.getUserById(id);
		return user;
	}
	
	public User getUserByUsername(String username) {
		User user = null;
		user = this.masterDao.getUserByUsername(username);
		return user;
	}
	
	public Collection<User> getAllUser() {
		return this.masterDao.getAllUser();
	}

	public void save(Role role) {
		this.masterDao.save(role);
	}

	public void update(Role role) {
		this.masterDao.update(role);
	}

	public Collection<Role> getRoleByParam(String param) {
		return this.masterDao.getRoleByParam(param);
	}

	public Role getRoleById(Long id) {
		Role role = null;
		role = this.masterDao.getRoleById(id);
		return role;
	}

}