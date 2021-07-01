package hibernate.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String itemname;
	String unit;
	double rate;
	double quantity;
	double amount;
	@ManyToOne
	@JoinColumn(name="billno")
	Bill bill;
	public Transaction() {
		super();
	}
	public Transaction(String itemname, String unit, double rate, double quantity, double amount,
			Bill bill) {
		super();
		this.itemname = itemname;
		this.unit = unit;
		this.rate = rate;
		this.quantity = quantity;
		this.amount = amount;
		this.bill = bill;
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
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", itemname=" + itemname + ", unit=" + unit + ", rate=" + rate + ", quantity="
				+ quantity + ", amount=" + amount + ", bill=" + bill + "]";
	}
	
}
