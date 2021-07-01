package hibernate.reportEntity;

import java.time.LocalDate;

public class ReportTable {
	int srNo;
	LocalDate date;
	String salesman;
	String itemName;
	double qty;
	double rate;
	double amount;
	double commision;
	double labour;
	double remain;
	public ReportTable() {
		super();
	}
	public ReportTable(int srNo, LocalDate date, String salesman, String itemName, double qty, double rate,
			double amount, double commision, double labour, double remain) {
		super();
		this.srNo = srNo;
		this.date = date;
		this.salesman = salesman;
		this.itemName = itemName;
		this.qty = qty;
		this.rate = rate;
		this.amount = amount;
		this.commision = commision;
		this.labour = labour;
		this.remain = remain;
	}
	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
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
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getCommision() {
		return commision;
	}
	public void setCommision(double commision) {
		this.commision = commision;
	}
	public double getLabour() {
		return labour;
	}
	public void setLabour(double labour) {
		this.labour = labour;
	}
	public double getRemain() {
		return remain;
	}
	public void setRemain(double remain) {
		this.remain = remain;
	}
	@Override
	public String toString() {
		return "ReportTable [srNo=" + srNo + ", date=" + date + ", salesman=" + salesman + ", itemName=" + itemName
				+ ", qty=" + qty + ", rate=" + rate + ", amount=" + amount + ", commision=" + commision + ", labour="
				+ labour + ", remain=" + remain + "]";
	}
}