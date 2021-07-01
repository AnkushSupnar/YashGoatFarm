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
	private long id;
	private String itemname;
	private String unit;
	private double rate;
	private double quantity;
	private double amount;
	private double commision;
	@ManyToOne
	@JoinColumn(name="billno")
	private Bill bill;
	public Transaction() {
		super();
	}
	public Transaction(String itemname, String unit, double rate, double quantity, double amount,
			Bill bill,double commision) {
		super();
		this.itemname = itemname;
		this.unit = unit;
		this.rate = rate;
		this.quantity = quantity;
		this.amount = amount;
		this.commision = commision;
		this.bill = bill;
	}
	public double getCommision() {
		return commision;
	}
	public void setCommision(double commision) {
		this.commision = commision;
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
				+ quantity + ", amount=" + amount + ", commision=" + commision + ", bill=" + bill + "]";
	}
	public String toString2() {
		return  id + "|" + itemname + "|" + unit + "|" + rate + "|"
				+ quantity + "|" + amount + "|" + commision + "|" + bill ;
	}
	
	
}
