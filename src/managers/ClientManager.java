package managers;

import interfaces.*;
import entities.*;
/*
	@note	There is no remove client method available
 */
public class ClientManager implements IManager
{
	//attributes

	private static ClientManager instance;
	Client clientList;

	//methods

	@Override
	public void add(Object obj)
	{
		// TODO
	}

	//getters

	@Override
	public Client get(int id_client)
	{
		// TODO
		return null;
	}

	public static ClientManager getInstance()
	{
		if(instance == null) instance = new ClientManager();
		return instance;
	}

	// handled by database -------------
	// .
}