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

    public void onUserListClicked(View view)
    {
        startActivity(new Intent(this, UserList.class));
    }

    public void onCategoryListClicked(View view)
    {
        startActivity(new Intent(this, CategoryList.class));
    }

    public void onExpenseListClicked(View view)
    {
        startActivity(new Intent(this, ExpenseList.class));
    }

    public void onIncomeListClicked(View view)
    {
        startActivity(new Intent(this, IncomeList.class));
    }
}
