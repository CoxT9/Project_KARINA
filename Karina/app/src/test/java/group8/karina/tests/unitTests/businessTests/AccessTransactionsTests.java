package group8.karina.tests.unitTests.businessTests;

import org.junit.After;
import org.junit.Before;

import java.util.List;

import group8.karina.application.DatabaseService;
import group8.karina.business.AccessTransactions;
import group8.karina.objects.Transaction;
import group8.karina.tests.testHelpers.DataAccessStub;
import group8.karina.persistence.Database;


public class AccessTransactionsTests extends junit.framework.TestCase
{
	private Database dataAccess;
	private AccessTransactions accessTransactions;

	@Before
	public void setUp()
	{
		Database testDb = new DataAccessStub("test");
		testDb.open("test");
		DatabaseService.setDatabase(testDb);
		dataAccess = DatabaseService.getDataAccess();
		accessTransactions = new AccessTransactions();
	}

	@After
	public void tearDown()
	{
		DatabaseService.closeDataAccess();
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
		assertEquals(8, actualTransaction.getUserID());
		assertEquals(true, actualTransaction.isExpense());
		assertEquals(1.12, actualTransaction.getAmount(), 0);
		assertEquals(1, actualTransaction.getCategoryID());
		assertEquals("hello world", actualTransaction.getComments());
	}

	public void testGetIncomeReturnsTotalIncome()
	{
		double actualValue = accessTransactions.totalIncome();
		assertEquals(151.34, actualValue, 0.0);
	}
	public void testGetExpensesReturnsTotalExpenses()
	{
		double actualValue = accessTransactions.totalExpenses();
		assertEquals(172.75, actualValue, 0.0);
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
