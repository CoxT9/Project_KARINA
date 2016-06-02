package group8.karina.presentation;

import android.content.Intent;
import android.view.View;

import java.util.List;

import group8.karina.objects.Category;
import group8.karina.objects.Transaction;


public class IncomeActivity extends TransactionActivity
{

	public void saveButtonClicked(View view)
	{
		if (validateForSave())
		{
			Transaction t = new Transaction(getSelectedDate(), getSelectedUser(), false, getEnteredAmount(), getSelectedCategory(), getComments());
			accessTransactions.insertTransaction(t);

			startActivity(new Intent(this, MainActivity.class));
		}
	}

	protected List<Category> getCategories()
	{
		return accessCategories.getIncomeCategories();
	}
}
