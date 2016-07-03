package group8.karina.test;

import group8.karina.TestHelpers.TestDataAccessObject;
import group8.karina.application.DatabaseService;
import group8.karina.presentation.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;


public class CompareSpendingBetweenCategoriesTests extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public CompareSpendingBetweenCategoriesTests() {
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
        super.tearDown();
  	}

	public void testRun() {
        //Wait for activity: 'group8.karina.presentation.MainActivity'
		solo.waitForActivity(group8.karina.presentation.MainActivity.class, 2000);
        //Set default small timeout to 21510 milliseconds
		Timeout.setSmallTimeout(21510);
        //Click on Category
		solo.clickOnView(solo.getView(group8.karina.R.id.categoryListButton));
        //Wait for activity: 'group8.karina.presentation.CategoryList'
		assertTrue("group8.karina.presentation.CategoryList is not found!", solo.waitForActivity(group8.karina.presentation.CategoryList.class));
        //Click on Add Category
		solo.clickOnView(solo.getView(group8.karina.R.id.addCategoryButton));
        //Wait for activity: 'group8.karina.presentation.CategoryActivity'
		assertTrue("group8.karina.presentation.CategoryActivity is not found!", solo.waitForActivity(group8.karina.presentation.CategoryActivity.class));
        //Enter the text: 'Car'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.editCategory));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.editCategory), "Car");
        //Click on Save
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Wait for activity: 'group8.karina.presentation.CategoryList'
		assertTrue("group8.karina.presentation.CategoryList is not found!", solo.waitForActivity(group8.karina.presentation.CategoryList.class));
        //Click on Car
		solo.clickOnView(solo.getView(android.R.id.text1, 4));
        //Wait for activity: 'group8.karina.presentation.CategoryActivity'
		assertTrue("group8.karina.presentation.CategoryActivity is not found!", solo.waitForActivity(group8.karina.presentation.CategoryActivity.class));
        //Press menu back key
		solo.goBack();
        //Wait for activity: 'group8.karina.presentation.CategoryList'
		assertTrue("group8.karina.presentation.CategoryList is not found!", solo.waitForActivity(group8.karina.presentation.CategoryList.class));
        //Click on Add Category
		solo.clickOnView(solo.getView(group8.karina.R.id.addCategoryButton));
        //Wait for activity: 'group8.karina.presentation.CategoryActivity'
		assertTrue("group8.karina.presentation.CategoryActivity is not found!", solo.waitForActivity(group8.karina.presentation.CategoryActivity.class));
        //Enter the text: 'Car'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.editCategory));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.editCategory), "Car");
        //Click on Save
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Press menu back key
		solo.goBack();
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
        //Enter the text: '20.00'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText), "20.00");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(group8.karina.R.id.setDate));
        //Enter the text: '12/12/2016'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.setDate));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.setDate), "12/12/2016");

		View view1 = solo.getView(Spinner.class, 1);
		solo.clickOnView(view1);
		solo.scrollToTop();
		solo.clickOnView(solo.getView(TextView.class, 3));

        //Click on Save
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Wait for activity: 'group8.karina.presentation.ExpenseList'
		assertTrue("group8.karina.presentation.ExpenseList is not found!", solo.waitForActivity(group8.karina.presentation.ExpenseList.class));

		solo.clickOnView(solo.getView(android.R.id.text1, 2));

	}
}
