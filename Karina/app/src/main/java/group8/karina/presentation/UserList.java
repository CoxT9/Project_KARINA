package group8.karina.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import group8.karina.R;
import group8.karina.business.AccessUsers;
import group8.karina.objects.User;

public class UserList extends AppCompatActivity
{

    private ListView listView;
    private AccessUsers access;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);

        listView = (ListView) findViewById(R.id.userList);

		populateListView();
		setListViewOnItemClicked();
    }

	private void setListViewOnItemClicked()
	{
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
									long id)
			{
				User selectedUser = (User) parent.getItemAtPosition(position);
				Intent editUser = new Intent(UserList.this,UserActivity.class);
				editUser.putExtra("EditUser",selectedUser);

				startActivity(editUser);
			}
		});
	}

	public void addUserClicked(View view)
	{
		startActivity(new Intent(this, UserActivity.class));
	}

	private void populateListView()
	{
		access = new AccessUsers();
		List<User> users = access.getUsers();

		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, users);
		listView.setAdapter(adapter);
	}
}
