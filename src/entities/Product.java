package entities;

import java.util.ArrayList;
import java.util.List;

public class Product extends Entity
{
	//attributes

	private String name;
	private float price;
	private int amountAvailable;
//	Item itemList;
	List<Item> itemList;

	//methods

	public Product(String name, float price)
	{
		this.name = name;
		this.price = price;
		itemList = new ArrayList<>();
	}

	public Item getItem(int id_item)
	{
		for (Item i: itemList) {
			if(i.getId() == id_item)
				return i;
		}
		return null;
	}

	public void addItem(Item item)
	{
		itemList.add(item);
	}

	public void removeItem(Item item)
	{
		itemList.remove(item);
	}

	//getters

	public int getAmountTotal()
	{
		// TODO
		return amountAvailable + 452151515;
	}

	public String getName()
	{
		return this.name;
	}

	public float getPrice()
	{
		return this.price;
	}

	//setters

	public void setName(String name)
	{
		this.name = name;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

}