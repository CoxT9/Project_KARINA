package group8.karina.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.R;
import group8.karina.business.AccessTransactions;
import group8.karina.business.AccessUsers;
import group8.karina.objects.User;

public class UserActivity extends AppCompatActivity implements DeleteUnassignDialogCaller
{
	private EditText editName;
	private TextView nameText;
	private AccessUsers access;
	private TextView errorText;
	private Button deleteButton;

	private User editUser;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);

		access = new AccessUsers();

		editName = (EditText) findViewById(R.id.editName);
		nameText = (TextView) findViewById(R.id.nameText);
		errorText = (TextView) findViewById(R.id.errorText);
		deleteButton = (Button) findViewById(R.id.deleteButton);

		editUser = (User) getIntent().getSerializableExtra("EditUser");

		if(editUser != null)
		{
			setUpActivityForEdit();
		}
	}

	private void setUpActivityForEdit()
	{
		editName.setText(editUser.getUserName());
		deleteButton.setVisibility(View.VISIBLE);
	}

	public void deleteButtonClicked(View view)
	{
		DeleteUnassignDialog d = new DeleteUnassignDialog(this,"user");
		d.show();
	}

	public void saveButtonClicked(View view)
	{
		if (validateForSave())
		{
			if(editUser != null)
			{
				updateExistingUser();
			}
			else
			{
				insertNewUser();
			}
		}
	}

	private void updateExistingUser()
	{
		try
		{
			editUser.setUserName(editName.getText().toString());
			access.updateUser(editUser);
			startActivity(new Intent(this, UserList.class));
			finish();
		}
		catch(Exception ex)
		{
			System.out.println("Something strange happened when updating user");
			ex.printStackTrace();
		}
	}

	private void insertNewUser()
	{
		User user = new User(editName.getText().toString());
		try
		{
			if (errorText.getVisibility() == View.VISIBLE)
			{
				errorText.setVisibility(View.GONE);
			}

			access.insertUser(user);
			startActivity(new Intent(this, UserList.class));
			finish();
		} catch (DuplicateEntryException dupEx)
		{
			writeDuplicateMessage();
		}
	}

	private boolean validateForSave()
	{
		if (editName.getText().toString() == null || editName.getText().toString().isEmpty())
		{
			errorText.setText("Required");
			errorText.setVisibility(View.VISIBLE);

			return false;
		}

		return true;
	}

	private void writeDuplicateMessage()
	{
		errorText.setText("A user already exists with that name");
		errorText.setVisibility(View.VISIBLE);
	}

	@Override
	public void deleteDialogDeleteButtonClicked()
	{
		AccessTransactions accessTransactions = new AccessTransactions();
		accessTransactions.deleteTransactionsByUserID(editUser.getUserID());

		access.deleteUserById(editUser.getUserID());
		startActivity(new Intent(this,UserList.class));
		finish();
	}

	@Override
	public void deleteDialogUnassignButtonClicked()
	{
		AccessTransactions accessTransactions = new AccessTransactions();
		accessTransactions.unassignTransactionsByUserID(editUser.getUserID());

		access.deleteUserById(editUser.getUserID());
		startActivity(new Intent(this,UserList.class));
		finish();
	}
}
