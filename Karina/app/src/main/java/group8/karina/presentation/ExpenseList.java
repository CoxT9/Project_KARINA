package group8.karina.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import group8.karina.R;
import group8.karina.business.AccessCategories;

public class ExpenseList extends AppCompatActivity
{
	private RelativeLayout expenseCard;
	private AccessCategories access;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_list);

		expenseCard = (RelativeLayout) findViewById(R.id.expenseCard);

	}

	public void addExpenseClicked(View view)
	{
		startActivity(new Intent(this, ExpenseActivity.class));
	}
}
