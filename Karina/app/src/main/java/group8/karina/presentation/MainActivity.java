package group8.karina.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import group8.karina.R;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAddUserClicked(View view)
    {
        startActivity(new Intent(this, UserActivity.class));
    }

    public void onAddCategoryClicked(View view)
    {
        startActivity(new Intent(this, CategoryActivity.class));
    }

    public void onAddExpenseClicked(View view)
    {
        startActivity(new Intent(this, ExpenseActivity.class));
    }

    public void onAddIncomeClicked(View view)
    {
        startActivity(new Intent(this, IncomeActivity.class));
    }
}
