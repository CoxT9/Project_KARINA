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

        ArrayList<String> values = new ArrayList<String>();
        access = new AccessUsers();
        List<User> users = access.getUsers();

        for (User us : users)
        {
            values.add(us.getUserName());
        }


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);
    }


	public void addUserClicked(View view)
	{
		startActivity(new Intent(this, UserActivity.class));
	}
}
