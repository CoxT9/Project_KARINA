package group8.karina.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

		ArrayList<String> values = new ArrayList<String>();
		access = new AccessTransactions();
		List<Transaction> transactions = access.getTransactionsByType(true);

		for (Transaction tr : transactions)
		{
			values.add("$" + tr.getAmount() + " on " + tr.getDate());
		}


		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
		listView.setAdapter(adapter);

    }

    public void addExpenseClicked(View view)
    {
        startActivity(new Intent(this, ExpenseActivity.class));
    }
}
