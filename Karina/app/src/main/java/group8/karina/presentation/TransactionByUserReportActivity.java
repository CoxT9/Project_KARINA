package group8.karina.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import group8.karina.R;
import group8.karina.business.AccessTransactions;
import group8.karina.objects.Transaction;

public class TransactionByUserReportActivity extends AppCompatActivity
{
	private PieChart pieChart;
	private AccessTransactions transactions;
	private RadioButton expenseButton;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_by_user_report);

		transactions = new AccessTransactions();
		pieChart = (PieChart) findViewById(R.id.pieChart);
		expenseButton = (RadioButton) findViewById(R.id.expenseRadioButton);

		initializePieChart();
		pieChart.setData(createPieChartData(expenseButton.isChecked()));
	}

	private void initializePieChart()
	{
		pieChart.setHoleRadius(40);
		pieChart.setTransparentCircleRadius(44);
		pieChart.setDescription("");
		setUpChartLegend(pieChart.getLegend());

		pieChart.invalidate();
	}

	private void setUpChartLegend(Legend legend)
	{
		legend.setEnabled(false);
	}

	private PieData createPieChartData(boolean isExpense)
	{
		ArrayList<Entry> entries = new ArrayList<Entry>();
		ArrayList<String> userNames = new ArrayList<String>();
		List<Transaction> totals = transactions.getTotalTransactionsByUser(isExpense);

		for(int i=0;i<totals.size(); i++)
		{
			entries.add(new Entry((float)totals.get(i).getAmount(),i));
			userNames.add(totals.get(i).getUserName());
		}

		PieDataSet dataSet = new PieDataSet(entries,"");
		dataSet.setColors(ColorTemplate.PASTEL_COLORS);
		dataSet.setValueTextSize(17f);
		PieData pieData = new PieData(userNames,dataSet);

		return pieData;
	}

	public void expenseRadioButtonClicked(View v)
	{
		pieChart.setData(createPieChartData(true));
		pieChart.invalidate();
	}

	public void incomeRadioButtonClicked(View v)
	{
		pieChart.setData(createPieChartData(false));
		pieChart.invalidate();
	}

}
