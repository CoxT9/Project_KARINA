package group8.karina.tests.persistenceTests;

import org.junit.After;
import org.junit.Before;

import java.util.Date;
import java.util.List;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.Exceptions.unfoundResourceException;
import group8.karina.objects.Category;
import group8.karina.objects.Transaction;
import group8.karina.objects.User;
import group8.karina.tests.testHelpers.TestDataAccessObject;
import group8.karina.persistence.Database;

import static org.junit.Assert.assertNotEquals;


public class DataAccessObjectTests extends junit.framework.TestCase
{
	Database test;

	@Before
	public void setUp()
	{
		test = new TestDataAccessObject("db");
		test.open("src/test/java/database/db");
	}

	@After
	public void tearDown()
	{
		test.close();
	}

	public void testGetUsers()
	{
		List<User> users = test.getAllUsers();
		assertEquals(users.get(1).getUserName(), "Jon");
		assertEquals(users.get(2).getUserName(), "Aria");
		assertEquals(test.getUserByName("Default").getUserID(), 1);
		assertEquals(test.getUserById(4).getUserName(), "Bran");

		assertNull(test.getUserById(0));
		assertNull(test.getUserByName("Jorah the explorah"));

	}

	public void testInsertUsers()
	{
		User newUser = new User("tester");
		try
		{
			test.insertUser(newUser);
		} catch (DuplicateEntryException e)
		{
			fail("inserting a valid user should not throw an exception");
		}

		try
		{
			test.insertUser(newUser);
			fail("inserting a duplicate user should throw an exception");
		} catch (DuplicateEntryException e)
		{
			//we want this
		}
		newUser = null;
		newUser = test.getUserByName("tester");
		assertEquals(newUser.getUserName(), "tester");
		assertNotNull(newUser);
		test.deleteUserById(newUser.getUserID());
		newUser = null;
		assertNull(test.getUserByName("tester"));

	}

	public void testChangeUsers()
	{
		int totalUsers = test.getAllUsers().size();
		User user = new User(1, "NotDefault");

		assertNull(test.getUserByName("NotDefault"));
		try
		{
			test.updateUser(user);
		} catch (unfoundResourceException e)
		{
			fail("User 1 should be in the database");
		}

		user = null;
		user = test.getUserById(1);
		assertEquals(user.getUserName(), "NotDefault");

		assertNull(test.getUserByName("Default"));
		user = new User(1, "Default");
		try
		{
			test.updateUser(user);
		} catch (unfoundResourceException e)
		{
			fail("User 1 should still be in the database");
		}

		test.deleteUserById(-1);
		assertEquals(totalUsers, test.getAllUsers().size());

	}

	public void testGetCategories()
	{
		List<Category> cat = test.getAllCategories();
		assertEquals(cat.get(2).getCategoryName(), "groceries");
		assertEquals(cat.get(3).getCategoryName(), "weapons");
		assertEquals(test.getCategoryByNameAndIsExpense("Default", true).getCategoryID(), 1);
		assertEquals(test.getCategoryById(4).getCategoryName(), "entertainment");
		cat = test.getIncomeCategories();
		assertEquals(cat.get(0).getCategoryName(), "Default");
		assertEquals(cat.size(), 2);
		cat = test.getExpenseCategories();
		assertEquals(cat.get(0).getCategoryName(), "Default");
		assertEquals(cat.size(), 4);
		assertNull(test.getCategoryById(-1));
		assertNull(test.getCategoryByNameAndIsExpense("groceries", false));
	}

	public void testInsertCategories()
	{
		Category newCat = new Category("tester", true);
		try
		{
			test.insertCategory(newCat);
		} catch (DuplicateEntryException e)
		{
			fail("inserting a valid category should not throw an exception");
		}

		try
		{
			test.insertCategory(newCat);
			fail("inserting a duplicate category should not throw an exception");
		} catch (DuplicateEntryException e)
		{
			//we want this
		}
		newCat = null;
		newCat = test.getCategoryByNameAndIsExpense("tester", true);
		assertNotNull(newCat);
		test.deleteCategoryById(newCat.getCategoryID());
		newCat = null;
		assertNull(test.getCategoryByNameAndIsExpense("tester", true));
	}

	public void testGetTransactions()
	{
		List<Transaction> trans = test.getTransactionsByType(true);
		assertEquals(trans.get(0).getUserID(), 1);
		assertEquals(trans.get(1).getAmount(), 30.0, .1);
		assertNotEquals(trans.size(), 1);

		trans = test.getTransactionsByType(false);
		assertEquals(trans.get(0).getCategoryID(), 5);
		assertEquals(trans.size(), 2);

		assertEquals(test.getTransactionByID(1).getCategoryID(), 1);

		assertNull(test.getTransactionByID(0));

	}

	public void testInsertDeleteTransactions()
	{
		Transaction newTrans = new Transaction(new Date(), 1, true, 39.95, 1, "comment");

		assertEquals(test.getTransactionsByType(true).size(), 4);
		test.insertTransaction(newTrans);
		assertEquals(test.getTransactionsByType(true).size(), 5);
		test.deleteTransactionsByUserID(1);
		assertEquals(test.getTransactionsByType(true).size(), 3);

		test.insertTransaction(newTrans);
		assertEquals(test.getTransactionsByType(true).size(), 4);
		test.deleteTransactionsByCategoryID(1);
		assertEquals(test.getTransactionsByType(true).size(), 2);

	}

	public void testChangeTransactions()
	{
		Transaction oldTrans = test.getTransactionByID(1);
		Transaction newTrans = new Transaction(1, new Date(), 3, true, 49.8, 3, "hi");

		try
		{
			test.updateTransaction(newTrans);
		} catch (unfoundResourceException e)
		{
			fail("Transaction 1 should be in the database");
		}

		newTrans = null;
		newTrans = test.getTransactionByID(1);
		assertNotEquals(newTrans.getAmount(), oldTrans.getAmount());

		try
		{
			test.updateTransaction(oldTrans);
		} catch (unfoundResourceException e)
		{
			fail("Transaction 1 should still be in the database");
		}

		assertEquals(test.getTransactionsByType(true).size(), 4);
		oldTrans = new Transaction (new Date(), 3, true, 49.8, 3, "hi");
		test.insertTransaction(oldTrans);
		assertEquals(test.getTransactionsByType(true).size(), 5);
		test.unassignTransactionsByCategoryID(3);
		assertEquals(test.getTransactionsByType(true).get(2).getCategoryID(), 2);
		assertEquals(test.getTransactionsByType(true).get(2).getUserID(), 3);
		test.deleteTransactionsByUserID(3);

		assertEquals(test.getTransactionsByType(true).size(), 3);
		oldTrans = new Transaction (new Date(), 3, true, 49.8, 3, "hi");
		test.insertTransaction(oldTrans);
		assertEquals(test.getTransactionsByType(true).size(), 4);
		test.unassignTransactionsByUserID(3);
		assertEquals(test.getTransactionsByType(true).get(2).getUserID(), 2);
		assertEquals(test.getTransactionsByType(true).get(2).getCategoryID(), 1);
		test.deleteTransactionsByCategoryID(3);
		assertEquals(test.getTransactionsByType(true).size(), 3);


	}
}
