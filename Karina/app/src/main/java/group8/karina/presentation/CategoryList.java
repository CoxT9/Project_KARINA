package group8.karina.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import group8.karina.R;
import group8.karina.business.AccessCategories;
import group8.karina.objects.Category;
import group8.karina.objects.User;

public class CategoryList extends AppCompatActivity
{

	private RelativeLayout card;
	private AccessCategories access;
    private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_list);

		listView = (ListView) findViewById(R.id.categoryList);

		populateListView();
		setListViewOnItemClicked();

	}

	public void populateListView()
	{
		access = new AccessCategories();
		List<Category> categories = access.getAllCategories();

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
		listView.setAdapter(adapter);
	}

	public void setListViewOnItemClicked()
	{
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
									long id)
			{
				Category selectedCategory = (Category) parent.getItemAtPosition(position);
				Intent editCategory = new Intent(CategoryList.this,CategoryActivity.class);
				editCategory.putExtra("EditCategory",selectedCategory);

				startActivity(editCategory);
			}
		});
	}


	public void addCategoryClicked(View view)
	{
		startActivity(new Intent(this, CategoryActivity.class));
	}

}
