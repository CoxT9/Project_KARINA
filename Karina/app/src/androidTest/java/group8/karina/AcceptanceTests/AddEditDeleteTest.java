/*
Add/Edit/Delete Objects

//User
Add a new User "Mitch"
Change user "Bran" to "Brandon"

//Category
Add a new Expense Category "Food"
Verify "Food" is in Expense list
Add a new Income Category "Gifts"
Verify "Presents" is in Income list
Edit Category "Presents", change to "Gifts"

//Expense
Add $50 to "Food" and "Mitch
Edit $25 to "Weapons" and "Brandon"
Delete $25
Add $75 to "Food" and "Mitch"
Edit $75, hit delete, then hit Cancel

//Income
Add $50 to "Gifts" and "Mitch"
Edit $25 to "Income" and "Brandon"
Delete $25
Add $75 to "Gifts" and "Mitch"

//Delete
Delete "Food" and Remove All Associated Transactions
Delete "Gifts" and Set to Unassigned
Check Expenses, that all were removed
Check Income, that none were removed
Check one transaction, make sure it's set to Default
Delete "Mitch" and delete all Associated Transactions
Verify that all Income/Expenses are removed

Quit
 */

package group8.karina.AcceptanceTests;

import group8.karina.TestHelpers.TestDataAccessObject;
import group8.karina.application.DatabaseService;
import group8.karina.presentation.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;


public class AddEditDeleteTest extends ActivityInstrumentationTestCase2<MainActivity> {
	private Solo solo;

	public AddEditDeleteTest() {
		super(MainActivity.class);
	}

	public void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();

