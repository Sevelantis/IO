package managers;

import interfaces.*;
import entities.*;

import java.util.List;

public class ProductManager implements IManager
{
	//attributes

	private static ProductManager instance;
	Product productList;

	//methods

	public void remove(int id_product)
	{
		// TODO
	}

	@Override
	public void add(Object obj)
	{
		// TODO
	}

	private void uploadFromDatabase()
	{
		//TODO intelligent DAO object
	}

	//getters

	@Override
	public Product get(int id_product)
	{
		//TODO
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

}