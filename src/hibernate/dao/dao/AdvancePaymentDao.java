package hibernate.dao.dao;

import java.time.LocalDate;
import java.util.List;

import hibernate.entities.AdvancePayment;

public interface AdvancePaymentDao {

	AdvancePayment getAdvancePaymentById(long id);
	List<AdvancePayment>getAllAdvancePayment();
	List<AdvancePayment> getPartyWiseAdvancePayment(int partyId);
	List<AdvancePayment>getDateWiseAdvancePayment(LocalDate date);
	List<AdvancePayment>getDatePartyWiseAdvancePayment(LocalDate date,int party);
	int saveAdvancePayment(AdvancePayment payment);
	double getPartyAdvancePayment(int partyId);
	
}
