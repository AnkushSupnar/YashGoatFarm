package hibernate.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class CuttingLabour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@ManyToOne
	@JoinColumn(name="labourId")
	Employee labour;
	
	double quantity;
	
	@ManyToOne
	@JoinColumn(name="cuttingTransactionId")
	CuttingTransaction transaction;
	
	double cuttingCharges;
	double paidCuttingCharges;
	
	public CuttingLabour() {
		super();
	}

	public CuttingLabour(Employee labour, 
			double quantity,
			CuttingTransaction transaction,
			double cuttingCharges,
			double paidCuttingCharges) {
		super();
		this.labour = labour;
		this.quantity = quantity;
		this.transaction = transaction;
		this.cuttingCharges = cuttingCharges;
		this.paidCuttingCharges = paidCuttingCharges;
	}

	public double getCuttingCharges() {
		return cuttingCharges;
	}

	public void setCuttingCharges(double cuttingCharges) {
		this.cuttingCharges = cuttingCharges;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee getLabour() {
		return labour;
	}

	public void setLabour(Employee labour) {
		this.labour = labour;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public CuttingTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(CuttingTransaction transaction) {
		this.transaction = transaction;
	}

	public double getPaidCuttingCharges() {
		return paidCuttingCharges;
	}

	public void setPaidCuttingCharges(double paidCuttingCharges) {
		this.paidCuttingCharges = paidCuttingCharges;
	}

	@Override
	public String toString() {
		return "CuttingLabour [id=" + id + ", labour=" + labour + ", quantity=" + quantity + ", transaction="
				+ transaction + ", cuttingCharges=" + cuttingCharges + ", paidCuttingCharges=" + paidCuttingCharges
				+ "]";
	}

	

	
	
}