		DatabaseService.setDatabase(new TestDataAccessObject(DatabaseService.dbName));
		DatabaseService.openDataAccess();
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
		super.tearDown();
	}
  
	public void testRun() {
		View view1; //to be used for testing the spinners
        //Wait for activity: 'group8.karina.presentation.MainActivity'
		solo.waitForActivity(group8.karina.presentation.MainActivity.class, 2000);
        //Click on User
		solo.clickOnView(solo.getView(group8.karina.R.id.userListButton));
        //Wait for activity: 'group8.karina.presentation.UserList'
		assertTrue("group8.karina.presentation.UserList is not found!", solo.waitForActivity(group8.karina.presentation.UserList.class));
        //Click on Add User
		solo.clickOnView(solo.getView(group8.karina.R.id.addUserButton));
        //Wait for activity: 'group8.karina.presentation.UserActivity'
		assertTrue("group8.karina.presentation.UserActivity is not found!", solo.waitForActivity(group8.karina.presentation.UserActivity.class));
        //Enter the text: 'Mitch'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.editName));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.editName), "Mitch");
        //Click on Save
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Wait for activity: 'group8.karina.presentation.UserList'
		assertTrue("group8.karina.presentation.UserList is not found!", solo.waitForActivity(group8.karina.presentation.UserList.class));
        //Click on Bran
		solo.clickOnView(solo.getView(android.R.id.text1, 2));
        //Wait for activity: 'group8.karina.presentation.UserActivity'
		assertTrue("group8.karina.presentation.UserActivity is not found!", solo.waitForActivity(group8.karina.presentation.UserActivity.class));
        //Click on Bran
		solo.clickOnView(solo.getView(group8.karina.R.id.editName));
        //Enter the text: 'Brandon'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.editName));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.editName), "Brandon");
        //Click on Save
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Wait for activity: 'group8.karina.presentation.UserList'
		assertTrue("group8.karina.presentation.UserList is not found!", solo.waitForActivity(group8.karina.presentation.UserList.class));
        //Press menu back key
		solo.goBack();
        //Click on Category
		solo.clickOnView(solo.getView(group8.karina.R.id.categoryListButton));
        //Wait for activity: 'group8.karina.presentation.CategoryList'
		assertTrue("group8.karina.presentation.CategoryList is not found!", solo.waitForActivity(group8.karina.presentation.CategoryList.class));
        //Click on Add Category
		solo.clickOnView(solo.getView(group8.karina.R.id.addCategoryButton));
        //Wait for activity: 'group8.karina.presentation.CategoryActivity'
		assertTrue("group8.karina.presentation.CategoryActivity is not found!", solo.waitForActivity(group8.karina.presentation.CategoryActivity.class));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(group8.karina.R.id.editCategory));
        //Enter the text: 'Food'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.editCategory));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.editCategory), "Food");
        //Click on Save
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Wait for activity: 'group8.karina.presentation.CategoryList'
		assertTrue("group8.karina.presentation.CategoryList is not found!", solo.waitForActivity(group8.karina.presentation.CategoryList.class));
        //Click on Add Category
		solo.clickOnView(solo.getView(group8.karina.R.id.addCategoryButton));
        //Wait for activity: 'group8.karina.presentation.CategoryActivity'
		assertTrue("group8.karina.presentation.CategoryActivity is not found!", solo.waitForActivity(group8.karina.presentation.CategoryActivity.class));
        //Click on Income
		solo.clickOnView(solo.getView(group8.karina.R.id.incomeRadioButton2));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(group8.karina.R.id.editCategory));
        //Enter the text: 'Presents'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.editCategory));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.editCategory), "Presents");
        //Click on Save
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Wait for activity: 'group8.karina.presentation.CategoryList'
		assertTrue("group8.karina.presentation.CategoryList is not found!", solo.waitForActivity(group8.karina.presentation.CategoryList.class));
        //Click on Presents
		solo.clickOnView(solo.getView(android.R.id.text1, 1));
        //Wait for activity: 'group8.karina.presentation.CategoryActivity'
		assertTrue("group8.karina.presentation.CategoryActivity is not found!", solo.waitForActivity(group8.karina.presentation.CategoryActivity.class));
        //Click on Presents
		solo.clickOnView(solo.getView(group8.karina.R.id.editCategory));
        //Enter the text: 'Gifts'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.editCategory));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.editCategory), "Gifts");
        //Click on Save
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Wait for activity: 'group8.karina.presentation.CategoryList'
		assertTrue("group8.karina.presentation.CategoryList is not found!", solo.waitForActivity(group8.karina.presentation.CategoryList.class));
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
        //Enter the text: '50'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText), "50");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(group8.karina.R.id.setDate));
        //Enter the text: '01/01/2016'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.setDate));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.setDate), "01/01/2016");
		//Select "Mitch" from Spinner
		view1 = solo.getView(Spinner.class, 0);
		solo.clickOnView(view1);
		solo.scrollToTop();
		solo.clickOnView(solo.getView(TextView.class, 5));
		//Select "Food" from Spinner
		view1 = solo.getView(Spinner.class, 1);
		solo.clickOnView(view1);
		solo.scrollToTop();
		solo.clickOnView(solo.getView(TextView.class, 4));
        //Click on Save
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Wait for activity: 'group8.karina.presentation.ExpenseList'
		assertTrue("group8.karina.presentation.ExpenseList is not found!", solo.waitForActivity(group8.karina.presentation.ExpenseList.class));
        //Click on $50.0 on 2016-01-01
		solo.clickOnView(solo.getView(android.R.id.text1, 4));
        //Wait for activity: 'group8.karina.presentation.ExpenseActivity'
		assertTrue("group8.karina.presentation.ExpenseActivity is not found!", solo.waitForActivity(group8.karina.presentation.ExpenseActivity.class));
        //Click on 50
		solo.clickOnView(solo.getView(group8.karina.R.id.valueText));
        //Enter the text: '25'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText), "25");
		//Select "Brandon" from Spinner
		view1 = solo.getView(Spinner.class, 0);
		solo.clickOnView(view1);
		solo.scrollToTop();
		solo.clickOnView(solo.getView(TextView.class, 3));
		//Select "Weapons" from Spinner
		view1 = solo.getView(Spinner.class, 1);
		solo.clickOnView(view1);
		solo.scrollToTop();
		solo.clickOnView(solo.getView(TextView.class, 2));
        //Click on Save
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Wait for activity: 'group8.karina.presentation.ExpenseList'
		assertTrue("group8.karina.presentation.ExpenseList is not found!", solo.waitForActivity(group8.karina.presentation.ExpenseList.class));
        //Click on $25.0 on 2016-01-01
		solo.clickOnView(solo.getView(android.R.id.text1, 4));
        //Wait for activity: 'group8.karina.presentation.ExpenseActivity'
		assertTrue("group8.karina.presentation.ExpenseActivity is not found!", solo.waitForActivity(group8.karina.presentation.ExpenseActivity.class));
        //Click on Delete
		solo.clickOnView(solo.getView(group8.karina.R.id.deleteButton));
        //Click on Delete
		solo.clickOnView(solo.getView(group8.karina.R.id.deleteButton));
        //Wait for activity: 'group8.karina.presentation.ExpenseList'
		assertTrue("group8.karina.presentation.ExpenseList is not found!", solo.waitForActivity(group8.karina.presentation.ExpenseList.class));
        //Click on Add Expense
		solo.clickOnView(solo.getView(group8.karina.R.id.addExpenseButton));
        //Wait for activity: 'group8.karina.presentation.ExpenseActivity'
		assertTrue("group8.karina.presentation.ExpenseActivity is not found!", solo.waitForActivity(group8.karina.presentation.ExpenseActivity.class));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(group8.karina.R.id.valueText));
        //Enter the text: '75'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText), "75");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(group8.karina.R.id.setDate));
        //Enter the text: '01/01/2016'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.setDate));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.setDate), "01/01/2016");
		//Select "Mitch" from Spinner
		view1 = solo.getView(Spinner.class, 0);
		solo.clickOnView(view1);
		solo.scrollToTop();
		solo.clickOnView(solo.getView(TextView.class, 5));
		//Select "Food" from Spinner
		view1 = solo.getView(Spinner.class, 1);
		solo.clickOnView(view1);
		solo.scrollToTop();
		solo.clickOnView(solo.getView(TextView.class, 4));
        //Click on Save
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Wait for activity: 'group8.karina.presentation.ExpenseList'
		assertTrue("group8.karina.presentation.ExpenseList is not found!", solo.waitForActivity(group8.karina.presentation.ExpenseList.class));
        //Click on $75.0 on 2016-01-01
		solo.clickOnView(solo.getView(android.R.id.text1, 4));
        //Wait for activity: 'group8.karina.presentation.ExpenseActivity'
		assertTrue("group8.karina.presentation.ExpenseActivity is not found!", solo.waitForActivity(group8.karina.presentation.ExpenseActivity.class));
        //Click on Delete
		solo.clickOnView(solo.getView(group8.karina.R.id.deleteButton));
        //Click on Cancel
		solo.clickOnView(solo.getView(group8.karina.R.id.cancelButton));
        //Press menu back key
		solo.goBack();
        //Wait for activity: 'group8.karina.presentation.ExpenseList'
		assertTrue("group8.karina.presentation.ExpenseList is not found!", solo.waitForActivity(group8.karina.presentation.ExpenseList.class));
        //Press menu back key
		solo.goBack();
        //Click on Income
		solo.clickOnView(solo.getView(group8.karina.R.id.incomeListButton));
        //Wait for activity: 'group8.karina.presentation.IncomeList'
		assertTrue("group8.karina.presentation.IncomeList is not found!", solo.waitForActivity(group8.karina.presentation.IncomeList.class));
        //Click on Add Income
		solo.clickOnView(solo.getView(group8.karina.R.id.addIncomeButton));
        //Wait for activity: 'group8.karina.presentation.IncomeActivity'
		assertTrue("group8.karina.presentation.IncomeActivity is not found!", solo.waitForActivity(group8.karina.presentation.IncomeActivity.class));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(group8.karina.R.id.valueText));
        //Enter the text: '50'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText), "50");
        //Click on Save
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(group8.karina.R.id.setDate));
        //Enter the text: '01/01/2016'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.setDate));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.setDate), "01/01/2016");
        //Click on Save
		//Select "Mitch" from Spinner
		view1 = solo.getView(Spinner.class, 0);
		solo.clickOnView(view1);
		solo.scrollToTop();
		solo.clickOnView(solo.getView(TextView.class, 5));
		//Select "Gifts" from Spinner
		view1 = solo.getView(Spinner.class, 1);
		solo.clickOnView(view1);
		solo.scrollToTop();
		solo.clickOnView(solo.getView(TextView.class, 2));
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Wait for activity: 'group8.karina.presentation.IncomeList'
		assertTrue("group8.karina.presentation.IncomeList is not found!", solo.waitForActivity(group8.karina.presentation.IncomeList.class));
        //Click on $50.0 on 2016-01-01
		solo.clickOnView(solo.getView(android.R.id.text1, 2));
        //Wait for activity: 'group8.karina.presentation.IncomeActivity'
		assertTrue("group8.karina.presentation.IncomeActivity is not found!", solo.waitForActivity(group8.karina.presentation.IncomeActivity.class));
        //Click on Delete
		solo.clickOnView(solo.getView(group8.karina.R.id.deleteButton));
        //Click on Cancel
		solo.clickOnView(solo.getView(group8.karina.R.id.cancelButton));
        //Click on 50
		solo.clickOnView(solo.getView(group8.karina.R.id.valueText));
        //Enter the text: '25'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText), "25");
		//Select "Brandon" from Spinner
		view1 = solo.getView(Spinner.class, 0);
		solo.clickOnView(view1);
		solo.scrollToTop();
		solo.clickOnView(solo.getView(TextView.class, 3));
		//Select "Income" from Spinner
		view1 = solo.getView(Spinner.class, 1);
		solo.clickOnView(view1);
		solo.scrollToTop();
		solo.clickOnView(solo.getView(TextView.class, 1));
        //Click on Save
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Wait for activity: 'group8.karina.presentation.IncomeList'
		assertTrue("group8.karina.presentation.IncomeList is not found!", solo.waitForActivity(group8.karina.presentation.IncomeList.class));
        //Click on $25.0 on 2016-01-01
		solo.clickOnView(solo.getView(android.R.id.text1, 2));
        //Wait for activity: 'group8.karina.presentation.IncomeActivity'
		assertTrue("group8.karina.presentation.IncomeActivity is not found!", solo.waitForActivity(group8.karina.presentation.IncomeActivity.class));
        //Click on Delete
		solo.clickOnView(solo.getView(group8.karina.R.id.deleteButton));
        //Click on Delete
		solo.clickOnView(solo.getView(group8.karina.R.id.deleteButton));
        //Wait for activity: 'group8.karina.presentation.IncomeList'
		assertTrue("group8.karina.presentation.IncomeList is not found!", solo.waitForActivity(group8.karina.presentation.IncomeList.class));
        //Click on Add Income
		solo.clickOnView(solo.getView(group8.karina.R.id.addIncomeButton));
        //Wait for activity: 'group8.karina.presentation.IncomeActivity'
		assertTrue("group8.karina.presentation.IncomeActivity is not found!", solo.waitForActivity(group8.karina.presentation.IncomeActivity.class));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(group8.karina.R.id.valueText));
        //Enter the text: '75'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText), "75");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(group8.karina.R.id.setDate));
        //Enter the text: '01/01/2016'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.setDate));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.setDate), "01/01/2016");
		//Select "Mitch" from Spinner
		view1 = solo.getView(Spinner.class, 0);
		solo.clickOnView(view1);
		solo.scrollToTop();
		solo.clickOnView(solo.getView(TextView.class, 5));
		//Select "Gifts" from Spinner
		view1 = solo.getView(Spinner.class, 1);
		solo.clickOnView(view1);
		solo.scrollToTop();
		solo.clickOnView(solo.getView(TextView.class, 2));
        //Click on Save
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Wait for activity: 'group8.karina.presentation.IncomeList'
		assertTrue("group8.karina.presentation.IncomeList is not found!", solo.waitForActivity(group8.karina.presentation.IncomeList.class));
        //Press menu back key
		solo.goBack();
        //Click on Category
		solo.clickOnView(solo.getView(group8.karina.R.id.categoryListButton));
        //Wait for activity: 'group8.karina.presentation.CategoryList'
		assertTrue("group8.karina.presentation.CategoryList is not found!", solo.waitForActivity(group8.karina.presentation.CategoryList.class));
        //Click on Food
		solo.clickOnView(solo.getView(android.R.id.text1, 5));
        //Wait for activity: 'group8.karina.presentation.CategoryActivity'
		assertTrue("group8.karina.presentation.CategoryActivity is not found!", solo.waitForActivity(group8.karina.presentation.CategoryActivity.class));
        //Click on Delete
		solo.clickOnView(solo.getView(group8.karina.R.id.deleteButton));
        //Click on Delete All
		solo.clickOnView(solo.getView(group8.karina.R.id.deleteAllButton));
        //Wait for activity: 'group8.karina.presentation.CategoryList'
		assertTrue("group8.karina.presentation.CategoryList is not found!", solo.waitForActivity(group8.karina.presentation.CategoryList.class));
        //Press menu back key
		solo.goBack();
        //Click on Expense
		solo.clickOnView(solo.getView(group8.karina.R.id.expenseListButton));
        //Wait for activity: 'group8.karina.presentation.ExpenseList'
		assertTrue("group8.karina.presentation.ExpenseList is not found!", solo.waitForActivity(group8.karina.presentation.ExpenseList.class));
        //Press menu back key
		solo.goBack();
        //Click on Category
		solo.clickOnView(solo.getView(group8.karina.R.id.categoryListButton));
        //Wait for activity: 'group8.karina.presentation.CategoryList'
		assertTrue("group8.karina.presentation.CategoryList is not found!", solo.waitForActivity(group8.karina.presentation.CategoryList.class));
        //Click on Gifts
		solo.clickOnView(solo.getView(android.R.id.text1, 1));
        //Wait for activity: 'group8.karina.presentation.CategoryActivity'
		assertTrue("group8.karina.presentation.CategoryActivity is not found!", solo.waitForActivity(group8.karina.presentation.CategoryActivity.class));
        //Click on Delete
		solo.clickOnView(solo.getView(group8.karina.R.id.deleteButton));
        //Click on Set unassigned
		solo.clickOnView(solo.getView(group8.karina.R.id.unassignButton));
        //Wait for activity: 'group8.karina.presentation.CategoryList'
		assertTrue("group8.karina.presentation.CategoryList is not found!", solo.waitForActivity(group8.karina.presentation.CategoryList.class));
        //Press menu back key
		solo.goBack();
        //Click on Income
		solo.clickOnView(solo.getView(group8.karina.R.id.incomeListButton));
        //Wait for activity: 'group8.karina.presentation.IncomeList'
		assertTrue("group8.karina.presentation.IncomeList is not found!", solo.waitForActivity(group8.karina.presentation.IncomeList.class));
        //Press menu back key
		solo.goBack();
        //Click on User
		solo.clickOnView(solo.getView(group8.karina.R.id.userListButton));
        //Wait for activity: 'group8.karina.presentation.UserList'
		assertTrue("group8.karina.presentation.UserList is not found!", solo.waitForActivity(group8.karina.presentation.UserList.class));
        //Click on Mitch
		solo.clickOnView(solo.getView(android.R.id.text1, 4));
        //Wait for activity: 'group8.karina.presentation.UserActivity'
		assertTrue("group8.karina.presentation.UserActivity is not found!", solo.waitForActivity(group8.karina.presentation.UserActivity.class));
        //Click on Delete
		solo.clickOnView(solo.getView(group8.karina.R.id.deleteButton));
        //Click on Delete All
		solo.clickOnView(solo.getView(group8.karina.R.id.deleteAllButton));
        //Wait for activity: 'group8.karina.presentation.UserList'
		assertTrue("group8.karina.presentation.UserList is not found!", solo.waitForActivity(group8.karina.presentation.UserList.class));
        //Press menu back key
		solo.goBack();
        //Click on Income
		solo.clickOnView(solo.getView(group8.karina.R.id.incomeListButton));
        //Wait for activity: 'group8.karina.presentation.IncomeList'
		assertTrue("group8.karina.presentation.IncomeList is not found!", solo.waitForActivity(group8.karina.presentation.IncomeList.class));
        //Press menu back key
		solo.goBack();
        //Click on Expense
		solo.clickOnView(solo.getView(group8.karina.R.id.expenseListButton));
        //Wait for activity: 'group8.karina.presentation.ExpenseList'
		assertTrue("group8.karina.presentation.ExpenseList is not found!", solo.waitForActivity(group8.karina.presentation.ExpenseList.class));
        //Press menu back key
		solo.goBack();
	}
}
