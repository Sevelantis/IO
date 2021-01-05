package entities;

import java.util.List;
import java.util.Vector;

//todo do produktu moze jednak niekoniecznie useful.
class ProductException extends Exception
{
	private static final long serialVersionUID = 1L;
	public ProductException(String message)
	{
		super(message);
	}
}

public class Product extends Entity
{
	//attributes

	private String name;
	private float price;
	private int amountAvailable;
	List<Item> itemList = new Vector<>();

	//methods

	public Product(String name, float price)
	{
		this.name = name;
		this.price = price;
	}

	public Item getItem(int id_item)
	{
		for(Item i : itemList)
			if(i.getId() == id_item)
				return i;
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

	public void removeItem(int id_item)
	{
		itemList.remove(id_item);
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

	@Override
	public String toString()
	{
		String str = "[Product: " + name + ", id: " + id + ", price: " + price + "]";
		System.out.println(str);
		return str;
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