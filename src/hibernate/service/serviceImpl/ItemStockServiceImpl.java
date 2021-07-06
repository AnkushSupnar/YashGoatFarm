package hibernate.service.serviceImpl;

import java.util.List;

import hibernate.dao.dao.ItemStockDao;
import hibernate.dao.daoImpl.ItemStockDaoImpl;
import hibernate.entities.ItemStock;
import hibernate.service.service.ItemStockService;

public class ItemStockServiceImpl implements ItemStockService {

	private ItemStockDao dao;
	public ItemStockServiceImpl() {
		this.dao = new ItemStockDaoImpl();
	}
	@Override
	public ItemStock getItemStockById(long id) {
		return dao.getItemStockById(id);
	}

	@Override
	public ItemStock getItemStockByItemName(String name) {
		return dao.getItemStockByItemName(name);
	}

	@Override
	public int saveItemStock(ItemStock stock) {
		return dao.saveItemStock(stock);
	}

	@Override
	public List<ItemStock> getAllItemStock() {
		return dao.getAllItemStock();
	}

	@Override
	public double getItemStock(String name) {
		return dao.getItemStock(name);
	}
	@Override
	public List<String> getItemNames() {
		return dao.getItemNames();
	}
	@Override
	public int reduceItemStock(String itemname, double qty) {
		return dao.reduceItemStock(itemname, qty);
	}

}
