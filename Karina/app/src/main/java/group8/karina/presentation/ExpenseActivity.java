
package group8.karina.presentation;

import android.content.Intent;
import android.view.View;
import java.util.List;

import group8.karina.business.AccessTransactions;
import group8.karina.objects.Category;


public class ExpenseActivity extends TransactionActivity
{

	public void saveButtonClicked(View view)
	{
		if (validateForSave())
		{
			if(editTransaction != null)
			{
				updateExistingTransaction(true);
			}
			else
			{
				insertNewTransaction(true);
			}
		}
	}

	public void deleteButtonClicked(View view)
	{
		DeletionDialog d = new DeletionDialog(this,"Expense");
		d.show();
	}

	protected List<Category> getCategories()
	{
		return accessCategories.getExpenseCategories();
	}

	@Override
	public void deleteDialogDeleteButtonClicked()
	{
		accessTransactions.deleteTransactionByID(editTransaction.getTransactionID());
		startActivity(new Intent(this, ExpenseList.class));
		finish();
	}
}
