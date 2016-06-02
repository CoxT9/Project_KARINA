package group8.karina.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import group8.karina.R;
import group8.karina.business.AccessCategories;

public class UserList extends AppCompatActivity
{

	private RelativeLayout card;
	private AccessCategories access;
	private TextView name;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);

		card = (RelativeLayout) findViewById(R.id.userCard);
		name = (TextView) findViewById(R.id.userName);

		access = new AccessCategories();
	}


	public void addUserClicked(View view)
	{
		startActivity(new Intent(this, UserActivity.class));
	}
}
