package hibernate.dao.dao;

import java.util.List;

import hibernate.entities.Customer;

public interface CustomerDao {

	public Customer getCustomerbyId(int id);
	public Customer getCustomerByName(String fname,String mname,String lname);
	public List<Customer> getAllCustomer();
	public List<String> getAllCustomerNames();
	
	public int saveCustomer(Customer customer);
}
