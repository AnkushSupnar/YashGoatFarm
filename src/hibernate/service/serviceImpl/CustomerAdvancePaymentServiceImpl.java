package hibernate.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import hibernate.dao.dao.CustomerAdvancePaymentDao;
import hibernate.dao.daoImpl.CustomerAdvancePaymentDaoImpl;
import hibernate.entities.CustomerAdvancePayment;
import hibernate.service.service.CustomerAdvancePaymentService;

public class CustomerAdvancePaymentServiceImpl implements CustomerAdvancePaymentService {

	private CustomerAdvancePaymentDao dao;
	public CustomerAdvancePaymentServiceImpl()
	{
		this.dao = new CustomerAdvancePaymentDaoImpl();
	}
	@Override
	public CustomerAdvancePayment getCustomerAdvanceById(long id) {
		return dao.getCustomerAdvanceById(id);
	}

	@Override
	public List<CustomerAdvancePayment> getAllCustomerAdvance() {
		return dao.getAllCustomerAdvance();
	}

	@Override
	public List<CustomerAdvancePayment> getCustomerAdvanceByCustomer(int id) {
		return dao.getCustomerAdvanceByCustomer(id);
	}

	@Override
	public List<CustomerAdvancePayment> getCustomerAdvanceByDate(LocalDate date) {
		return dao.getCustomerAdvanceByDate(date);
	}

	@Override
	public List<CustomerAdvancePayment> getCustomerAdvanceByDatePeriod(LocalDate start, LocalDate end) {
		return dao.getCustomerAdvanceByDatePeriod(start, end);
	}

	@Override
	public List<CustomerAdvancePayment> getCustomerAdvanceByDatePeriodAndCustomer(LocalDate start, LocalDate end,
			int customerid) {
		return dao.getCustomerAdvanceByDatePeriodAndCustomer(start, end, customerid);
	}

	@Override
	public int saveCustomerAdvance(CustomerAdvancePayment payment) {
		return dao.saveCustomerAdvance(payment);
	}

}
