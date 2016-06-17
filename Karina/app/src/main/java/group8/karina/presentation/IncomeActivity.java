package group8.karina.presentation;

import android.content.Intent;
import android.view.View;

import java.util.List;

import group8.karina.objects.Category;


public class IncomeActivity extends TransactionActivity
{
	public void saveButtonClicked(View view)
	{
		if (validateForSave())
		{
			if(editTransaction != null)
			{
				updateExistingTransaction(false);
			}
			else
			{
				insertNewTransaction(false);
			}
		}
	}

	public void deleteButtonClicked(View view)
	{
		accessTransactions.deleteTransactionByID(editTransaction.getTransactionID());
		startActivity(new Intent(this, IncomeList.class));
		finish();
	}

	protected List<Category> getCategories()
	{
		return accessCategories.getIncomeCategories();
	}
}
