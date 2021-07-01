package hibernate.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PurchaseTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String itemname;
	String unit;
	double rate;
	double quantity;
	double amount;
	@ManyToOne
	@JoinColumn(name = "billno")
	PurchaseInvoice invoice;

	public PurchaseTransaction() {
		super();
	}

	public PurchaseTransaction(String itemname, 
			String unit, double rate, double quantity, double amount,
			PurchaseInvoice invoice) {
		super();
		this.itemname = itemname;
		this.unit = unit;
		this.rate = rate;
		this.quantity = quantity;
		this.amount = amount;
		this.invoice = invoice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public PurchaseInvoice getInvoiceno() {
		return invoice;
	}

	public void setInvoiceno(PurchaseInvoice invoice) {
		this.invoice = invoice;
	}

	@Override
	public String toString() {
		return "PurchaseTransaction [id=" + id + ", itemname=" + itemname + ", unit=" + unit + ", rate=" + rate
				+ ", quantity=" + quantity + ", amount=" + amount + ", invoice=" + invoice + "]";
	}
	public String toString2() {
		return id + "|" + itemname + "|" + unit + "|" + rate
				+ "|" + quantity + "|" + amount + "|" + invoice;
	}

}
