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
		for (Product c: productList) {
			if( c.getId() == id_product)
			{
				productList.remove(c);
				break;
			}
		}
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

	/*
		* Metoda wyszukiwania na podstawie dwóch parametrów
	*/
	public List<Product> search(String name, float price)
	{
		List<Product> interest = new Vector<>();
		if( price == 0)
		{
			for (Product c: productList) {
				if( c.getName().equals(name))
					interest.add(c);
			}
		}
		else
			for (Product c: productList) {
				if( c.getPrice() == price)
					interest.add(c);
			}
		return interest;
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