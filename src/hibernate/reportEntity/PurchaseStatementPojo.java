package hibernate.reportEntity;

import java.time.LocalDate;

public class PurchaseStatementPojo {
	int id;
	String particulars;
	double debit,credit,balance;
	LocalDate date;
	long billno;
	public PurchaseStatementPojo() {
		super();
	}
	public PurchaseStatementPojo(int id, String particulars, double debit, double credit, double balance,
			LocalDate date, long billno) {
		super();
		this.id = id;
		this.particulars = particulars;
		this.debit = debit;
		this.credit = credit;
		this.balance = balance;
		this.date = date;
		this.billno = billno;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getParticulars() {
		return particulars;
	}
	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}
	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public long getBillno() {
		return billno;
	}
	public void setBillno(long billno) {
		this.billno = billno;
	}
	@Override
	public String toString() {
		return "PurchaseStatementPojo [id=" + id + ", particulars=" + particulars + ", debit=" + debit + ", credit="
				+ credit + ", balance=" + balance + ", date=" + date + ", billno=" + billno + "]";
	}
	
}
