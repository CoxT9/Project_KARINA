package group8.karina.business;

import java.util.List;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.Exceptions.unfoundResourceException;
import group8.karina.application.DatabaseService;
import group8.karina.objects.User;
import group8.karina.persistence.Database;

public class AccessUsers
{
	private Database dataAccess;

	public AccessUsers()
	{
		dataAccess = DatabaseService.getDataAccess();
	}

	public List<User> getUsers()
	{
		return dataAccess.getAllUsers();
	}

	public User getUserByID(int uID) { return dataAccess.getUserById(uID); }

	public void insertUser(User currentUser) throws DuplicateEntryException
	{
		if (currentUser.getUserName() == null || currentUser.getUserName().isEmpty())
		{
			throw new IllegalArgumentException("Null or empty value for user");
		}

		dataAccess.insertUser(currentUser);
	}

	public void deleteUserById(int userId)
	{
		dataAccess.deleteUserById(userId);
	}

	public void updateUser(User user) throws unfoundResourceException
	{
		dataAccess.updateUser(user);
	}
}