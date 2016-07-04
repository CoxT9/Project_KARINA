package group8.karina.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Hashtable;

import group8.karina.R;
import group8.karina.business.AccessTransactions;

public class TransactionByUserReportActivity extends PieChartReportActivityBase
{
	private AccessTransactions transactions;
	private RadioButton expenseButton;
	private boolean isExpense;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_by_user_report);

		transactions = new AccessTransactions();
		pieChart = (PieChart) findViewById(R.id.pieChart);
		expenseButton = (RadioButton) findViewById(R.id.expenseRadioButton);
		isExpense = expenseButton.isChecked();

		initializePieChart();
		pieChart.setData(createPieChartData());
		pieChart.invalidate();
	}


	@Override
	protected void setEntriesAndCategories(ArrayList<Entry> entries, ArrayList<String> categoryNames)
	{
		Hashtable<String,Double> totals = transactions.getTotalTransactionsByUser(isExpense);
		int i=0;

		for(String key : totals.keySet())
		{
			entries.add(new Entry(totals.get(key).floatValue(),i));
			categoryNames.add(key);
			i++;
		}
	}
	public void expenseRadioButtonClicked(View v)
	{
		isExpense = true;
		pieChart.setData(createPieChartData());
		pieChart.invalidate();
	}

	public void incomeRadioButtonClicked(View v)
	{
		isExpense = false;
		pieChart.setData(createPieChartData());
		pieChart.invalidate();
	}

}
