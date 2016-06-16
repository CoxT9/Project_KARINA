package group8.karina.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import group8.karina.R;
import group8.karina.business.AccessCategories;
import group8.karina.business.AccessTransactions;
import group8.karina.business.AccessUsers;
import group8.karina.objects.Category;
import group8.karina.objects.Transaction;
import group8.karina.objects.User;

public abstract class TransactionActivity extends AppCompatActivity
{
    private EditText value;
    private Spinner userSpinner;
    private Spinner categorySpinner;
    private EditText comments;
    private TextView userSpinnerText;
    private TextView categorySpinnerText;
    private AccessUsers accessUsers;
    private EditText setDate;

    protected AccessCategories accessCategories;
    protected AccessTransactions accessTransactions;
    protected List<User> users;
    protected List<Category> categories;
    protected TextView errorValue;
    protected TextView errorDate;
	protected Button deleteButton;
	protected Transaction editTransaction;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_income);

		accessUsers = new AccessUsers();
		accessCategories = new AccessCategories();
		accessTransactions = new AccessTransactions();

        value = (EditText) findViewById(R.id.valueText);
        userSpinner = (Spinner) findViewById(R.id.userSpinner);
        categorySpinner = (Spinner)findViewById(R.id.categorySpinner);
        comments = (EditText)findViewById(R.id.commentText);
        userSpinnerText = (TextView)findViewById(R.id.userSpinnerText);
        categorySpinnerText = (TextView)findViewById(R.id.categorySpinnerText);
        setDate = (EditText)findViewById(R.id.setDate);
        errorValue = (TextView) findViewById(R.id.errorValue);
        errorDate = (TextView) findViewById(R.id.errorDate);
		deleteButton = (Button) findViewById(R.id.deleteButton);

		editTransaction = (Transaction) getIntent().getSerializableExtra("EditTransaction");

		if(editTransaction != null)
		{
			setUpActivityForEdit();
		}

		fillUserSpinner();
		fillCategorySpinner();
	}

	private void setUpActivityForEdit()
	{
		String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(editTransaction.getDate());

		value.setText(String.valueOf(editTransaction.getAmount()));
		setDate.setText(formattedDate);
		comments.setText(editTransaction.getComments());
		deleteButton.setVisibility(View.VISIBLE);
	}

	private void fillCategorySpinner()
	{
		ArrayAdapter<String> adapter;
		List<String> list;

		list = new ArrayList<String>();
		categories = getCategories();

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

				if (categorySpinnerText != null) //have to do null check to avoid problems when initially opening the page
				{
					if(editTransaction != null)
					{
						categorySpinnerText.setText(accessCategories.getCategoryByID(editTransaction.getCategoryID()).toString());
					}
					else
					{
						categorySpinnerText.setText(item);
					}
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

				if (userSpinnerText != null) //have to do null check to avoid problems when initially opening the page
				{
					if(editTransaction != null)
					{
						userSpinnerText.setText(accessUsers.getUserByID(editTransaction.getUserID()).toString());
					}
					else
					{
						userSpinnerText.setText(item);
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});
	}

	protected void updateExistingTransaction(boolean isExpense)
	{
		try
		{
			editTransaction.setDate(getSelectedDate());
			editTransaction.setUserID(getSelectedUser());
			editTransaction.setIsExpense(isExpense);
			editTransaction.setAmount(getEnteredAmount());
			editTransaction.setCategoryID(getSelectedCategory());
			editTransaction.setComments(getComments());

			accessTransactions.updateTransaction(editTransaction);
			startActivity(new Intent(this, MainActivity.class));
		}
		catch(Exception ex)
		{
			System.out.println("Something strange happened when updating user");
			ex.printStackTrace();
		}
	}

	protected void insertNewTransaction(boolean isExpense)
	{
		Transaction t = new Transaction(getSelectedDate(), getSelectedUser(), isExpense, getEnteredAmount(), getSelectedCategory(), getComments());
		accessTransactions.insertTransaction(t);

		startActivity(new Intent(this, MainActivity.class));
	}

	protected boolean validateForSave()
	{
		boolean result = true;

        if(value.getText() == null || value.getText().toString().isEmpty())
		{
			errorValue.setText("Required");
			errorValue.setVisibility(View.VISIBLE);
		}
        else if(!value.getText().toString().matches("[0-9]+(\\.[0-9][0-9])?"))
		{
			errorValue.setText("Value must be a dollar amount");
			errorValue.setVisibility(View.VISIBLE);

			result = false;
		}
		else if(!value.getText().toString().matches("[0-9]+(\\.[0-9][0-9])?"))
		{
			errorValue.setText("Value must be a dollar amount");
			errorValue.setVisibility(View.VISIBLE);

			result = false;
		}
		else if(!amountWithinReason(value.getText().toString()))
		{
			errorValue.setText("Value is too large");
			errorValue.setVisibility(View.VISIBLE);

			result = false;
		}
        else
        {
            errorValue.setVisibility(View.GONE);
        }

        if(setDate.getText() == null || setDate.getText().toString().isEmpty())
        {
            errorDate.setText("Required");
            errorDate.setVisibility(View.VISIBLE);

            result = false;
        }
        else if(!setDate.getText().toString().matches("[0-3][0-9]/[0-1]?[1-9]/[0-9][0-9][0-9][0-9]"))
		{
			errorDate.setText("Date must be DD/MM/YYYY");
			errorDate.setVisibility(View.VISIBLE);

			result = false;
		}
		else if(!isValidDate(setDate.getText().toString()))
		{
			errorDate.setText("Date must be valid");
			errorDate.setVisibility(View.VISIBLE);

			result = false;
		}
        else
        {
            errorDate.setVisibility(View.GONE);
        }

		return result;
	}

	private boolean amountWithinReason(String amount)
	{
		final double BILLION = 1000000000;
		boolean result = false;

		if (!(amount == null) && !amount.isEmpty())//make sure it's parsable
		{
			if (Double.parseDouble(amount)<=BILLION)
			{
				result = true;
			}
		}
		return result;
	}

	private boolean isValidDate(String date)
	{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);

		try
		{
			format.parse(date.trim());
		} catch (ParseException parseEx)
		{
			return false;
		}

		return true;
	}

	protected int getSelectedUser()
	{
		String selectedUser = (String) userSpinner.getSelectedItem();

		for (User u : users)
		{
			if (u.getUserName().equals(selectedUser))
			{
				return u.getUserID();
			}
		}

		return -1;
	}

	protected int getSelectedCategory()
	{
		String selectedCategory = (String) categorySpinner.getSelectedItem();

		for (Category c : categories)
		{
			if (c.getCategoryName().equals(selectedCategory))
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

	protected Date getSelectedDate()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		Date date;

		try
		{
			date = dateFormat.parse(setDate.getText().toString());
		} catch (Exception e)
		{
			date = null;
		}

		return date;
	}

	protected List<Category> getCategories()
	{
		return accessCategories.getAllCategories();
	}
}
