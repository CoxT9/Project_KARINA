package group8.karina.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.R;
import group8.karina.business.AccessCategories;
import group8.karina.business.AccessTransactions;
import group8.karina.objects.Category;


public class CategoryActivity extends AppCompatActivity
{
	private EditText editName;
	private TextView errorText;
	private AccessCategories access;
	private RadioButton expenseRadio;
	private TextView radioText;
	private RadioGroup expenseRadioGrp;
	private Button deleteButton;
	private Category editCategory;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);

		access = new AccessCategories();

		editName = (EditText) findViewById(R.id.editCategory);
		errorText = (TextView) findViewById(R.id.errorText);
		expenseRadio = (RadioButton) findViewById(R.id.expenseRadioButton);
		radioText = (TextView) findViewById(R.id.typeTextView);
		deleteButton = (Button) findViewById(R.id.deleteButton);

		expenseRadioGrp = (RadioGroup) findViewById(R.id.expenseRadioGrp);

		editCategory = (Category) getIntent().getSerializableExtra("EditCategory");
		if(editCategory != null)
		{
			setUpActivityForEdit();
		}
	}

	public void setUpActivityForEdit()
	{
		radioText.setVisibility(View.GONE);
		expenseRadioGrp.setVisibility(View.GONE);
		editName.setText(editCategory.getCategoryName());
		deleteButton.setVisibility(View.VISIBLE);
	}

	public void saveButtonClicked(View view)
	{
		if (validateForSave())
		{
			if(editCategory != null)
			{
				updateExistingCategory();
			}
			else
			{
				insertNewCategory();
			}
		}
	}

	public void updateExistingCategory()
	{
		try
		{
			editCategory.setCategoryName(editName.getText().toString());
			access.updateCategory(editCategory);
			startActivity(new Intent(this, MainActivity.class));
		}
		catch(Exception ex)
		{
			System.out.println("Something strange happened when updating category");
			ex.printStackTrace();
		}
	}

	public void insertNewCategory()
	{
		Category category = new Category(editName.getText().toString(), expenseRadio.isActivated());
		try
		{
			if (errorText.getVisibility() == View.VISIBLE)
			{
				errorText.setVisibility(View.GONE);
			}

			access.insertCategory(category);
			startActivity(new Intent(this, MainActivity.class));
		} catch (DuplicateEntryException dupEx)
		{
			writeDuplicateMessage();
		}
	}

	public void deleteButtonClicked(View view)
	{
		AccessTransactions accessTransactions = new AccessTransactions();
		accessTransactions.deleteTransactionsByCategoryID(editCategory.getCategoryID());

		access.deleteCategoryById(editCategory.getCategoryID());
		startActivity(new Intent(this, MainActivity.class));
	}

	private boolean validateForSave()
	{
		if (editName.getText().toString() == null || editName.getText().toString().isEmpty())
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
