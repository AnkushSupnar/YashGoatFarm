package hibernate.entities;

import java.time.LocalDate;
import java.util.ArrayList;
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
public class CuttingOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	LocalDate date;
	
	@ManyToOne
	@JoinColumn(name="employeeId")
	Employee employee;
	
	@ManyToOne
	@JoinColumn(name="customerId")
	Customer customer;
	
//	@ManyToOne
//	@JoinColumn(name="labourId")
//	Employee labour;
	
	
	
	@OneToMany(mappedBy = "cuttingOrder",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<CuttingTransaction> transaction = new ArrayList<CuttingTransaction>();

	public CuttingOrder() {
		super();
	}

	public CuttingOrder(LocalDate date,
			Employee employee,
			Customer customer,
			List<CuttingTransaction> transaction) {
		super();
		this.date = date;
		this.employee = employee;
		this.customer = customer;
		//this.labour = labour;
		//this.transaction.clear();
		this.transaction = transaction;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

//	public Employee getLabour() {
//		return labour;
//	}

//	public void setLabour(Employee labour) {
//		this.labour = labour;
//	}

	public List<CuttingTransaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<CuttingTransaction> transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "CuttingOrder [id=" + id + ", date=" + date + ", employee=" + employee + ", customer=" + customer
				+", transaction=" + transaction + "]";
	}

	
	
}
