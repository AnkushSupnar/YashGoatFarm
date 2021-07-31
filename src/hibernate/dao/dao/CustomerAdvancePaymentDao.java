package hibernate.dao.dao;

import java.time.LocalDate;
import java.util.List;

import hibernate.entities.CustomerAdvancePayment;

public interface CustomerAdvancePaymentDao {

	CustomerAdvancePayment getCustomerAdvanceById(long id);
	List<CustomerAdvancePayment>getAllCustomerAdvance();
	List<CustomerAdvancePayment>getCustomerAdvanceByCustomer(int id);
	List<CustomerAdvancePayment>getCustomerAdvanceByDate(LocalDate date);
	List<CustomerAdvancePayment>getCustomerAdvanceByDatePeriod(LocalDate start,LocalDate end);
	List<CustomerAdvancePayment>getCustomerAdvanceByDatePeriodAndCustomer(LocalDate start,LocalDate end,int customerid);
	int saveCustomerAdvance(CustomerAdvancePayment payment);
	
}
