package hibernate.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import hibernate.dao.dao.AdvancePaymentDao;
import hibernate.dao.daoImpl.AdvancePaymentDaoImpl;
import hibernate.entities.AdvancePayment;
import hibernate.service.service.AdvancePaymentService;

public class AdvancePaymentServiceImpl implements AdvancePaymentService {

	private AdvancePaymentDao dao;
	public AdvancePaymentServiceImpl() {
		this.dao = new AdvancePaymentDaoImpl();
	}
	@Override
	public AdvancePayment getAdvancePaymentById(long id) {
		return dao.getAdvancePaymentById(id);
	}

	@Override
	public List<AdvancePayment> getAllAdvancePayment() {
		return dao.getAllAdvancePayment();
	}

	@Override
	public List<AdvancePayment> getPartyWiseAdvancePayment(int partyId) {
		return dao.getDatePartyWiseAdvancePayment(null, partyId);
	}

	@Override
	public List<AdvancePayment> getDateWiseAdvancePayment(LocalDate date) {
		return dao.getDateWiseAdvancePayment(date);
	}

	@Override
	public List<AdvancePayment> getDatePartyWiseAdvancePayment(LocalDate date, int party) {
		return dao.getDatePartyWiseAdvancePayment(date, party);
	}

	@Override
	public int saveAdvancePayment(AdvancePayment payment) {
		return dao.saveAdvancePayment(payment);
	}
	@Override
	public double getPartyAdvancePayment(int partyId) {
		return dao.getPartyAdvancePayment(partyId);
	}

}
