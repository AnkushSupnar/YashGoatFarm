package hibernate.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class PurchaseInvoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long billno;
	String invoiceNo;
	@ManyToOne
	@JoinColumn(name="partyid")
	PurchaseParty party;
	LocalDate date;
	double nettotal;
	double gst;
	double othercharges;
	double transportcharges;
	double grandtotal;
	double paid;

	@OneToMany(mappedBy = "invoice",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<PurchaseTransaction> transaction;
	@ManyToOne
	@JoinColumn(name="bankid")
	Bank bank;
	String bankreffno;
	public PurchaseInvoice() {
		super();
	}
	public PurchaseInvoice(String invoiceNo, PurchaseParty party, LocalDate date, double nettotal, double gst,
			double othercharges, double transportcharges, double grandtotal, List<PurchaseTransaction> transaction,
			Bank bank,String bankreffno,double paid) {
		super();
		this.invoiceNo = invoiceNo;
		this.party = party;
		this.date = date;
		this.nettotal = nettotal;
		this.gst = gst;
		this.othercharges = othercharges;
		this.transportcharges = transportcharges;
		this.grandtotal = grandtotal;
		this.transaction = transaction;
		this.bank = bank;
		this.bankreffno = bankreffno;
		this.paid = paid;
	}
	public double getPaid() {
		return paid;
	}
	public void setPaid(double paid) {
		this.paid = paid;
	}
	public String getBankreffno() {
		return bankreffno;
	}
	public void setBankreffno(String bankreffno) {
		this.bankreffno = bankreffno;
	}
	public long getBillno() {
		return billno;
	}
	public void setBillno(long billno) {
		this.billno = billno;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public PurchaseParty getParty() {
		return party;
	}
	public void setParty(PurchaseParty party) {
		this.party = party;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getNettotal() {
		return nettotal;
	}
	public void setNettotal(double nettotal) {
		this.nettotal = nettotal;
	}
	public double getGst() {
		return gst;
	}
	public void setGst(double gst) {
		this.gst = gst;
	}
	public double getOthercharges() {
		return othercharges;
	}
	public void setOthercharges(double othercharges) {
		this.othercharges = othercharges;
	}
	public double getTransportcharges() {
		return transportcharges;
	}
	public void setTransportcharges(double transportcharges) {
		this.transportcharges = transportcharges;
	}
	public double getGrandtotal() {
		return grandtotal;
	}
	public void setGrandtotal(double grandtotal) {
		this.grandtotal = grandtotal;
	}
	public List<PurchaseTransaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<PurchaseTransaction> transaction) {
		this.transaction = transaction;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	
	
	@Override
	public String toString() {
		return "PurchaseInvoice [billno=" + billno + ", invoiceNo=" + invoiceNo + ", party=" + party + ", date=" + date
				+ ", nettotal=" + nettotal + ", gst=" + gst + ", othercharges=" + othercharges + ", transportcharges="
				+ transportcharges + ", grandtotal=" + grandtotal + ", paid=" + paid + ", transaction=" + transaction
				+ ", bank=" + bank + ", bankreffno=" + bankreffno + "]";
	}
	public String toString2() {
		return  billno + "|" + invoiceNo + "|" + party + "|" + date
				+ "|" + nettotal + "|" + gst + "|" + othercharges + "|"
				+ transportcharges + "|" + grandtotal + "|" + paid + "|" + transaction
				+ "|" + bank + "|" + bankreffno;
	}
	
}
