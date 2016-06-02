package group8.karina.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.R;
import group8.karina.business.AccessCategories;
import group8.karina.objects.Category;

/**
 * Created by Mike on 5/27/2016.
 */
public class CategoryActivity extends AppCompatActivity
{
    private EditText editCategory;
    private TextView addCategoryText;
    private TextView errorText;
    private AccessCategories access;
    private RadioButton expenseRadio;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        access = new AccessCategories();

        editCategory = (EditText) findViewById(R.id.editCategory);
        addCategoryText = (TextView) findViewById(R.id.addCategoryText);
        errorText = (TextView) findViewById(R.id.errorText);
        expenseRadio = (RadioButton) findViewById(R.id.expenseRadioButton);
    }

    public void saveButtonClicked(View view)
    {
        if(validateForSave())
        {
            Category category = new Category(editCategory.getText().toString(),expenseRadio.isChecked());

            try
            {
                access.insertCategory(category);
                startActivity(new Intent(this, MainActivity.class));
            }
            catch(DuplicateEntryException dupEx)
            {
                writeDuplicateMessage();
            }

        }
    }

    private boolean validateForSave()
    {
        if(editCategory.getText().toString() == null || editCategory.getText().toString().isEmpty())
        {
            errorText.setText("Cannot have a blank name");
            errorText.setTextColor(Color.RED);

            return false;
        }

        return true;
    }

    private void writeDuplicateMessage()
    {
        errorText.setText("A category already exists with that name");
        errorText.setTextColor(Color.RED);
    }

}
