package hibernate.dao.dao;

import java.time.LocalDate;
import java.util.List;

import hibernate.entities.CounterStock;
import hibernate.entities.CounterStockData;

public interface CounterStockDao {

	List<CounterStock> getAllCounterStock();
	CounterStock getCounterStockById(long id);
	List<CounterStock>getCounterStockByDate(LocalDate date);
	List<CounterStock>getCounterStockByDatePeriod(LocalDate start,LocalDate end);
	int saveCounterStock(CounterStock stock);
	void deleteTransaction(long stockid);
	float getAvailableCounterStock(String itemname);
	
	
}
