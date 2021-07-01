package hibernate.dao.dao;

import java.util.List;

import hibernate.entities.Employee;
public interface EmployeeDao {

	public Employee getEmployeeById(int id);
	public Employee getEmployeeByName(String name);
	public List<String> getAllEmployeeNames();
	public List<Employee>getAllEmployee();
	
	public List<String> getAllSalesmanNames();
	
	public int saveEmployee(Employee employee);
	
	
}
