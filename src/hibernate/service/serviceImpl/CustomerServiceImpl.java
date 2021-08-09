package hibernate.service.serviceImpl;

import java.util.List;

import hibernate.dao.dao.CustomerDao;
import hibernate.dao.daoImpl.CustomerDaoImpl;
import hibernate.entities.Customer;
import hibernate.service.service.CustomerService;


public class CustomerServiceImpl implements CustomerService {

	private CustomerDao dao;
	public CustomerServiceImpl() {
		this.dao = new CustomerDaoImpl();
	}
	@Override
	public Customer getCustomerbyId(int id) {
		return dao.getCustomerbyId(id);
	}

	@Override
	public Customer getCustomerByName(String name) {
		String n[] = name.split(" ");
		if(n.length!=3)
			return null;
		else			
		return dao.getCustomerByName(n[0], n[1], n[2]);
				
	}

	@Override
	public List<Customer> getAllCustomer() {
		return dao.getAllCustomer();
	}

	@Override
	public List<String> getAllCustomerNames() {
		return dao.getAllCustomerNames();
	}

	@Override
	public int saveCustomer(Customer customer) {
		return dao.saveCustomer(customer);
	}
}
