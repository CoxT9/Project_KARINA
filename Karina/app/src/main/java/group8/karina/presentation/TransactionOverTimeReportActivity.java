package group8.karina.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.List;
import java.util.ArrayList;

import group8.karina.R;
import group8.karina.business.AccessTransactions;
import group8.karina.objects.Transaction;

public class TransactionOverTimeReportActivity extends AppCompatActivity
{
	private AccessTransactions transactions;
	private BarChart barChart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_over_time_report);

		transactions = new AccessTransactions();
		barChart = (BarChart) findViewById(R.id.barChart);

		initializeBarChart();

	}

	private void initializeBarChart()
	{
		//setUpChartLegend(barChart.getLegend());

		barChart.setData(createBarChartData());
	}

	private void setUpChartLegend(Legend legend)
	{
		legend.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
		legend.setXEntrySpace(7);
		legend.setYEntrySpace(5);
		legend.setTextSize(15);
	}

	private BarData createBarChartData()
	{
		List<Transaction> data = transactions.getOrderedTransactionsByDate();
		ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
		List<String> titles = new ArrayList<String>();
		double amount;

		for (int i = 0; i<data.size(); i++)
		{
			if (data.get(i).isExpense())
			{
				amount = data.get(i).getAmount() * -1;
			}
			else
			{
				amount = data.get(i).getAmount();
			}
			entries.add(new BarEntry((float)amount, i));
			titles.add(data.get(i).getDate().toString());

		}


		BarDataSet dataSet = new BarDataSet(entries,"");
		//dataSet.setColors(ColorTemplate.PASTEL_COLORS);
		dataSet.setValueTextSize(17f);
		BarData barData = new BarData(titles,dataSet);


		return barData;
	}


}
