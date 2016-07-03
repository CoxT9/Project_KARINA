package group8.karina.presentation;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import group8.karina.R;

public class TransactionSortDialog extends Dialog{

	private final int CATEGORIES = 0;
	private final int USERS = 1;
	private final int DATE = 2;

	private Button okButton;
	private RadioButton sortCategories;
	private RadioButton sortUsers;
	private RadioButton sortDate;
	private RadioGroup sortRadioGrp;
	private TransactionSortDialogCaller caller;


	public TransactionSortDialog(Context context) {
		super(context);
		caller = (group8.karina.presentation.TransactionSortDialogCaller) context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_transaction_sort_dialog);

		//radio buttons
		sortCategories = (RadioButton) findViewById(R.id.categoryRadioButton);
		sortUsers = (RadioButton) findViewById(R.id.userRadioButton);
		sortDate = (RadioButton) findViewById(R.id.dateRadioButton);
		sortRadioGrp = (RadioGroup) findViewById(R.id.sortRadioGrp);

		//ok button
		okButton = (Button) findViewById(R.id.okButton);

		setOkOnClickListener();
	}

	private void setOkOnClickListener()
	{
		okButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TransactionSortDialog.this.hide();
				int result = getRadioOption();
				caller.sortOkButtonClicked(result);
			}
		});
	}

	private int getRadioOption()
	{
		int result;
		if(sortCategories.isChecked())
		{
			result = CATEGORIES;
		}
		else if(sortUsers.isChecked())
		{
			result = USERS;
		}
		else
		{
			result = DATE;
		}
		return result;
	}

}
