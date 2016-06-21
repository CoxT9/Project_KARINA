package group8.karina.tests.persistenceTests;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.*;


import group8.karina.Exceptions.*;
import group8.karina.objects.*;
import group8.karina.persistence.DataAccessObject;
import group8.karina.persistence.DataAccessStub;
import group8.karina.persistence.Database;

public class DataAccessObjectTests extends junit.framework.TestCase
{
	Database test;

	@Before
	public void setUp()
	{
		test = new DataAccessStub("test");
		test.open("test");
	}

	@After
	public void tearDown()
	{
		test.close();
	}

	public void testGetUsersTest()
	{
		List<User> users = test.getAllUsers();
		assertEquals(users.get(1).getUserName(), "Jon");
		assertEquals(users.get(2).getUserName(), "Bran");
		assertEquals(test.getUserByName("Default").getUserID(), 1);
		assertEquals(test.getUserById(4).getUserName(), "Aria");

		assertNull(test.getUserById(0));
		assertNull(test.getUserByName("Jorah the explorah"));
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

	}

	public void testInsertUsersTest()
	{
		User newUser = new User("tester");
		try
		{
			test.insertUser(newUser);
		} catch (DuplicateEntryException e)
		{
			fail("inserting a valid user should not throw an exception");
		}

		newUser = null;
		newUser = test.getUserByName("tester");
		assertEquals(newUser.getUserName(), "tester");
		assertNotNull(newUser);
		test.deleteUserById(newUser.getUserID());
		newUser = null;
		assertNull(test.getUserByName("tester"));

	}

	public void testChangeUsersTest()
	{
		User user = new User(1, "NotDefault");

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


	}

	public void testGetCategoriesTest()
	{
		List<Category> cat = test.getAllCategories();
		assertEquals(cat.get(2).getCategoryName(), "weapons");
		assertEquals(cat.get(3).getCategoryName(), "entertainment");
		assertEquals(test.getCategoryByNameAndIsExpense("Default", true).getCategoryID(), 1);
		assertEquals(test.getCategoryById(4).getCategoryName(), "entertainment");
		cat = test.getIncomeCategories();
		assertEquals(cat.get(0).getCategoryName(), "income");
		assertEquals(cat.size(), 2);
		cat = test.getExpenseCategories();
		assertEquals(cat.get(0).getCategoryName(), "Default");
		assertEquals(cat.size(), 4);
		assertNull(test.getCategoryById(-1));
		assertNull(test.getCategoryByNameAndIsExpense("groceries", false));
	}

	public void testInsertCategoriesTest()
	{
		Category newCat = new Category("tester", true);
		try
		{
			test.insertCategory(newCat);
		} catch (DuplicateEntryException e)
		{
			fail("inserting a valid category should not throw an exception");
		}

		newCat = null;
		newCat = test.getCategoryByNameAndIsExpense("tester", true);
		assertNotNull(newCat);
		test.deleteCategoryById(newCat.getCategoryID());
		newCat = null;
		assertNull(test.getCategoryByNameAndIsExpense("tester", true));
	}

	public void testGetTransactionsTest()
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

	public void testInsertDeleteTransactionsTest()
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
		assertEquals(test.getTransactionsByType(true).get(2).getCategoryID(), 1);
		assertEquals(test.getTransactionsByType(true).get(2).getUserID(), 2);
		test.deleteTransactionsByUserID(3);

		assertEquals(test.getTransactionsByType(true).size(), 3);
		oldTrans = new Transaction (new Date(), 3, true, 49.8, 3, "hi");
		test.insertTransaction(oldTrans);
		assertEquals(test.getTransactionsByType(true).size(), 4);
		test.unassignTransactionsByUserID(3);
		assertEquals(test.getTransactionsByType(true).get(2).getUserID(), 1);
		assertEquals(test.getTransactionsByType(true).get(2).getCategoryID(), 1);
		test.deleteTransactionsByCategoryID(3);
		assertEquals(test.getTransactionsByType(true).size(), 3);


	}
}
