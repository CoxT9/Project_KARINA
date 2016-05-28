package group8.karina.business;

import java.util.List;
import group8.karina.application.Services;

import group8.karina.objects.Transaction;
import group8.karina.persistence.DataAccessStub;

public class AccessTransactions
{
    private DataAccessStub dataAccess;
    private List<Transaction> Transactions;
    private Transaction Transaction;
    private boolean isExpense;

    public AccessTransactions()
    {
        dataAccess = Services.getDataAccess();
    }

    public List<Transaction> getAllTransactions() // Why does it pass in a list to get a list?
    {
        return dataAccess.getAllTransactions();
    }

    public List<Transaction> getTransactionsByType(boolean isExpense)
    {
        return dataAccess.getTransactionsByType(isExpense);
    }

    public String insertTransaction(Transaction currentTransaction)
    {
        return dataAccess.insertTransaction(currentTransaction);
    }

    public String updateTransaction(Transaction currentTransaction)
    {
        return dataAccess.updateTransaction(currentTransaction);
    }

    public String deleteTransaction(Transaction currentTransaction)
    {
        return dataAccess.deleteTransaction(currentTransaction);
    }

    public int totalExpenses()
    {
       // for t in transcation: if t is exp, add to total. return total
        return -1;
    }

    public int totalIncome()
    {
        // for t in transcations: if t is income, add to total. return total
        return -1;
    }
    // Also have to do expense by user and expense by category
}
