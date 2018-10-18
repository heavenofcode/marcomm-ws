package com.app.service;

import java.util.Collection;

import com.app.model.Company;
import com.app.model.Employee;
import com.app.model.Role;
import com.app.model.User;

public interface MasterService {

	public void save(Company company);
	public void update(Company company);
	public Collection<Company> getCompanyByParam(String param);
	public Company getCompanyById(Long id);

	public void save(Employee employee);
	public void update(Employee employee);
	public Collection<Employee> getEmployeeByParam(String param);
	public Employee getEmployeeById(Long id);
	public Collection<Employee> getAllEmployee();
	
	public void save(User user);
	public void update(User user);
	public Collection<User> getUserByParam(String param);
	public User getUserById(Long id);
	public User getUserByUsername(String username);
	public Collection<User> getAllUser();
	
	public void save(Role role);
	public void update(Role role);
	public Collection<Role> getRoleByParam(String param);
	public Role getRoleById(Long id);
}