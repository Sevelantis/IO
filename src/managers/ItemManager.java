package managers;

import interfaces.*;
import entities.*;

public class ItemManager implements IManager
{
	//attributes

	private static ItemManager instance;
	Item itemList;

	//methods

	public void remove(int id_item)
	{
		//TODO
	}

	@Override
	public void add(Object obj)
	{

	}

	//getters

	public Item get(int id_item)
	{
		//TODO
		return null;
	}


	public static ItemManager getInstance()
	{
		if(instance == null) instance = new ItemManager();
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