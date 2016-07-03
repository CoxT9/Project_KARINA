package group8.karina.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import group8.karina.R;
import group8.karina.business.AccessTransactions;
import group8.karina.objects.Transaction;

public class ExpenseList extends AppCompatActivity implements TransactionSortDialogCaller
{
	private ListView listView;
	private AccessTransactions access;

	private final int CATEGORIES = 0;
	private final int USERS = 1;
	private final int DATE = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_list);

		listView = (ListView) findViewById(R.id.expenseList);

		populateListView();
		setListViewOnItemClicked();
	}

	private void setListViewOnItemClicked()
	{
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
									long id)
			{
				Transaction selectedExpense = (Transaction) parent.getItemAtPosition(position);
				Intent editExpense = new Intent(ExpenseList.this,ExpenseActivity.class);
				editExpense.putExtra("EditTransaction", selectedExpense);

				startActivity(editExpense);
				finish();
			}
		});
	}

	public void addExpenseClicked(View view) { startActivity(new Intent(this, ExpenseActivity.class)); }

	public void sortButtonClicked(View view)
	{

		TransactionSortDialog t = new TransactionSortDialog(this);
		t.show();
	}

	public void sortOkButtonClicked(int sortOption)
	{
		List<Transaction> transaction;

		if(sortOption==CATEGORIES)
		{
			transaction = access.getOrderedTransactionsByCategory(true);
		}
		else if(sortOption==USERS)
		{
			transaction = access.getOrderedTransactionsByUser(true);
		}
		else
		{
			transaction = access.getOrderedTransactionsByDateAndType(true);
		}

		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, transaction);
		listView.setAdapter(adapter);
	}

	private void populateListView()
	{
		access = new AccessTransactions();
		List<Transaction> transactions = access.getTransactionsByType(true);

		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, transactions);
		listView.setAdapter(adapter);
	}
}
