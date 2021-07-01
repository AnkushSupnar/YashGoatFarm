package hibernate.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String bankname;
	String accounttype;
	String ifsc;
	String branch;
	String accountno;
	double balance;
	public Bank() {
		super();
	}
	public Bank(String bankname, String accounttype, String ifsc, String branch, String accountno, double balance) {
		super();
		this.bankname = bankname;
		this.accounttype = accounttype;
		this.ifsc = ifsc;
		this.branch = branch;
		this.accountno = accountno;
		this.balance = balance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Bank [id=" + id + ", bankname=" + bankname + ", accounttype=" + accounttype + ", ifsc=" + ifsc
				+ ", branch=" + branch + ", accountno=" + accountno + ", balance=" + balance + "]";
	}
}
