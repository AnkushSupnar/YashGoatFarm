package hibernate.dao.dao;

import java.util.List;

import hibernate.entities.Item;

public interface ItemDao {
	public Item getItemById(int id);
	public Item getItemByName(String name);
	public List<Item> getAllItems();
	public List<String> getAllItemNames();
	
	public int saveItem(Item item);
	public float getCommision(String itemName);
	public List<String>getCuttingItemNames();
	public float getLabourCharges(String itemName);
	
	public List<String>getStockableItemNames();
}
