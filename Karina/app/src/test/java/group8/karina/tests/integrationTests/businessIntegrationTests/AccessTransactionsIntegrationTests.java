package group8.karina.tests.integrationTests.businessIntegrationTests;

import org.junit.After;
import org.junit.Before;

import java.util.List;

import group8.karina.application.DatabaseService;
import group8.karina.business.AccessTransactions;
import group8.karina.objects.Transaction;
import group8.karina.persistence.Database;
import group8.karina.tests.testHelpers.TestDataAccessObject;


public class AccessTransactionsIntegrationTests extends junit.framework.TestCase
{
	private Database dataAccess;
	private AccessTransactions accessTransactions;

	@Before
	public void setUp()
	{
		dataAccess = new TestDataAccessObject("db");
		DatabaseService.setDatabase(dataAccess);
		DatabaseService.setDBPathName("src/main/assets/db/");
		DatabaseService.openDataAccess();
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

		List<Transaction> expenseTransactions = accessTransactions.getTransactionsByType(true);

		assertEquals(4, expenseTransactions.size());

		for (Transaction t : expenseTransactions)
		{
			assertEquals(t.isExpense(), true);
		}
	}

	public void testGetTransactionsByTypeReturnsIncomeTransactions()
	{
		List<Transaction> incomeTransactions = accessTransactions.getTransactionsByType(false);

		assertEquals(2, incomeTransactions.size());

		for (Transaction t : incomeTransactions)
		{
			assertEquals(t.isExpense(), false);
		}
	}

	public void testInsertTransactionInsertsTransaction()
	{
		Transaction expectedTransaction = new Transaction(null, 5, true, 1.12, 1, "hello world");
		expectedTransaction.setTransactionID(-3);
		accessTransactions.insertTransaction(expectedTransaction);

		Transaction actualTransaction = dataAccess.getTransactionByID(expectedTransaction.getTransactionID());

		assertNotNull(actualTransaction);
		assertEquals(5, actualTransaction.getUserID());
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

		Transaction testTransaction = new Transaction(null, 4, true, 5.5, 5, "test transaction");
		testTransaction.setTransactionID(-50);

		accessTransactions.insertTransaction(testTransaction);

		accessTransactions.unassignTransactionsByCategoryID(5);
		assertNotNull(dataAccess.getTransactionByID(-50));
		assertEquals(dataAccess.getTransactionByID(-50).getCategoryID(), 1);
	}

	public void testDeleteTransactionsByCategoryDeletesAll()
	{

		Transaction testTransaction = new Transaction(null, 4, true, 5.5, 5, "test transaction");
		testTransaction.setTransactionID(-50);

		accessTransactions.insertTransaction(testTransaction);

		accessTransactions.deleteTransactionsByCategoryID(5);
		List<Transaction> finalTransactions = accessTransactions.getTransactionsByType(true);
		assertTrue(!finalTransactions.contains(testTransaction));
	}

	public void testDeleteTransactionsByUserDeletesAll()
	{

		Transaction testTransaction = new Transaction(null, 4, true, 5.5, 5, "test transaction");
		testTransaction.setTransactionID(-50);

		accessTransactions.insertTransaction(testTransaction);

		accessTransactions.deleteTransactionsByUserID(4);
		List<Transaction> finalTransactions = accessTransactions.getTransactionsByType(true);
		assertTrue(!finalTransactions.contains(testTransaction));
	}

	public void testUnassignTransactionsByUserUnassigns()
	{

		Transaction testTransaction = new Transaction(null, 4, true, 5.5, 5, "test transaction");
		testTransaction.setTransactionID(15);

		accessTransactions.insertTransaction(testTransaction);

		accessTransactions.unassignTransactionsByUserID(4);
		assertEquals(dataAccess.getTransactionByID(testTransaction.getTransactionID()).getUserID(), 1);
	}
}
