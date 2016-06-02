package group8.karina.business;

import java.util.List;

import group8.karina.application.Services;

import group8.karina.objects.Transaction;
import group8.karina.persistence.DataAccessStub;

public class AccessTransactions
{
	private DataAccessStub dataAccess;

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
}
