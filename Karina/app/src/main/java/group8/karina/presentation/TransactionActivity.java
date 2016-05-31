package group8.karina.presentation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import group8.karina.R;
import group8.karina.business.AccessCategories;
import group8.karina.business.AccessTransactions;
import group8.karina.business.AccessUsers;
import group8.karina.objects.Category;
import group8.karina.objects.User;

/**
 * Created by Malcolm on 2016-05-30.
 */
public class TransactionActivity extends AppCompatActivity
{
    private EditText title;
    private EditText value;
    //TODO: datepicker
    private Spinner userSpinner;
    private Spinner categorySpinner;
    private EditText comments;
    private TextView userSpinnerText;
    private TextView categorySpinnerText;
    private AccessUsers accessUsers;
    private AccessCategories accessCategories;
    private TextView titleErrorText;
    private TextView valueErrorText;

    protected AccessTransactions accessTransactions;
    protected List<User> users;
    protected List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        accessUsers = new AccessUsers();
        accessCategories = new AccessCategories();
        accessTransactions = new AccessTransactions();

        title = (EditText) findViewById(R.id.titleText);
        value = (EditText) findViewById(R.id.valueText);
        userSpinner = (Spinner) findViewById(R.id.userSpinner);
        categorySpinner = (Spinner)findViewById(R.id.categorySpinner);
        comments = (EditText)findViewById(R.id.commentText);
        userSpinnerText = (TextView)findViewById(R.id.userSpinnerText);
        categorySpinnerText = (TextView)findViewById(R.id.categorySpinnerText);
        titleErrorText = (TextView)findViewById(R.id.titleErrorText);
        valueErrorText = (TextView)findViewById(R.id.valueErrorText);

        fillUserSpinner();
        fillCategorySpinner();
    }

    private void fillCategorySpinner()
    {
        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();
        categories = accessCategories.getAllCategories();

        for (Category cat : categories)
        {
            list.add(cat.getCategoryName());
        }

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String item = parent.getItemAtPosition(position).toString();

                if(categorySpinnerText != null) //have to do null check to avoid problems when initially opening the page
                {
                    categorySpinnerText.setText(item);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    private void fillUserSpinner()
    {
        ArrayAdapter<String> adapter;
        List<String> list;

        users = accessUsers.getUsers();

        list = new ArrayList<String>();

        for (User user : users)
        {
            list.add(user.getUserName());
        }

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSpinner.setAdapter(adapter);

        userSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String item = parent.getItemAtPosition(position).toString();

                if(userSpinnerText != null) //have to do null check to avoid problems when initially opening the page
                {
                    userSpinnerText.setText(item);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    protected boolean validateForSave()
    {
        boolean result = true;

        if(title.getText() == null || title.getText().toString().isEmpty())
        {
            titleErrorText.setText("(required)");
            titleErrorText.setTextColor(Color.RED);
            result = false;
        }

        if(value.getText() == null || value.getText().toString().isEmpty())
        {
            valueErrorText.setText("(required)");
            valueErrorText.setTextColor(Color.RED);
            result = false;
        }
        else if(!value.getText().toString().matches("[0-9]*\\.[0-9][0-9]"))
        {
            valueErrorText.setText("invalid money amount");
            valueErrorText.setTextColor(Color.RED);
            result = false;
        }

        return result;
    }

    protected int getSelectedUser()
    {
        String selectedUser = (String) userSpinner.getSelectedItem();

        for(User u : users)
        {
            if(u.getUserName().equals(selectedUser))
            {
                return u.getUserID();
            }
        }

        return -1;
    }

    protected int getSelectedCategory()
    {
        String selectedCategory = (String) categorySpinner.getSelectedItem();

        for(Category c : categories)
        {
            if(c.getCategoryName().equals(selectedCategory))
            {
                return c.getCategoryID();
            }
        }

        return -1;
    }

    protected double getEnteredAmount()
    {
        return Double.parseDouble(value.getText().toString());
    }

    protected String getComments()
    {
        return comments.getText().toString();
    }
}
