package group8.karina.tests.businessTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import group8.karina.application.Services;
import group8.karina.business.AccessTransactions;
import group8.karina.objects.Transaction;
import group8.karina.persistence.Database;

/**
 * Created by Malcolm on 2016-06-01.
 */
public class AccessTransactionsTests extends junit.framework.TestCase
{
	private Database dataAccess;
	private AccessTransactions accessTransactions;

	@Before
	public void setUp()
	{
		dataAccess = Services.getDataAccess();
		accessTransactions = new AccessTransactions();
	}

	@After
	public void tearDown()
	{
		Services.closeDataAccess();
	}

	public void testGetTransactionsByTypeReturnsExpenseTransactions()
	{
		int expectedSize = 4; //from seed data
		List<Transaction> expenseTransactions = accessTransactions.getTransactionsByType(true);

		assertEquals(expectedSize, expenseTransactions.size());

		for (Transaction t : expenseTransactions)
		{
			assertEquals(t.isExpense(), true);
		}
	}

	public void testGetTransactionsByTypeReturnsIncomeTransactions()
	{
		int expectedSize = 2; //from seed data
		List<Transaction> incomeTransactions = accessTransactions.getTransactionsByType(false);

		assertEquals(expectedSize, incomeTransactions.size());

		for (Transaction t : incomeTransactions)
		{
			assertEquals(t.isExpense(), false);
		}
	}

	public void testInsertTransactionInsertsTransaction()
	{
		Transaction expectedTransaction = new Transaction(null, 8, true, 1.12, 1, "hello world");
		expectedTransaction.setTransactionID(123);
		accessTransactions.insertTransaction(expectedTransaction);

		Transaction actualTransaction = dataAccess.getTransactionByID(expectedTransaction.getTransactionID());

		assertNotNull(actualTransaction);
		assertEquals(expectedTransaction.getDate(), actualTransaction.getDate());
		assertEquals(expectedTransaction.getUserID(), actualTransaction.getUserID());
		assertEquals(expectedTransaction.isExpense(), actualTransaction.isExpense());
		assertEquals(expectedTransaction.getAmount(), actualTransaction.getAmount(), 0);
		assertEquals(expectedTransaction.getCategoryID(), actualTransaction.getCategoryID());
		assertEquals(expectedTransaction.getComments(), actualTransaction.getComments());

	}

	public void testGetIncomeReturnsTotalIncome()
	{
		double expectedValue = 151.34; // from seed data
		double actualValue = accessTransactions.totalIncome();
		assertEquals(expectedValue, actualValue, 0.0);
	}
	public void testGetExpensesReturnsTotalExpenses()
	{
		double expectedValue = 172.75; // from seed data
		double actualValue = accessTransactions.totalExpenses();
		assertEquals(expectedValue, actualValue, 0.0);
	}

	public void testUnassignTransactionsByCategoryUnassigns()
	{
		int testCatID = -10;
		Transaction testTransaction = new Transaction(null, 8, true, 5.5, testCatID, "test transaction");
		testTransaction.setTransactionID(-50);

		accessTransactions.insertTransaction(testTransaction);

		accessTransactions.unassignTransactionsByCategoryID(testCatID);
		assertEquals(testTransaction.getCategoryID(), 1);
	}

	public void testDeleteTransactionsByCategoryDeletesAll()
	{
		int testCatID = -10;
		Transaction testTransaction = new Transaction(null, 8, true, 5.5, testCatID, "test transaction");
		testTransaction.setTransactionID(-50);

		accessTransactions.insertTransaction(testTransaction);

		accessTransactions.deleteTransactionsByCategoryID(testCatID);
		List<Transaction> finalTransactions = accessTransactions.getTransactionsByType(true);
		assertTrue(!finalTransactions.contains(testTransaction));
	}

	public void testDeleteTransactionsByUserDeletesAll()
	{
		int testUserID = -10;
		Transaction testTransaction = new Transaction(null, testUserID, true, 5.5,8 , "test transaction");
		testTransaction.setTransactionID(-50);

		accessTransactions.insertTransaction(testTransaction);

		accessTransactions.deleteTransactionsByUserID(testUserID);
		List<Transaction> finalTransactions = accessTransactions.getTransactionsByType(true);
		assertTrue(!finalTransactions.contains(testTransaction));
	}

	public void testUnassignTransactionsByUserUnassigns()
	{
		int testUserID = -10;
		Transaction testTransaction = new Transaction(null,testUserID , true, 5.5, 8, "test transaction");
		testTransaction.setTransactionID(15);

		accessTransactions.insertTransaction(testTransaction);

		accessTransactions.unassignTransactionsByUserID(testUserID);
		assertEquals(dataAccess.getTransactionByID(testTransaction.getTransactionID()).getUserID(), 1);
	}
}
