package group8.karina.business;

import java.util.List;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.application.Services;
import group8.karina.objects.User;
import group8.karina.persistence.DataAccessStub;

public class AccessUsers
{
	private DataAccessStub dataAccess;

	public AccessUsers()
	{
		dataAccess = Services.getDataAccess();
	}

	public List<User> getUsers()
	{
		return dataAccess.getUserSequential();
	}

	public void insertUser(User currentUser) throws DuplicateEntryException
	{
		if (currentUser.getUserName() == null || currentUser.getUserName().isEmpty())
		{
			throw new IllegalArgumentException("Null or empty value for user");
		}

		dataAccess.insertUser(currentUser);
	}
}