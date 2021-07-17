package hibernate.dao.dao;

import java.util.List;

import hibernate.entities.CounterStockData;

public interface CounterStockDataDao {

	float getCounterItemStock(String itemname);
	List<CounterStockData>getAllCounterStockData();
	int saveCounterStockdata(CounterStockData counterStockData);
	int updateQuantity(String itemname,float newqty);
	CounterStockData getItemNameWiseCounterStockData(String itemname);
	List<String>getAllCounterItemNames();
}
