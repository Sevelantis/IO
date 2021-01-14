package managers;

import entities.Client;
import interfaces.IManager;

import java.util.List;
import java.util.Vector;

/*
	@note	There is no remove client method available
 */
public class ClientManager implements IManager
{
	//attributes

	private static ClientManager instance;
	List<Client> clientList = new Vector<>();

	//methods

	@Override
	public void add(Object obj)
	{
		clientList.add((Client) obj);
	}

	//getters

	@Override
	public Client get(int id_client)
	{
		for (Client c: clientList) {
			if( c.getId() == id_client)
				return c;
		}
		return null;
	}

	public static ClientManager getInstance()
	{
		if(instance == null)
			instance = new ClientManager();
		return instance;
	}

	public List<Client> getClientList()
	{
		return this.clientList;
	}

	@Override
	public boolean updateDatabaseFromLocal() {
		return true;
	}

	@Override
	public boolean updateLocalFromDatabase() {
		return true;
	}

	// handled by database -------------
	// .
}