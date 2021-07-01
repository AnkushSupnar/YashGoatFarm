package hibernate.dao.dao;

import java.time.LocalDate;
import java.util.List;

import hibernate.entities.Bill;
import hibernate.entities.Transaction;



public interface BillDao {
	public Bill getBillByBillno(long billno);
	public List<Bill>getAllBills();
	public List<Bill>getDateWiseBill(LocalDate date);
	public List<Bill> getMonthWiseBill(LocalDate date);
	public List<Bill> getThisWeekBill();
	public List<Bill> getThisYearBill();
	public List<Transaction>getBillTransactions(int billno);
	public int saveBill(Bill bill);
	public long getNewBNillNo();
	public void deleteTransactions(long billno);
	
}
