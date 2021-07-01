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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "counterstock")
public class CounterStock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	LocalDate date;
	@OneToMany(mappedBy = "counterstock",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<CounterStockTransaction>transaction = new ArrayList<>();
	public CounterStock() {
		super();		
	}
	public CounterStock(LocalDate date, List<CounterStockTransaction> transaction) {
		super();
		this.date = date;
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
	public List<CounterStockTransaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<CounterStockTransaction> transaction) {
		this.transaction = transaction;
	}
	@Override
	public String toString() {
		return "CounterStock [id=" + id + ", date=" + date + ", transaction=" + transaction + "]";
	}	
}

