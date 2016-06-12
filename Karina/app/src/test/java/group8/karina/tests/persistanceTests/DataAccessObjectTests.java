package group8.karina.tests.persistanceTests;

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

public class DataAccessObjectTests
{
	DataAccessObject test;

	@Before
	public void setUp()
	{
		test = new DataAccessObject("database");
		test.open("database/db1");
	}

	@After
	public void shutdown()
	{
		test.close();
	}

	@Test
	public void getUsersTest()
	{
		List<User> users = test.getUserSequential();
		assertEquals(users.get(1).getUserName(), "Jon");
		assertEquals(users.get(2).getUserName(), "Aria");
		assertEquals(test.getUserByName("Default").getUserID(), 1);
		assertEquals(test.getUserById(4).getUserName(), "Bran");

		assertNull(test.getUserById(0));
		assertNull(test.getUserByName("Jorah the explorah"));
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

	}

	@Test
	public void insertUsersTest()
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
		assertNotNull(newUser);
		test.deleteUserById(newUser.getUserID());
		newUser = null;
		assertNull(test.getUserByName("tester"));

	}

	@Test
	public void changeUsersTest()
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

	@Test
	public void getCategoriesTest()
	{
		List<Category> cat = test.getAllCategories();
		assertEquals(cat.get(1).getCategoryName(), "groceries");
		assertEquals(cat.get(2).getCategoryName(), "weapons");
		assertEquals(test.getCategoryByNameAndIsExpense("Default", true).getCategoryID(), 1);
		assertEquals(test.getCategoryById(4).getCategoryName(), "income");
		cat = test.getIncomeCategories();
		assertEquals(cat.get(0).getCategoryName(), "income");
		assertEquals(cat.size(), 1);
		cat = test.getExpenseCategories();
		assertEquals(cat.get(0).getCategoryName(), "Default");
		assertNotEquals(cat.size(), 1);
		assertNull(test.getCategoryById(0));
		assertNull(test.getCategoryByNameAndIsExpense("Default", false));
	}

	@Test
	public void insertCategoriesTest()
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
		test.deleteCategoryByID(newCat.getCategoryID());
		newCat = null;
		assertNull(test.getCategoryByNameAndIsExpense("tester", true));
	}

	@Test
	public void getTransactionsTest()
	{
		List<Transaction> trans = test.getTransactionsByType(true);
		assertEquals(trans.get(0).getUserID(), 1);
		assertEquals(trans.get(1).getAmount(), 95.5, .1);
		assertNotEquals(trans.size(), 1);

		trans = test.getTransactionsByType(false);
		assertEquals(trans.get(0).getCategoryID(), 4);
		assertEquals(trans.size(), 1);

		assertEquals(test.getTransactionByID(1).getCategoryID(), 2);

		assertNull(test.getTransactionByID(0));
	}

	@Test
	public void insertTransactionsTest()
	{
		Transaction newTrans = new Transaction(new Date(), 4, true, 39.95, 1, "comment");
		assertEquals(test.getTransactionsByType(true).size(), 2);
		test.insertTransaction(newTrans);
		assertEquals(test.getTransactionsByType(true).size(), 3);
		assertNotNull(newTrans);
		test.deleteTransactionsByUserID(4);
		assertEquals(test.getTransactionsByType(true).size(), 2);
	}
}
