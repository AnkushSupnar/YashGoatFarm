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
public class SalesmanCuttingCharges {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String saleman;
	LocalDate date;
	double cuttingCharges;
	@ManyToOne
	@JoinColumn(name = "bankid")
	Bank bank;
	@OneToMany(mappedBy = "salesmanCuttingChargesNo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<SalesmanCuttingTransaction> transaction;

	String bankTransaction;
	public SalesmanCuttingCharges() {
		super();
	}

	public SalesmanCuttingCharges(String saleman, LocalDate date, double cuttingCharges, Bank bank,
			List<SalesmanCuttingTransaction> transaction,String bankTransaction) {
		super();
		this.saleman = saleman;
		this.date = date;
		this.cuttingCharges = cuttingCharges;
		this.bank = bank;
		this.transaction = transaction;
		this.bankTransaction = bankTransaction;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSaleman() {
		return saleman;
	}

	public void setSaleman(String saleman) {
		this.saleman = saleman;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getCuttingCharges() {
		return cuttingCharges;
	}

	public void setCuttingCharges(double cuttingCharges) {
		this.cuttingCharges = cuttingCharges;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public List<SalesmanCuttingTransaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<SalesmanCuttingTransaction> transaction) {
		this.transaction = transaction;
	}
	

	public String getBankTransaction() {
		return bankTransaction;
	}

	public void setBankTransaction(String bankTransaction) {
		this.bankTransaction = bankTransaction;
	}

	@Override
	public String toString() {
		return "SalesmanCuttingCharges [id=" + id + ", saleman=" + saleman + ", date=" + date + ", cuttingCharges="
				+ cuttingCharges + ", bank=" + bank + ", transaction=" + transaction + ", bankTransaction="
				+ bankTransaction + "]";
	}

	

}
