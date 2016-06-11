package group8.karina.presentation;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import group8.karina.R;

public class DeletionDialog extends Dialog
{
	private DeleteDialogCaller caller;
	private Button deleteAllButton;
	private Button unassignButton;
	private Button cancelButton;
	private TextView dialogText;
	private String typeText;

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

		deleteAllButton = (Button) findViewById(R.id.deleteButton);
		unassignButton = (Button) findViewById(R.id.unassignButton);
		cancelButton = (Button) findViewById(R.id.cancelButton);
		dialogText = (TextView) findViewById(R.id.dialogText);

		setDeleteAllOnClickListener();
		setUnAssignOnClickListener();
		setCancelOnClickListener();
		setDialogText();
	}

	private void setDialogText()
	{
		dialogText.setText("Would you like to delete all incomes and expenses associated with this "+typeText+", or un-assign them?");
	}

	private void setCancelOnClickListener()
	{
		cancelButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				DeletionDialog.this.hide();
			}
		});
	}

	private void setUnAssignOnClickListener()
	{
		unassignButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				DeletionDialog.this.hide();
				caller.deleteDialogUnassignButtonClicked();
			}
		});
	}

	private void setDeleteAllOnClickListener()
	{
		deleteAllButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				DeletionDialog.this.hide();
				caller.deleteDialogDeleteButtonClicked();
			}
		});
	}


}
