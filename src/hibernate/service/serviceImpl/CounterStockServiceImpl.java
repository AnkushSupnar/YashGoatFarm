package hibernate.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import hibernate.dao.dao.CounterStockDao;
import hibernate.dao.daoImpl.CounterStockDaoImpl;
import hibernate.entities.CounterStock;
import hibernate.service.service.CounterStockService;

public class CounterStockServiceImpl implements CounterStockService{

	private CounterStockDao dao;
	public CounterStockServiceImpl()
	{
		this.dao = new CounterStockDaoImpl();
	}
	
	@Override
	public List<CounterStock> getAllCounterStock() {
		return dao.getAllCounterStock();
	}

	@Override
	public CounterStock getCounterStockById(long id) {
		return dao.getCounterStockById(id);
	}

	@Override
	public List<CounterStock> getCounterStockByDate(LocalDate date) {
		return dao.getCounterStockByDate(date);
	}

	@Override
	public List<CounterStock> getCounterStockByDatePeriod(LocalDate start, LocalDate end) {
		return dao.getCounterStockByDatePeriod(start, end);
	}

	@Override
	public int saveCounterStock(CounterStock stock) {
		return dao.saveCounterStock(stock);
	}

	@Override
	public void deleteTransaction(long stockid) {
		dao.deleteTransaction(stockid);
		
	}

	@Override
	public float getAvailableCounterStock(String itemname) {
	return dao.getAvailableCounterStock(itemname);
	}

}
