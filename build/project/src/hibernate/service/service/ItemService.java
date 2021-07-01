package hibernate.service.service;
import java.util.List;
import hibernate.entities.Item;

public interface ItemService{
	public Item getItemById(int id);
	public Item getItemByName(String name);
	public List<Item> getAllItems();
	public List<String> getAllItemNames();
	
	public int saveItem(Item item);
}
