package group8.karina.presentation;


import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import group8.karina.R;
import group8.karina.business.AccessTransactions;
import group8.karina.objects.Transaction;

public class TransactionByCategoryPieChartReportActivity extends PieChartReportActivityBase
{
	private AccessTransactions transactions;
	private RadioButton expenseButton;
	private boolean isExpense;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_by_category_report);

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
		List<Transaction> totals = transactions.getTotalTransactionsByCategory(isExpense);

		for(int i=0;i<totals.size(); i++)
		{
			entries.add(new Entry((float)totals.get(i).getAmount(),i));
			categoryNames.add(totals.get(i).getCategoryName());
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
