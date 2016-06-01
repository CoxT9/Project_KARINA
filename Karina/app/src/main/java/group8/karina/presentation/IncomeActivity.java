package group8.karina.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import group8.karina.R;
import group8.karina.objects.Transaction;


public class IncomeActivity extends TransactionActivity
{

    public void saveButtonClicked(View view)
    {
        if(validateForSave())
        {
            Transaction t = new Transaction(getSelectedDate(),getSelectedUser(),false,getEnteredAmount(),getSelectedCategory(),getComments());
            accessTransactions.insertTransaction(t);

            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
