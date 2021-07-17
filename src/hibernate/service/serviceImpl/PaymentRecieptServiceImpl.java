package hibernate.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import hibernate.dao.dao.PaymentRecieptDao;
import hibernate.dao.daoImpl.PaymentReceiptDaoImpl;
import hibernate.entities.PaymentReciept;
import hibernate.service.service.PaymentRecieptService;

public class PaymentRecieptServiceImpl implements PaymentRecieptService {

	private PaymentRecieptDao dao;
	public PaymentRecieptServiceImpl()
	{
		this.dao= new PaymentReceiptDaoImpl();
	}
	@Override
	public PaymentReciept getPaymentRecieptById(long id) {
		return dao.getPaymentRecieptById(id);
	}

	@Override
	public List<PaymentReciept> getAllPaymentReciept() {
		return dao.getAllPaymentReciept();
	}

	@Override
	public List<PaymentReciept> getDateWisePaymentReciept(LocalDate date) {
		return dao.getDateWisePaymentReciept(date);
	}

	@Override
	public List<PaymentReciept> getDatePeriodPaymentReciept(LocalDate start, LocalDate end) {
		return dao.getDatePeriodPaymentReciept(start, end);
	}

	@Override
	public List<PaymentReciept> getNameWisePaymentReciept(String name) {
		return dao.getNameWisePaymentReciept(name);
	}

	@Override
	public List<PaymentReciept> getBankWisePaymentReciept(int bankid) {
		return dao.getBankWisePaymentReciept(bankid);
	}

	@Override
	public int savePaymentReciept(PaymentReciept reciept) {
		return dao.savePaymentReciept(reciept);
	}

}
