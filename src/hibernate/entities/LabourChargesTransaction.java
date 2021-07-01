package hibernate.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class LabourChargesTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	LocalDate date;
	
	String itemName;
	double qty;
	double charges;
	double paidLabourCharges;
	
	@ManyToOne
	@JoinColumn(name = "labourChargesId")
	LabourCharges labourCharges;

	public LabourChargesTransaction() {
		super();
	}

	public LabourChargesTransaction(LocalDate date, String itemName, double qty, double charges, double paidLabourCharges,
			LabourCharges labourCharges) {
		super();
		this.date = date;
		this.itemName = itemName;
		this.qty = qty;
		this.charges = charges;
		this.paidLabourCharges = paidLabourCharges;
		this.labourCharges = labourCharges;
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

	public String getItem() {
		return itemName;
	}

	public void setItem(String itemName) {
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

	public double getPaidLabourCharges() {
		return paidLabourCharges;
	}

	public void setPaidLabourCharges(double paidLabourCharges) {
		this.paidLabourCharges = paidLabourCharges;
	}

	public LabourCharges getLabourCharges() {
		return labourCharges;
	}

	public void setLabourCharges(LabourCharges labourCharges) {
		this.labourCharges = labourCharges;
	}

	@Override
	public String toString() {
		return "LabourChargesTransaction [id=" + id + ", date=" + date + ", itemName=" + itemName + ", qty=" + qty
				+ ", charges=" + charges + ", paidLabourCharges=" + paidLabourCharges + ", labourCharges="
				+ labourCharges + "]";
	}
	
}
