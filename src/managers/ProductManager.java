package managers;

import interfaces.*;
import entities.*;

import java.util.List;
import java.util.Vector;

public class ProductManager implements IManager
{
	//attributes

	private static ProductManager instance;
	List<Product> productList = new Vector<>();

	//methods

	public void remove(int id_product)
	{
		productList.remove(id_product); // remove at index
	}

	@Override
	public void add(Object obj)
	{
		productList.add(	(Product)obj	);
	}

	@Override
	public Product get(int id_product)
	{
		for (Product p : productList)
			if(p.getId() == id_product)
				return p;
		return null;
	}

	public List<Product> search(String name, float price)
	{
		// TODO
		return null;
	}

	public static ProductManager getInstance()
	{
		if(instance == null) instance = new ProductManager();
		return instance;
	}

	//setters
	// .

	// methods handled by database -------------
	// .

	//DB
	private void uploadFromDatabase()
	{
		//my tylko robimy logike biznesową, a nie połączenie z bazą :)
		//TODO
	}

	//getters
}