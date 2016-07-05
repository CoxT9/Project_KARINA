package group8.karina.tests.integrationTests.persistenceIntegrationTests;

import org.junit.After;
import org.junit.Before;

import java.util.Date;
import java.util.List;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.Exceptions.unfoundResourceException;
import group8.karina.application.DatabaseService;
import group8.karina.objects.Category;
import group8.karina.objects.Transaction;
import group8.karina.objects.User;
import group8.karina.tests.testHelpers.TestDataAccessObject;
import group8.karina.persistence.Database;

import static org.junit.Assert.assertNotEquals;


public class DataAccessObjectTests extends junit.framework.TestCase
{
	Database dataAccess;

	@Before
	public void setUp()
	{
		dataAccess = new TestDataAccessObject("db");
		DatabaseService.setDatabase(dataAccess);
		DatabaseService.openDataAccess();
		dataAccess = DatabaseService.getDataAccess();
	}

	@After
	public void tearDown()
	{
		dataAccess.close();
	}

	public void testGetUsers()
	{
		List<User> users = dataAccess.getAllUsers();
		assertEquals(users.get(1).getUserName(), "Jon");
		assertEquals(users.get(2).getUserName(), "Aria");
		assertEquals(dataAccess.getUserByName("Default").getUserID(), 1);
		assertEquals(dataAccess.getUserById(4).getUserName(), "Bran");

		assertNull(dataAccess.getUserById(0));
		assertNull(dataAccess.getUserByName("Jorah the explorah"));

	}

	public void testInsertUsers()
	{
		User newUser = new User("tester");
		try
		{
			dataAccess.insertUser(newUser);
		} catch (DuplicateEntryException e)
		{
			fail("inserting a valid user should not throw an exception");
		}

		try
		{
			dataAccess.insertUser(newUser);
			fail("inserting a duplicate user should throw an exception");
		} catch (DuplicateEntryException e)
		{
			//we want this
		}
		newUser = null;
		newUser = dataAccess.getUserByName("tester");
		assertEquals(newUser.getUserName(), "tester");
		assertNotNull(newUser);
		dataAccess.deleteUserById(newUser.getUserID());
		newUser = null;
		assertNull(dataAccess.getUserByName("tester"));

	}

	public void testChangeUsers()
	{
		int totalUsers = dataAccess.getAllUsers().size();
		User user = new User(1, "NotDefault");

		assertNull(dataAccess.getUserByName("NotDefault"));
		try
		{
			dataAccess.updateUser(user);
		} catch (unfoundResourceException e)
		{
			fail("User 1 should be in the database");
		}

		user = null;
		user = dataAccess.getUserById(1);
		assertEquals(user.getUserName(), "NotDefault");

		assertNull(dataAccess.getUserByName("Default"));
		user = new User(1, "Default");
		try
		{
			dataAccess.updateUser(user);
		} catch (unfoundResourceException e)
		{
			fail("User 1 should still be in the database");
		}

		dataAccess.deleteUserById(-1);
		assertEquals(totalUsers, dataAccess.getAllUsers().size());

	}

	public void testGetCategories()
	{
		List<Category> cat = dataAccess.getAllCategories();
		assertEquals(cat.get(2).getCategoryName(), "groceries");
		assertEquals(cat.get(3).getCategoryName(), "weapons");
		assertEquals(dataAccess.getCategoryByNameAndIsExpense("Default", true).getCategoryID(), 1);
		assertEquals(dataAccess.getCategoryById(4).getCategoryName(), "entertainment");
		cat = dataAccess.getIncomeCategories();
		assertEquals(cat.get(0).getCategoryName(), "Default");
		assertEquals(cat.size(), 2);
		cat = dataAccess.getExpenseCategories();
		assertEquals(cat.get(0).getCategoryName(), "Default");
		assertEquals(cat.size(), 4);
		assertNull(dataAccess.getCategoryById(-1));
		assertNull(dataAccess.getCategoryByNameAndIsExpense("groceries", false));
	}

