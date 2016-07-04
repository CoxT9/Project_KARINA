package group8.karina.presentation;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.github.mikephil.charting.formatter.*;


import java.text.DecimalFormat;

/**
 * This ValueFormatter is just for convenience and simply puts a "$" sign before
 * each value. (Recommeded for PieChart)(blatently stolen from mikephil charting)
 * It is modelled after the PerccentFormatter example by Philipp Jahoda
 *
 */
public class CurrencyFormatter implements ValueFormatter {

	protected DecimalFormat mFormat;
	public CurrencyFormatter() {
		mFormat = new DecimalFormat("###,###,##0.00");
	}

	public CurrencyFormatter(DecimalFormat format) {
		this.mFormat = format;
	}

	// ValueFormatter
	@Override
	public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
		return  "$ " + mFormat.format(value);
	}

}