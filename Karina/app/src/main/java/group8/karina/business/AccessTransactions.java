package group8.karina.business;

import java.util.Hashtable;
import java.util.List;

import group8.karina.application.Services;
import group8.karina.objects.Transaction;
import group8.karina.persistence.Database;
import group8.karina.Exceptions.unfoundResourceException;

public class AccessTransactions
{
	private Database dataAccess;

	public AccessTransactions()
	{
		dataAccess = Services.getDataAccess();
	}

	public List<Transaction> getTransactionsByType(boolean isExpense)
	{
		return dataAccess.getTransactionsByType(isExpense);
	}

	public void insertTransaction(Transaction currentTransaction)
	{
		dataAccess.insertTransaction(currentTransaction);
	}

	public double totalExpenses()
	{
		double sumExp = 0;
		for (Transaction tr : getTransactionsByType(true))
		{
			sumExp += tr.getAmount();
		}
		return sumExp;
	}

	public double totalIncome()
	{
		double sumInc = 0;
		for (Transaction tr : getTransactionsByType(false))
		{
			sumInc += tr.getAmount();
		}
		return sumInc;
	}

	public void deleteTransactionsByUserID(int userID)
	{
		dataAccess.deleteTransactionsByUserID(userID);
	}

	public void deleteTransactionsByCategoryID(int categoryID)
	{
		dataAccess.deleteTransactionsByCategoryID(categoryID);
	}

	public void unassignTransactionsByUserID(int userID)
	{
		dataAccess.unassignTransactionsByUserID(userID);
	}

	public void unassignTransactionsByCategoryID(int categoryID)
	{
		dataAccess.unassignTransactionsByCategoryID(categoryID);
	}

	public void deleteTransactionByID(int transID)
	{
		dataAccess.deleteTransactionByID(transID);
	}

	public void updateTransaction(Transaction trans) throws unfoundResourceException
	{
		dataAccess.updateTransaction(trans);
	}

	public Hashtable<String,Double> getTotalTransactionsByCategory(boolean isExpense)
	{
		List<Transaction> transactions = dataAccess.getTransactionsByType(isExpense);
		Hashtable<String,Double> transactionsByCategory = new Hashtable<String,Double>();

		for(Transaction transaction : transactions)
		{
			if(transactionsByCategory.contains(transaction.getCategoryName()))
			{
				transactionsByCategory.put(transaction.getCategoryName(),transactionsByCategory.get(transaction.getCategoryName())+transaction.getAmount());
			}
			else
			{
				transactionsByCategory.put(transaction.getCategoryName(),transaction.getAmount());
			}
		}

		return transactionsByCategory;
	}

	public Hashtable<String,Double> getTotalTransactionsByUser(boolean isExpense)
	{
		List<Transaction> transactions = dataAccess.getTransactionsByType(isExpense);
		Hashtable<String,Double> transactionsByUser = new Hashtable<String,Double>();

		for(Transaction transaction : transactions)
		{
			if(transactionsByUser.contains(transaction.getUserName()))
			{
				transactionsByUser.put(transaction.getUserName(),transactionsByUser.get(transaction.getUserName())+transaction.getAmount());
			}
			else
			{
				transactionsByUser.put(transaction.getUserName(),transaction.getAmount());
			}
		}

		return transactionsByUser;
	}
	public List<Transaction> getOrderedTransactionsByDate()
	{
		return dataAccess.getOrderedTransactionsByDate();
	}

	public double positiveSumTransactions()
	{
		return totalExpenses()+totalIncome();
	}

	public double totalNet()
	{
		return totalIncome() - totalExpenses();
	}
}
