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
import java.util.List;

import group8.karina.R;
import group8.karina.application.Services;
import group8.karina.business.AccessCategories;
import group8.karina.objects.Category;

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
		access = new AccessCategories(Services.getDataAccess());
		List<Category> categories = access.getAllCategories();
		// Originally the list view showed only strings (Category name). This caused problems with extracting categories and was inconsistent with the rest of the design
		// Let's re-visit why these were being saved as Strings (Comment attribute in category?)
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
