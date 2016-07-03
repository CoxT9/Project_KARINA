package group8.karina.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

import group8.karina.R;
import group8.karina.business.AccessCategories;
import group8.karina.objects.Category;

public class CategoryList extends AppCompatActivity
{

	private AccessCategories access;
    private ListView incomeList;
	private ListView expenseList;
	private TextView incomeTitle;
	private TextView expenseTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_list);

		incomeList = (ListView) findViewById(R.id.incomeCategoryList);
		expenseList = (ListView) findViewById(R.id.expenseCategoryList);

		incomeTitle = (TextView) findViewById(R.id.incomeCategoryTitle);
		expenseTitle = (TextView) findViewById(R.id.expenseCategoryTitle);

		access = new AccessCategories();
		List<Category> incomeCategories = access.getIncomeCategories();
		List<Category> expenseCategories = access.getExpenseCategories();

		populateListView(incomeCategories, true, access.getIncomeCategories().isEmpty());
		populateListView(expenseCategories, false, access.getExpenseCategories().isEmpty());

		setListViewOnItemClicked();
	}

	public void populateListView(List<Category> categories, boolean isIncome, boolean isEmpty)
	{
		Iterator<Category> categoryIterator = categories.iterator();
		Category c;

		while(categoryIterator.hasNext())
		{
			c = categoryIterator.next();

			//user id of 1 means the default user, which we dont want the user to be able to edit or remove
			if(c.getCategoryID() == 1)
			{
				categoryIterator.remove();
				break; //no need to look at everything else in the list
			}
		}

		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, categories);
		if(isIncome)
		{
			if(isEmpty)
			{
				incomeTitle.setVisibility(View.GONE);
			}
			else
			{
				incomeList.setAdapter(adapter);
			}
		}
		else
		{
			if(isEmpty)
			{
				expenseTitle.setVisibility(View.GONE);
			}
			else
			{
				expenseList.setAdapter(adapter);
			}
		}
	}


	public void setListViewOnItemClicked()
	{
		incomeList.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
									long id)
			{
				Category selectedCategory = (Category) parent.getItemAtPosition(position);
				Intent editCategory = new Intent(CategoryList.this,CategoryActivity.class);
				editCategory.putExtra("EditCategory",selectedCategory);

				startActivity(editCategory);
				finish();
			}
		});
		expenseList.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
									long id)
			{
				Category selectedCategory = (Category) parent.getItemAtPosition(position);
				Intent editCategory = new Intent(CategoryList.this,CategoryActivity.class);
				editCategory.putExtra("EditCategory",selectedCategory);

				startActivity(editCategory);
				finish();
			}
		});
	}


	public void addCategoryClicked(View view)
	{
		startActivity(new Intent(this, CategoryActivity.class));
		finish();
	}

}
