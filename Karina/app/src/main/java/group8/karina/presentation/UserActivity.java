package group8.karina.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.String;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.R;
import group8.karina.business.AccessUsers;
import group8.karina.objects.User;

public class UserActivity extends AppCompatActivity
{
    private EditText editName;
    private TextView nameText;
    private AccessUsers access;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        access = new AccessUsers();

        editName = (EditText) findViewById(R.id.editName);
        nameText = (TextView) findViewById(R.id.nameText);
    }

    public void saveButtonClicked(View view)
    {
        if(validateForSave())
        {
            User user = new User(editName.getText().toString());
            try
            {
                access.insertUser(user);
                startActivity(new Intent(this, MainActivity.class));
            }
            catch(DuplicateEntryException dupEx)
            {
                writeDuplicateMessage();
            }

        }
    }

    private boolean validateForSave()
    {
        if(editName.getText().toString() == null || editName.getText().toString().isEmpty())
        {
            nameText.setText("Name (required)");
            nameText.setTextColor(Color.RED);

            return false;
        }

        return true;
    }

    private void writeDuplicateMessage()
    {
        nameText.setText("Name (a user already exists with that name)");
        nameText.setTextColor(Color.RED);
    }
}
