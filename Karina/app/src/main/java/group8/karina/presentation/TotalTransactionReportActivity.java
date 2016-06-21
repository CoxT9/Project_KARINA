package group8.karina.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import group8.karina.R;
import group8.karina.business.AccessTransactions;

public class TotalTransactionReportActivity extends PieChartReportActivityBase
{
	private TextView totalIncome;
	private TextView totalExpense;
	private TextView totalNet;
	private AccessTransactions transactions;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_total_transaction_report);
		transactions = new AccessTransactions();

		formatTextLabels();
		initializePieChart();

		pieChart.setData(createPieChartData());
		pieChart.invalidate();

	}

	private void formatTextLabels()
	{
		double netMonies = transactions.totalNet();

		totalIncome = (TextView) findViewById(R.id.incomeTotalValue);
		totalExpense = (TextView) findViewById(R.id.expenseTotalValue);
		totalNet = (TextView) findViewById(R.id.netTotalValue);
		pieChart = (PieChart) findViewById(R.id.pieChart);

		totalIncome.setText("$" + transactions.totalIncome());
		totalExpense.setText("$" + transactions.totalExpenses());

		//put into accounting format
		if (netMonies<0)
		{
			totalNet.setTextColor(0xFFFF0000);
			totalNet.setText("- $" + String.format("%.2f", netMonies*-1));
		}
		else
		{
			totalNet.setTextColor(totalIncome.getCurrentTextColor());
			totalNet.setText("$" + String.format("%.2f", netMonies));
		}
	}


	@Override
	protected void setEntriesAndCategories(ArrayList<Entry> entries, ArrayList<String> categoryNames)
	{
		double sumOfMonies = transactions.positiveSumTransactions();

		entries.add(new Entry((float)(transactions.totalIncome()*100/sumOfMonies),0));
		entries.add(new Entry((float)(transactions.totalExpenses()*100/sumOfMonies),1));

		categoryNames.add("Income");
		categoryNames.add("Expense");
	}


	@Override
	protected PieData createPieChartData()
	{
		PieData pieData = super.createPieChartData();
		pieData.setValueFormatter(new PercentFormatter());

		return pieData;
	}
}
