package hibernate.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="counterstocktransaction")
public class CounterStockTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String itemname;
	double oldqty;
	double newqty;
	double totalqty;
	@ManyToOne
	@JoinColumn(name="counterstockid")
	CounterStock counterstock;
	public CounterStockTransaction() {
		super();		
	}
	public CounterStockTransaction(String itemname, 
			double oldqty,
			double newqty,
			double totalqty,
			CounterStock counterstock) {
		super();
		this.itemname = itemname;
		this.oldqty = oldqty;
		this.newqty = newqty;
		this.totalqty = totalqty;
		this.counterstock = counterstock;
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
	public double getOldqty() {
		return oldqty;
	}
	public void setOldqty(double oldqty) {
		this.oldqty = oldqty;
	}
	public double getNewqty() {
		return newqty;
	}
	public void setNewqty(double newqty) {
		this.newqty = newqty;
	}
	public double getTotalqty() {
		return totalqty;
	}
	public void setTotalqty(double totalqty) {
		this.totalqty = totalqty;
	}
	public CounterStock getCounterstock() {
		return counterstock;
	}
	public void setCounterstock(CounterStock counterstock) {
		this.counterstock = counterstock;
	}
	@Override
	public String toString() {
		return "CounterStockTransaction [id=" + id + ", itemname=" + itemname + ", oldqty=" + oldqty + ", newqty="
				+ newqty + ", totalqty=" + totalqty + ", counterstock=" + counterstock + "]";
	}
	
	
}

