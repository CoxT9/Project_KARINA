package group8.karina.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import group8.karina.R;
import group8.karina.business.AccessTransactions;
import group8.karina.objects.Transaction;

public class ExpenseList extends AppCompatActivity
{
	private ListView listView;
	private AccessTransactions access;


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
			}
		});
	}

	public void addExpenseClicked(View view) { startActivity(new Intent(this, ExpenseActivity.class)); }

	private void populateListView()
	{
		access = new AccessTransactions();
		List<Transaction> transactions = access.getTransactionsByType(true);

		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, transactions);
		listView.setAdapter(adapter);
	}
}