	public void testInsertCategories()
	{
		Category newCat = new Category("tester", true);
		try
		{
			dataAccess.insertCategory(newCat);
		} catch (DuplicateEntryException e)
		{
			fail("inserting a valid category should not throw an exception");
		}

		try
		{
			dataAccess.insertCategory(newCat);
			fail("inserting a duplicate category should not throw an exception");
		} catch (DuplicateEntryException e)
		{
			//we want this
		}
		newCat = null;
		newCat = dataAccess.getCategoryByNameAndIsExpense("tester", true);
		assertNotNull(newCat);
		dataAccess.deleteCategoryById(newCat.getCategoryID());
		newCat = null;
		assertNull(dataAccess.getCategoryByNameAndIsExpense("tester", true));
	}

	public void testGetTransactions()
	{
		List<Transaction> trans = dataAccess.getTransactionsByType(true);
		assertEquals(trans.get(0).getUserID(), 1);
		assertEquals(trans.get(1).getAmount(), 30.0, .1);
		assertNotEquals(trans.size(), 1);

		trans = dataAccess.getTransactionsByType(false);
		assertEquals(trans.get(0).getCategoryID(), 5);
		assertEquals(trans.size(), 2);

		assertEquals(dataAccess.getTransactionByID(1).getCategoryID(), 1);

		assertNull(dataAccess.getTransactionByID(0));

	}

	public void testInsertDeleteTransactions()
	{
		Transaction newTrans = new Transaction(new Date(), 1, true, 39.95, 1, "comment");

		assertEquals(dataAccess.getTransactionsByType(true).size(), 4);
		dataAccess.insertTransaction(newTrans);
		assertEquals(dataAccess.getTransactionsByType(true).size(), 5);
		dataAccess.deleteTransactionsByUserID(1);
		assertEquals(dataAccess.getTransactionsByType(true).size(), 3);

		dataAccess.insertTransaction(newTrans);
		assertEquals(dataAccess.getTransactionsByType(true).size(), 4);
		dataAccess.deleteTransactionsByCategoryID(1);
		assertEquals(dataAccess.getTransactionsByType(true).size(), 2);

	}

	public void testChangeTransactions()
	{
		Transaction oldTrans = dataAccess.getTransactionByID(1);
		Transaction newTrans = new Transaction(1, new Date(), 3, true, 49.8, 3, "hi");

		try
		{
			dataAccess.updateTransaction(newTrans);
		} catch (unfoundResourceException e)
		{
			fail("Transaction 1 should be in the database");
		}

		newTrans = null;
		newTrans = dataAccess.getTransactionByID(1);
		assertNotEquals(newTrans.getAmount(), oldTrans.getAmount());

		try
		{
			dataAccess.updateTransaction(oldTrans);
		} catch (unfoundResourceException e)
		{
			fail("Transaction 1 should still be in the database");
		}

		assertEquals(dataAccess.getTransactionsByType(true).size(), 4);
		oldTrans = new Transaction(new Date(), 3, true, 49.8, 3, "hi");
		dataAccess.insertTransaction(oldTrans);
		assertEquals(dataAccess.getTransactionsByType(true).size(), 5);
		dataAccess.unassignTransactionsByCategoryID(3);
		assertEquals(dataAccess.getTransactionsByType(true).get(2).getCategoryID(), 2);
		assertEquals(dataAccess.getTransactionsByType(true).get(2).getUserID(), 3);
		dataAccess.deleteTransactionsByUserID(3);

		assertEquals(dataAccess.getTransactionsByType(true).size(), 3);
		oldTrans = new Transaction(new Date(), 3, true, 49.8, 3, "hi");
		dataAccess.insertTransaction(oldTrans);
		assertEquals(dataAccess.getTransactionsByType(true).size(), 4);
		dataAccess.unassignTransactionsByUserID(3);
		assertEquals(dataAccess.getTransactionsByType(true).get(2).getUserID(), 2);
		assertEquals(dataAccess.getTransactionsByType(true).get(2).getCategoryID(), 1);
		dataAccess.deleteTransactionsByCategoryID(3);
		assertEquals(dataAccess.getTransactionsByType(true).size(), 3);


	}
}
