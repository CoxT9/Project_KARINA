package group8.karina.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import group8.karina.R;
import group8.karina.business.AccessTransactions;

public class StatisticsActivity extends AppCompatActivity
{
    private AccessTransactions transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        transactions = new AccessTransactions();
    }

}
