package group8.karina.tests.integrationTests.businessIntegrationTests;

import org.junit.After;
import org.junit.Before;

import java.util.List;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.Exceptions.unfoundResourceException;
import group8.karina.application.DatabaseService;
import group8.karina.business.AccessUsers;
import group8.karina.objects.User;
import group8.karina.persistence.Database;
import group8.karina.tests.testHelpers.TestDataAccessObject;

public class AccessUsersIntegrationTests extends junit.framework.TestCase
{
	private Database dataAccess;
	private AccessUsers accessUsers;

	@Before
	public void setUp()
	{
		dataAccess = new TestDataAccessObject("db");
		DatabaseService.setDatabase(dataAccess);
		DatabaseService.setDBPathName("src/main/assets/db/");
		DatabaseService.openDataAccess();
		dataAccess = DatabaseService.getDataAccess();
		accessUsers = new AccessUsers();
	}

	@After
	public void tearDown()
	{
		DatabaseService.closeDataAccess();
	}

	public void test_insert_user_inserts_user()
	{
		User expectedUser = new User("bob");
		User actualUser;

		try
		{
			accessUsers.insertUser(expectedUser);
		} catch (DuplicateEntryException e)
		{
			fail("inserting a valid user should not throw an exception");
		}

		actualUser = dataAccess.getUserByName(expectedUser.getUserName());

		assertNotNull(actualUser);
		assertEquals("bob", actualUser.getUserName());
	}

	public void testInsertUserThrowsExceptionWithNullUsername()
	{
		User expectedUser = new User(null);
		try
		{
			accessUsers.insertUser(expectedUser);
			fail("insert user should throw exception when name is null");
		} catch (IllegalArgumentException iae)
		{
			//this is what we want
		} catch (Exception e)
		{
			fail("Expected IllegalArgumentException when inserting null name");
		}
	}

	public void testInsertUserThrowsExceptionWithEmptyUsername()
	{
		User expectedUser = new User("");
		try
		{
			accessUsers.insertUser(expectedUser);
			fail("insert user should throw exception when name is empty");
		} catch (IllegalArgumentException iae)
		{
			//this is what we want
		} catch (Exception e)
		{
			fail("Expected IllegalArgumentException when inserting empty name");
		}
	}

	public void testGetUsersReturnsRightUsers()
	{
		try
		{
			dataAccess.insertUser(new User("billybob"));
		} catch (Exception e)
		{
			fail("insert user should not throw an exception for valid input");
		}

		List<User> users = accessUsers.getUsers();

		assertEquals(6, users.size()); //this is 6 because we have some seed data in the database and out inserted data
	}

	public void testDeleteUserByIdDeletes()
	{
		User u = new User("Bob");

		try
		{
			dataAccess.insertUser(u);
		} catch (Exception e)
		{
			fail("This should not throw an exception");
		}

		u = dataAccess.getUserByName("Bob");

		accessUsers.deleteUserById(u.getUserID());
		assertNull(dataAccess.getUserByName(u.getUserName()));
	}

	public void testUpdateUserUpdatesUser()
	{
		User u = new User("Bob");
		String expectedUserName = "Joe";

		try
		{
			dataAccess.insertUser(u);
		} catch (Exception e)
		{
			fail("This should not throw an exception");
		}

		u = dataAccess.getUserByName("Bob");
		u.setUserName(expectedUserName);

		try
		{
			accessUsers.updateUser(u);
		} catch (unfoundResourceException e)
		{
			fail("This should not throw an exception");
		}

		assertEquals("Joe", dataAccess.getUserById(u.getUserID()).getUserName());
	}


}
