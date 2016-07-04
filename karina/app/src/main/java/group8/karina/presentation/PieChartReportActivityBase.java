package group8.karina.presentation;

import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public abstract class PieChartReportActivityBase extends AppCompatActivity
{
	protected PieChart pieChart;

	protected void initializePieChart()
	{
		pieChart.setHoleRadius(40);
		pieChart.setTransparentCircleRadius(44);
		pieChart.setDescription("");
		setUpChartLegend(pieChart.getLegend());

		pieChart.invalidate();
	}

	abstract protected void setEntriesAndCategories(ArrayList<Entry> entries, ArrayList<String> categoryNames);

	private void setUpChartLegend(Legend legend)
	{
		legend.setEnabled(false);
	}

	protected PieData createPieChartData()
	{
		ArrayList<Entry> entries = new ArrayList<Entry>();
		ArrayList<String> categoryNames = new ArrayList<String>();

		setEntriesAndCategories(entries,categoryNames);

		PieDataSet dataSet = new PieDataSet(entries,"");
		dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
		dataSet.setValueTextSize(17f);
		dataSet.setValueFormatter(new CurrencyFormatter());;
		PieData pieData = new PieData(categoryNames,dataSet);

		return pieData;
	}
}
