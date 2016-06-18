package group8.karina.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import group8.karina.R;
import group8.karina.business.AccessTransactions;

public class ReportsActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reports);
	}

	public void totalTransactionReportButtonClicked(View v)
	{
		startActivity(new Intent(this,TotalTransactionReportActivity.class));
	}

	public void transactionByCategoryButtonClicked(View v)
	{
		startActivity(new Intent(this,TransactionByCategoryReportActivity.class));
	}

	public void transactionByUserButtonClicked(View v)
	{
		startActivity(new Intent(this,TransactionByUserReportActivity.class));
	}
}
