package hibernate.entities;

import java.time.LocalDate;
import java.util.ArrayList;
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
public class LabourCharges {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	LocalDate date;
	
	@ManyToOne
	@JoinColumn(name="labourId")
	Employee labour;
	
	@ManyToOne
	@JoinColumn(name="bankId")
	Bank bank;
	String bankReffNo;
	double amount;
	
	@OneToMany(mappedBy = "labourCharges",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<LabourChargesTransaction> transaction = new ArrayList<LabourChargesTransaction>();

	public LabourCharges() {
		super();
	}

	public LabourCharges(LocalDate date, Employee labour, Bank bank, double amount,
			List<LabourChargesTransaction> transaction,String bankReffNo) {
		super();
		this.date = date;
		this.labour = labour;
		this.bank = bank;
		this.amount = amount;
		this.bankReffNo = bankReffNo;
		this.transaction = transaction;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Employee getLabour() {
		return labour;
	}

	public void setLabour(Employee labour) {
		this.labour = labour;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public List<LabourChargesTransaction> getTransaction() {
		return transaction;
	}
	
	public String getBankReffNo() {
		return bankReffNo;
	}

	public void setBankReffNo(String bankReffNo) {
		this.bankReffNo = bankReffNo;
	}

	public void setTransaction(List<LabourChargesTransaction> transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "LabourCharges [id=" + id + ", date=" + date + ", labour=" + labour + ", bank=" + bank + ", bankReffNo="
				+ bankReffNo + ", amount=" + amount + ", transaction=" + transaction + "]";
	}

	
}
