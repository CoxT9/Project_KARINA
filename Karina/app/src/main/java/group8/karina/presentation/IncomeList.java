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
import group8.karina.application.Services;
import group8.karina.business.AccessTransactions;
import group8.karina.objects.Transaction;

public class IncomeList extends AppCompatActivity
{
	private ListView listView;
	private AccessTransactions access;


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
			}
		});
	}

	public void addIncomeClicked(View view)
	{
		startActivity(new Intent(this, IncomeActivity.class));
	}

	private void populateListView()
	{
		access = new AccessTransactions(Services.getDataAccess());
		List<Transaction> transactions = access.getTransactionsByType(false);

		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, transactions);
		listView.setAdapter(adapter);
	}
}
