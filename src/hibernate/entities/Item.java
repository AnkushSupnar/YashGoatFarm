package hibernate.entities;
import javax.persistence.Column;
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
	@Column(nullable = true)
	String hsn;
	double rate;
	String unit;
	double commision;
	double labourCharges;
	public Item() {
		super();
	}
	public Item(String itemname, String hsn, double rate, String unit, double commision,double labourCharges) {
		super();
		this.itemname = itemname;
		this.hsn = hsn;
		this.rate = rate;
		this.unit = unit;
		this.commision = commision;
		this.labourCharges = labourCharges;
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
	public String getHsn() {
		return hsn;
	}
	public void setHsn(String hsn) {
		this.hsn = hsn;
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
	public double getLabourCharges() {
		return labourCharges;
	}
	public void setLabourCharges(double labourCharges) {
		this.labourCharges = labourCharges;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", itemname=" + itemname + ", hsn=" + hsn + ", rate=" + rate + ", unit=" + unit
				+ ", commision=" + commision + ", labourCharges=" + labourCharges + "]";
	}
	
	public String toString2() {
		return id + "|" + itemname + "|" + hsn + "|" + rate + "|" + unit
				+ "|" + commision + "|" + labourCharges;
	}
	
		
}
