package group8.karina.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import group8.karina.R;
import group8.karina.business.AccessCategories;

public class IncomeList extends AppCompatActivity
{
	private RelativeLayout incomeCard;
	private AccessCategories access;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_income_list);

		incomeCard = (RelativeLayout) findViewById(R.id.incomeCard);

	}

	public void addIncomeClicked(View view)
	{
		startActivity(new Intent(this, IncomeActivity.class));
	}
}
