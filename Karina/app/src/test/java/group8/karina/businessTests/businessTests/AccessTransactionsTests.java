package group8.karina.businessTests.businessTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import group8.karina.application.Services;
import group8.karina.business.AccessTransactions;
import group8.karina.objects.Transaction;
import group8.karina.persistence.DataAccessStub;

import static org.junit.Assert.*;

/**
 * Created by Malcolm on 2016-06-01.
 */
public class AccessTransactionsTests
{
    private DataAccessStub dataAccess;
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

    @Test
    public void get_transactions_by_type_returns_expense_transactions()
    {
        int expectedSize = 4; //from seed data
        List<Transaction> expenseTransactions = accessTransactions.getTransactionsByType(true);

        assertEquals(expectedSize,expenseTransactions.size());

        for (Transaction t : expenseTransactions)
        {
            assertEquals(t.isExpense(),true);
        }
    }

    @Test
    public void get_transactions_by_type_returns_income_transactions()
    {
        int expectedSize = 2; //from seed data
        List<Transaction> incomeTransactions = accessTransactions.getTransactionsByType(false);

        assertEquals(expectedSize, incomeTransactions.size());

        for (Transaction t :  incomeTransactions)
        {
            assertEquals(t.isExpense(),false);
        }
    }

    @Test
    public void insert_transaction_inserts_transaction()
    {
        Transaction expectedTransaction = new Transaction(null,8,true,1.12,1,"hello world");
        expectedTransaction.setTransactionID(123);
        accessTransactions.insertTransaction(expectedTransaction);

        Transaction actualTransaction = dataAccess.getTransactionByID(expectedTransaction.getTransactionID());

        assertNotNull(actualTransaction);
        assertEquals(expectedTransaction.getDate(),actualTransaction.getDate());
        assertEquals(expectedTransaction.getUserID(),actualTransaction.getUserID());
        assertEquals(expectedTransaction.isExpense(),actualTransaction.isExpense());
        assertEquals(expectedTransaction.getAmount(),actualTransaction.getAmount(),0);
        assertEquals(expectedTransaction.getCategoryID(),actualTransaction.getCategoryID());
        assertEquals(expectedTransaction.getComments(),actualTransaction.getComments());

    }

    @Test
    public void get_income_returns_total_income()
    {
        double expectedValue = 151.34; // from seed data
        double actualValue = accessTransactions.totalIncome();
        assertEquals(expectedValue, actualValue, 0.0);
    }

    @Test
    public void get_expenses_returns_total_expenses()
    {
        double expectedValue = 172.75; // from seed data
        double actualValue = accessTransactions.totalExpenses();
        assertEquals(expectedValue, actualValue, 0.0);
    }
}
