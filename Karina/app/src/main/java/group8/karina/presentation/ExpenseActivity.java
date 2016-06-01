
package group8.karina.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.R;
import group8.karina.objects.Category;
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
            Transaction t = new Transaction(getSelectedDate(),getSelectedUser(),true,(-1.0*getEnteredAmount()),getSelectedCategory(),getComments());
            accessTransactions.insertTransaction(t);

            startActivity(new Intent(this, MainActivity.class));
        }
    }

    protected List<Category> getCategories(){
        return accessCategories.getExpenseCategories();
    }
}
