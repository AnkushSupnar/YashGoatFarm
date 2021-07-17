package hibernate.service.serviceImpl;

import java.util.List;
import hibernate.dao.dao.CounterStockDataDao;
import hibernate.dao.daoImpl.CounterStockDataDaoImpl;
import hibernate.entities.CounterStockData;
import hibernate.service.service.CounterStockDataService;

public class CounterStockDataServiceImpl implements CounterStockDataService {

	private CounterStockDataDao dao;
	public CounterStockDataServiceImpl()
	{
		this.dao = new CounterStockDataDaoImpl();
	}
	
	@Override
	public float getCounterItemStock(String itemname) {
		return dao.getCounterItemStock(itemname);
	}

	@Override
	public List<CounterStockData> getAllCounterStockData() {
		return dao.getAllCounterStockData();
	}

	@Override
	public int saveCounterStockdata(CounterStockData counterStockData) {
		return dao.saveCounterStockdata(counterStockData);
	}

	@Override
	public int updateQuantity(String itemname, float newqty) {
		return dao.updateQuantity(itemname, newqty);
	}

	@Override
	public CounterStockData getItemNameWiseCounterStockData(String itemname) {
		
		return dao.getItemNameWiseCounterStockData(itemname);
	}

	@Override
	public List<String> getAllCounterItemNames() {
		return dao.getAllCounterItemNames(); 
	}

}
