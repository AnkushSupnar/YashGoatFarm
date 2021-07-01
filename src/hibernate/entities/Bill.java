package hibernate.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long billno;
	
	@ManyToOne
	@JoinColumn(name="customerid")
	Customer customer;
	
	LocalDate date;
	double nettotal;
	double transportingchrges;
	double otherchargs;
	
	@ManyToOne
	@JoinColumn(name="bankid")
	Bank bank;
	
	String recievedby;
	String recievedreff;
	
	@ManyToOne
	@JoinColumn(name="employeeid")
	Employee employee;
	
	@OneToMany(mappedBy = "bill",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Transaction>transaction;
	
	private double recivedamount;
	private double paidcommision;
	public Bill() {
		super();
	}
	public Bill(Customer customer, LocalDate date, double nettotal, double transportingchrges, double otherchargs,
			Bank bank, String recievedby, String recievedreff, Employee employee,
			List<Transaction> transaction,double recivedamount,double paidcommision) {
		super();
		this.customer = customer;
		this.date = date;
		this.nettotal = nettotal;
		this.transportingchrges = transportingchrges;
		this.otherchargs = otherchargs;
		this.bank = bank;
		this.recievedby = recievedby;
		this.recievedreff = recievedreff;
		this.employee = employee;
		this.transaction = transaction;
		this.recivedamount = recivedamount;
		this.paidcommision = paidcommision;
	}
	public long getBillno() {
		return billno;
	}
	public void setBillno(long billno) {
		this.billno = billno;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getNettotal() {
		return nettotal;
	}
	public void setNettotal(double nettotal) {
		this.nettotal = nettotal;
	}
	public double getTransportingchrges() {
		return transportingchrges;
	}
	public void setTransportingchrges(double transportingchrges) {
		this.transportingchrges = transportingchrges;
	}
	public double getOtherchargs() {
		return otherchargs;
	}
	public void setOtherchargs(double otherchargs) {
		this.otherchargs = otherchargs;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public double getPaidcommision() {
		return paidcommision;
	}
	public void setPaidcommision(double paidcommision) {
		this.paidcommision = paidcommision;
	}
	public String getRecievedby() {
		return recievedby;
	}
	public void setRecievedby(String recievedby) {
		this.recievedby = recievedby;
	}
	public String getRecievedreff() {
		return recievedreff;
	}
	public void setRecievedreff(String recievedreff) {
		this.recievedreff = recievedreff;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public List<Transaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public double getRecivedamount() {
		return recivedamount;
	}
	public void setRecivedamount(double recivedamount) {
		this.recivedamount = recivedamount;
	}
	@Override
	public String toString() {
		return "Bill [billno=" + billno + ", customer=" + customer + ", date=" + date + ", nettotal=" + nettotal
				+ ", transportingchrges=" + transportingchrges + ", otherchargs=" + otherchargs + ", bank=" + bank
				+ ", recievedby=" + recievedby + ", recievedreff=" + recievedreff + ", employee=" + employee
				+  ", recivedamount=" + recivedamount + ", paidcommision="
				+ paidcommision + "]";
	}
	
	public String toString2() {
		return billno + "|" + customer.getId() + "|" + date + "|" + nettotal
				+ "|" + transportingchrges + "|" + otherchargs + "|" + bank.getId()
				+ "|" + recievedby + "|" + recievedreff + "|" + employee.getId()
				+ "|" + recivedamount + "|"
				+ paidcommision;
	}
}
