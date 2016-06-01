package group8.karina.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import group8.karina.R;
import group8.karina.business.AccessCategories;

/**
 * Created by Mike on 5/30/2016.
 */
public class CategoryList extends AppCompatActivity{

    private RelativeLayout card;
    private AccessCategories access;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        card = (RelativeLayout) findViewById(R.id.categoryCard);

        access = new AccessCategories();
    }


    public void addCategoryClicked(View view)
    {
        startActivity(new Intent(this, CategoryActivity.class));
    }

}
