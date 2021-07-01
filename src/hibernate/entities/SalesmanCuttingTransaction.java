package hibernate.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class SalesmanCuttingTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	LocalDate date;
	String itemName;
	double qty;
	double charges;
	@ManyToOne
	@JoinColumn(name="orderId")
	private CuttingOrder order;
	@ManyToOne
	@JoinColumn(name="SalesmanCuttingCharges")
	private SalesmanCuttingCharges salesmanCuttingChargesNo;
	public SalesmanCuttingTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SalesmanCuttingTransaction(LocalDate date, String itemName, double qty, double charges, CuttingOrder order,
			SalesmanCuttingCharges salesmanCuttingChargesNo) {
		super();
		this.date = date;
		this.itemName = itemName;
		this.qty = qty;
		this.charges = charges;
		this.order = order;
		this.salesmanCuttingChargesNo = salesmanCuttingChargesNo;
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
	public CuttingOrder getOrder() {
		return order;
	}
	public void setOrder(CuttingOrder order) {
		this.order = order;
	}
	public SalesmanCuttingCharges getSalesmanCuttingChargesNo() {
		return salesmanCuttingChargesNo;
	}
	public void setSalesmanCuttingChargesNo(SalesmanCuttingCharges salesmanCuttingChargesNo) {
		this.salesmanCuttingChargesNo = salesmanCuttingChargesNo;
	}
	@Override
	public String toString() {
		return "SalesmanCuttingTransaction [id=" + id + ", date=" + date + ", itemName=" + itemName + ", qty=" + qty
				+ ", charges=" + charges + ", order=" + order + ", salesmanCuttingChargesNo=" + salesmanCuttingChargesNo
				+ "]";
	}
	
}
