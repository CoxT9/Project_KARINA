package group8.karina.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import group8.karina.R;
import group8.karina.business.AccessCategories;
import group8.karina.objects.Category;

public class CategoryList extends AppCompatActivity
{

    private AccessCategories access;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        listView = (ListView) findViewById(R.id.categoryList);

        ArrayList<String> values = new ArrayList<String>();
        access = new AccessCategories();
        List<Category> categories = access.getAllCategories();

        for (Category cat : categories)
        {
            values.add(cat.getCategoryName());
        }


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);

    }


    public void addCategoryClicked(View view)
    {
        startActivity(new Intent(this, CategoryActivity.class));
    }

}
