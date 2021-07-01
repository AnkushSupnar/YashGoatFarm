package hibernate.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import hibernate.dao.dao.BillDao;
import hibernate.dao.daoImpl.BillDaoImpl;
import hibernate.entities.Bill;
import hibernate.entities.Transaction;
import hibernate.service.service.BillService;

public class BillServiceImpl implements BillService {

	private BillDao dao;
	public BillServiceImpl() {
		this.dao = new BillDaoImpl();
	}
	@Override
	public Bill getBillByBillno(long billno) {
		return dao.getBillByBillno(billno);
	}

	@Override
	public List<Bill> getAllBills() {
		return dao.getAllBills();
	}

	@Override
	public List<Bill> getDateWiseBill(LocalDate date) {
		return dao.getDateWiseBill(date);
	}

	@Override
	public List<Bill> getMonthWiseBill(LocalDate date) {
		return dao.getMonthWiseBill(date);
	}

	@Override
	public int saveBill(Bill bill) {
		return dao.saveBill(bill);
	}
	@Override
	public List<Transaction> getBillTransactions(int billno) {
		return dao.getBillTransactions(billno); 
	}
	@Override
	public long getNewBNillNo() {
		return dao.getNewBNillNo();
	}
	@Override
	public void deleteTransactions(long billno) {
		dao.deleteTransactions(billno);
		
	}
	@Override
	public List<Bill> getThisWeekBill() {
		return dao.getThisWeekBill();
	}
	@Override
	public List<Bill> getThisYearBill() {
		return dao.getThisYearBill();
	}
}
