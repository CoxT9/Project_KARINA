package group8.karina.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.R;
import group8.karina.objects.Transaction;
import group8.karina.objects.User;

/**
 * Created by Mike on 5/27/2016.
 */
public class ExpenseActivity extends TransactionActivity
{

    public void saveButtonClicked(View view)
    {
        if(validateForSave())
        {
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
