package hibernate.dao.dao;

import java.util.List;

import hibernate.entities.ItemStock;

public interface ItemStockDao {
	public ItemStock getItemStockById(long id);
	public ItemStock getItemStockByItemName(String name);
	public int saveItemStock(ItemStock stock);
	public List<ItemStock> getAllItemStock();
	public double getItemStock(String name);
	public List<String>getItemNames();
}
