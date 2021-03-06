package group8.karina.presentation;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.github.mikephil.charting.formatter.ValueFormatter;


import java.text.DecimalFormat;

/**
 * This ValueFormatter is just for convenience and simply puts a "$" sign before
 * each value. (Recommeded for PieChart)
 * It is modelled after the PercentFormatter example by Philipp Jahoda
 */
public class CurrencyFormatter implements ValueFormatter
{

	protected DecimalFormat mFormat;

	public CurrencyFormatter()
	{
		mFormat = new DecimalFormat("###,###,##0.00");
	}

	// ValueFormatter
	@Override
	public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler)
	{
		return "$ " + mFormat.format(value);
	}

}