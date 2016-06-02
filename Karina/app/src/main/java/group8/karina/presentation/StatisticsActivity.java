package group8.karina.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import group8.karina.R;
import group8.karina.business.AccessTransactions;

public class StatisticsActivity extends AppCompatActivity
{
	private TextView totalIncome;
	private TextView totalExpense;
	private AccessTransactions transactions;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);

		transactions = new AccessTransactions();

		totalIncome = (TextView) findViewById(R.id.incomeTotalValue);
		totalExpense = (TextView) findViewById(R.id.expenseTotalValue);

		totalIncome.setText("$" + transactions.totalIncome());
		totalExpense.setText("$" + transactions.totalExpenses());
	}


}
