package hibernate.reportEntity;

import java.time.LocalDate;

public class SalesmanCuttingChargesTrPojo {
	long id;
	LocalDate date;
	String itemName;
	double qty;
	double charges;
	public SalesmanCuttingChargesTrPojo() {
		super();		
	}
	public SalesmanCuttingChargesTrPojo(long id, LocalDate date, String itemName, double qty, double charges) {
		super();
		this.id = id;
		this.date = date;
		this.itemName = itemName;
		this.qty = qty;
		this.charges = charges;
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
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public double getCharges() {
		return charges;
	}
	public void setCharges(double charges) {
		this.charges = charges;
	}
	@Override
	public String toString() {
		return "SalesmanCuttingChargesTrPojo [id=" + id + ", date=" + date + ", itemName=" + itemName + ", qty=" + qty
				+ ", charges=" + charges + "]";
	}
	
}
