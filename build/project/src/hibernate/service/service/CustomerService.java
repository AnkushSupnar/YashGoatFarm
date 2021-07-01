package hibernate.service.service;

import java.util.List;

import hibernate.entities.Customer;

public interface CustomerService {
	public Customer getCustomerbyId(int id);
	public Customer getCustomerByName(String name);
	public List<Customer> getAllCustomer();
	public List<String> getAllCustomerNames();
	
	public int saveCustomer(Customer customer);
}
