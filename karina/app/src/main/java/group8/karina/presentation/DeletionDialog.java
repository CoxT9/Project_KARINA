package group8.karina.presentation;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import group8.karina.R;

public class DeletionDialog extends Dialog
{
	private DeleteDialogCaller caller;
	protected Button deleteButton;
	protected Button cancelButton;
	protected TextView dialogText;
	protected String typeText;

	public <T extends Activity, DeleteDialogCaller> DeletionDialog(T a,String typeText)
	{
		super(a);

		caller = (group8.karina.presentation.DeleteDialogCaller) a;
		this.typeText = typeText;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_deletion);

		deleteButton = (Button) findViewById(R.id.deleteButton);
		cancelButton = (Button) findViewById(R.id.cancelButton);
		dialogText = (TextView) findViewById(R.id.dialogText);

		setDeleteOnClickListener();
		setCancelOnClickListener();
		setDialogText();
	}

	private void setDialogText()
	{
		dialogText.setText("Would you like to delete this "+typeText+" Transaction?");
	}

	private void setCancelOnClickListener()
	{
		cancelButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				DeletionDialog.this.dismiss();
			}
		});
	}

	private void setDeleteOnClickListener()
	{
		deleteButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				DeletionDialog.this.dismiss();
				caller.deleteDialogDeleteButtonClicked();
			}
		});
	}

}
