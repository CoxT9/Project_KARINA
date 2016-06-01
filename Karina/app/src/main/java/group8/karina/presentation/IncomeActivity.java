package group8.karina.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import group8.karina.R;


public class IncomeActivity extends AppCompatActivity
{

    private EditText title;
    private EditText value;
    private EditText setDate;
    private Spinner userSpinner;
    private Spinner categorySpinner;
    private EditText comments;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        title = (EditText) findViewById(R.id.titleText);
        value = (EditText) findViewById(R.id.valueText);
        userSpinner = (Spinner) findViewById(R.id.userSpinner);
        categorySpinner = (Spinner)findViewById(R.id.categorySpinner);
        comments = (EditText)findViewById(R.id.commentText);
        setDate = (EditText) findViewById(R.id.setDate);

    }

    public void setDateClicked()
    {

    }

    public void saveButtonClicked(View view)
    {

    }
}
