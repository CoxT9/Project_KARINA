package group8.karina.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import group8.karina.R;

public class DeleteUnassignDialog extends DeletionDialog
{

	private DeleteUnassignDialogCaller deleteUnassignCaller;
	private Button deleteAllButton;
	private Button unassignButton;

	public <T extends Activity, DeleteUnassignDialogCaller> DeleteUnassignDialog(T a, String typeText)
	{
		super(a, typeText);

		deleteUnassignCaller = (group8.karina.presentation.DeleteUnassignDialogCaller) a;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		deleteAllButton = (Button) findViewById(R.id.deleteAllButton);
		unassignButton = (Button) findViewById(R.id.unassignButton);

		deleteButton.setVisibility(View.GONE);
		deleteAllButton.setVisibility(View.VISIBLE);
		unassignButton.setVisibility(View.VISIBLE);

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
				DeleteUnassignDialog.this.dismiss();
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
				DeleteUnassignDialog.this.dismiss();
				deleteUnassignCaller.deleteDialogUnassignButtonClicked();
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
				DeleteUnassignDialog.this.dismiss();
				deleteUnassignCaller.deleteDialogDeleteButtonClicked();
			}
		});
	}


}
