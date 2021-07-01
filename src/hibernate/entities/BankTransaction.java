package hibernate.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BankTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String particulars;
	long reffid;
	double debit;
	double credit;
	int bankid;
	LocalDate date;
	public BankTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BankTransaction(String particulars,
			long reffid, 
			double debit, 
			double credit, 
			int bankid,
			LocalDate date) {
		super();
		this.particulars = particulars;
		this.reffid = reffid;
		this.debit = debit;
		this.credit = credit;
		this.bankid = bankid;
		this.date = date;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getParticulars() {
		return particulars;
	}
	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}
	public long getReffid() {
		return reffid;
	}
	public void setReffid(long reffid) {
		this.reffid = reffid;
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
	public int getBankid() {
		return bankid;
	}
	public void setBankid(int bankid) {
		this.bankid = bankid;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "BankTransaction [id=" + id + ", particulars=" + particulars + ", reffid=" + reffid + ", debit=" + debit
				+ ", credit=" + credit + ", bankid=" + bankid + ", date=" + date + "]";
	}
	public String toString2() {
		return  id + "|" + particulars + "|" + reffid + "|" + debit
				+ "|" + credit + "|" + bankid + "|" + date;
	}
}
