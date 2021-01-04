package managers;

import entities.Item;
import entities.Product;
import interfaces.IManager;

import java.util.List;
import java.util.Vector;

public class ItemManager implements IManager
{
	//attributes

	private static ItemManager instance;
	List<Item> itemList = new Vector<>();

	//methods

	public void remove(int id_item)
	{
		for (Item item:	itemList) {
			if(item.getId() == id_item)
			{
				itemList.remove(item);
				break;
			}
		}
	}

	@Override
	public void add(Object obj)
	{
		itemList.add((Item) obj);
	}

	//getters

	public Item get(int id_item)
	{
		for (Item item: itemList) {
			if( item.getId() == id_item)
				return item;
		}
		return null;
	}


	public static ItemManager getInstance()
	{
		if(instance == null)
			instance = new ItemManager();
		return instance;
	}

	// methods handled by database -------------

	public void buildItems(Product product)
	{
		// handled by database
	}

	public void destroyItems(Product product)
	{
		// handled by database
	}

}