package hibernate.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String itemname;
	double rate;
	String unit;
	double commision;
	public Item() {
		super();
	}
	public Item(String itemname, double rate, String unit, double commision) {
		super();
		this.itemname = itemname;
		this.rate = rate;
		this.unit = unit;
		this.commision = commision;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getCommision() {
		return commision;
	}
	public void setCommision(double commision) {
		this.commision = commision;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", itemname=" + itemname + ", rate=" + rate + ", unit=" + unit + ", commision="
				+ commision + "]";
	}
	
}
