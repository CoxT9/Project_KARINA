package group8.karina.AcceptanceTests;

import group8.karina.R;
import group8.karina.TestHelpers.TestDataAccessObject;
import group8.karina.application.DatabaseService;
import group8.karina.presentation.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;


public class CompareSpendingBetweenUsersTests extends ActivityInstrumentationTestCase2<MainActivity>
{
  	private Solo solo;
  	
  	public CompareSpendingBetweenUsersTests() {
		super(MainActivity.class);
  	}

	public void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();

		DatabaseService.setDatabase(new TestDataAccessObject(DatabaseService.dbName));
		DatabaseService.openDataAccess();
	}

	@Override
	public void tearDown() throws Exception
	{
		solo.finishOpenedActivities();
		DatabaseService.closeDataAccess();
		super.tearDown();
	}

	public void testRun()
	{
        //Wait for activity: 'group8.karina.presentation.MainActivity'
		solo.waitForActivity(group8.karina.presentation.MainActivity.class, 2000);
        //Set default small timeout to 13754 milliseconds
		Timeout.setSmallTimeout(13754);
        //Click on User
		solo.clickOnView(solo.getView(group8.karina.R.id.userListButton));
        //Wait for activity: 'group8.karina.presentation.UserList'
		assertTrue("group8.karina.presentation.UserList is not found!", solo.waitForActivity(group8.karina.presentation.UserList.class));
        //Click on Add User
		solo.clickOnView(solo.getView(group8.karina.R.id.addUserButton));
        //Wait for activity: 'group8.karina.presentation.UserActivity'
		assertTrue("group8.karina.presentation.UserActivity is not found!", solo.waitForActivity(group8.karina.presentation.UserActivity.class));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(group8.karina.R.id.editName));
        //Enter the text: 'Bob'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.editName));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.editName), "Bob");
        //Click on Save
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Wait for activity: 'group8.karina.presentation.UserList'
		assertTrue("group8.karina.presentation.UserList is not found!", solo.waitForActivity(group8.karina.presentation.UserList.class));
        //Click on Bob
		solo.clickOnView(solo.getView(android.R.id.text1, 3));
        //Wait for activity: 'group8.karina.presentation.UserActivity'
		assertTrue("group8.karina.presentation.UserActivity is not found!", solo.waitForActivity(group8.karina.presentation.UserActivity.class));
        //Press menu back key
		solo.goBack();
        //Wait for activity: 'group8.karina.presentation.UserList'
		assertTrue("group8.karina.presentation.UserList is not found!", solo.waitForActivity(group8.karina.presentation.UserList.class));
        //Click on Add User
		solo.clickOnView(solo.getView(group8.karina.R.id.addUserButton));
        //Wait for activity: 'group8.karina.presentation.UserActivity'
		assertTrue("group8.karina.presentation.UserActivity is not found!", solo.waitForActivity(group8.karina.presentation.UserActivity.class));
        //Enter the text: 'Bob'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.editName));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.editName), "Bob");
        //Click on Save
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Press menu back key
		solo.goBack();
        //Wait for activity: 'group8.karina.presentation.UserList'
		assertTrue("group8.karina.presentation.UserList is not found!", solo.waitForActivity(group8.karina.presentation.UserList.class));
        //Press menu back key
		solo.goBack();
        //Click on Expense
		solo.clickOnView(solo.getView(group8.karina.R.id.expenseListButton));
        //Wait for activity: 'group8.karina.presentation.ExpenseList'
		assertTrue("group8.karina.presentation.ExpenseList is not found!", solo.waitForActivity(group8.karina.presentation.ExpenseList.class));
        //Click on Add Expense
		solo.clickOnView(solo.getView(group8.karina.R.id.addExpenseButton));
        //Wait for activity: 'group8.karina.presentation.ExpenseActivity'
		assertTrue("group8.karina.presentation.ExpenseActivity is not found!", solo.waitForActivity(group8.karina.presentation.ExpenseActivity.class));
        //Enter the text: '9.00'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText), "9.00");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(group8.karina.R.id.setDate));
        //Enter the text: '12/12/2016'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.setDate));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.setDate), "12/12/2016");

		View view1 = solo.getView(Spinner.class, 0);
		solo.clickOnView(view1);
		solo.scrollToTop();
		solo.clickOnView(solo.getView(TextView.class, 4));
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
		solo.clickOnView(solo.getView(android.R.id.text1, 2));
	}
}
