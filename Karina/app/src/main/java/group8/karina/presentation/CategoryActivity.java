package group8.karina.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import group8.karina.R;

/**
 * Created by Mike on 5/27/2016.
 */
public class CategoryActivity extends AppCompatActivity
{
    private EditText editCategory;
    private TextView addCategoryText;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        editCategory = (EditText) findViewById(R.id.editCategory);
        addCategoryText = (TextView) findViewById(R.id.addCategoryText);
    }

    public void saveButtonClicked(View view)
    {

    }

}
