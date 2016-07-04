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

public class IncomeList extends AppCompatActivity implements TransactionSortDialogCaller
{
	private ListView listView;
	private AccessTransactions access;

	private final int CATEGORIES = 0;
	private final int USERS = 1;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_income_list);

        listView = (ListView) findViewById(R.id.incomeList);

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
				Transaction selectedIncome = (Transaction) parent.getItemAtPosition(position);
				Intent editIncome = new Intent(IncomeList.this,IncomeActivity.class);
				editIncome.putExtra("EditTransaction", selectedIncome);

				startActivity(editIncome);
				finish();
			}
		});
	}

	public void addIncomeClicked(View view)
	{
		startActivity(new Intent(this, IncomeActivity.class));
		finish();
	}

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
			transaction = access.getOrderedTransactionsByCategory(false);
		}
		else if(sortOption==USERS)
		{
			transaction = access.getOrderedTransactionsByUser(false);
		}
		else
		{
			transaction = access.getOrderedTransactionsByDateAndType(false);
		}

		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, transaction);
		listView.setAdapter(adapter);
	}

	private void populateListView()
	{
		access = new AccessTransactions();
		List<Transaction> transactions = access.getTransactionsByType(false);

		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, transactions);
		listView.setAdapter(adapter);
	}
}
