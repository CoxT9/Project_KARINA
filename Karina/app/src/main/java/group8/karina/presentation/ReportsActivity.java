package group8.karina.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import group8.karina.R;

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
	public void transactionOverTimeButtonClicked(View v)
	{
		startActivity(new Intent(this,TransactionOverTimeReportActivity.class));
	}
}
