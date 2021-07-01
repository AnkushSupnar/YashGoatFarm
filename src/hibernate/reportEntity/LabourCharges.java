package hibernate.reportEntity;

import java.time.LocalDate;

public class LabourCharges {
	long id;
	LocalDate date;
	String item;
	double qty;
	double labourcharges;
	double paidLabourCharges;
	public LabourCharges() {
		super();		
	}
	public LabourCharges(long id, LocalDate date, String item, double qty, double labourcharges,
			double paidLabourCharges) {
		super();
		this.id = id;
		this.date = date;
		this.item = item;
		this.qty = qty;
		this.labourcharges = labourcharges;
		this.paidLabourCharges = paidLabourCharges;
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
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public double getLabourcharges() {
		return labourcharges;
	}
	public void setLabourcharges(double labourcharges) {
		this.labourcharges = labourcharges;
	}
	public double getPaidLabourCharges() {
		return paidLabourCharges;
	}
	public void setPaidLabourCharges(double paidLabourCharges) {
		this.paidLabourCharges = paidLabourCharges;
	}
	@Override
	public String toString() {
		return "LabourCharges [id=" + id + ", date=" + date + ", item=" + item + ", qty=" + qty + ", labourcharges="
				+ labourcharges + ", paidLabourCharges=" + paidLabourCharges + "]";
	}
	
}
