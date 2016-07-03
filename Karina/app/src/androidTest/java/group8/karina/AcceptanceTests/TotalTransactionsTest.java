/*
Total Transactions Acceptance Test

View Total Income and Expense Report
Move the chart around, click sides
Go back, add an Income
View Total Income and Expense Report
Go back, remove an Expense
View Total Income and Expense Report

Quit
*/

package group8.karina.AcceptanceTests;

import group8.karina.TestHelpers.TestDataAccessObject;
import group8.karina.application.DatabaseService;
import group8.karina.presentation.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;

public class TotalTransactionsTest extends ActivityInstrumentationTestCase2<MainActivity>
{
  	private Solo solo;
  	
  	public TotalTransactionsTest() {
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
        //Wait for activity: 'group8.karina.presentation.MainActivity'
		solo.waitForActivity(group8.karina.presentation.MainActivity.class, 2000);
        //Set default small timeout to 11584 milliseconds
		Timeout.setSmallTimeout(11584);
        //Click on Reports
		solo.clickOnView(solo.getView(group8.karina.R.id.reportsButton));
        //Wait for activity: 'group8.karina.presentation.ReportsActivity'
		assertTrue("group8.karina.presentation.ReportsActivity is not found!", solo.waitForActivity(group8.karina.presentation.ReportsActivity.class));
        //Click on Total Income and Expense
		solo.clickOnView(solo.getView(group8.karina.R.id.totalTransactionReportButton));
        //Wait for activity: 'group8.karina.presentation.TotalTransactionReportActivity'
		assertTrue("group8.karina.presentation.TotalTransactionReportActivity is not found!", solo.waitForActivity(group8.karina.presentation.TotalTransactionReportActivity.class));
        //Press menu back key
		solo.goBack();
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
        //Enter the text: '100'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.valueText), "100");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(group8.karina.R.id.setDate));
        //Enter the text: '01/01/2016'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.setDate));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.setDate), "01/01/2016");
        //Assert that: 'Default' is shown
		assertTrue("'Default' is not shown!", solo.waitForView(solo.getView(group8.karina.R.id.userSpinnerText)));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(group8.karina.R.id.commentText));
        //Enter the text: 'money'
		solo.clearEditText((android.widget.EditText) solo.getView(group8.karina.R.id.commentText));
		solo.enterText((android.widget.EditText) solo.getView(group8.karina.R.id.commentText), "money");
        //Assert that: 'Default' is shown
		assertTrue("'Default' is not shown!", solo.waitForView(solo.getView(group8.karina.R.id.userSpinnerText)));
        //Click on Save
		solo.clickOnView(solo.getView(group8.karina.R.id.saveButton));
        //Wait for activity: 'group8.karina.presentation.IncomeList'
		assertTrue("group8.karina.presentation.IncomeList is not found!", solo.waitForActivity(group8.karina.presentation.IncomeList.class));
        //Press menu back key
		solo.goBack();
        //Click on Reports
		solo.clickOnView(solo.getView(group8.karina.R.id.reportsButton));
        //Wait for activity: 'group8.karina.presentation.ReportsActivity'
		assertTrue("group8.karina.presentation.ReportsActivity is not found!", solo.waitForActivity(group8.karina.presentation.ReportsActivity.class));
        //Click on Total Income and Expense
		solo.clickOnView(solo.getView(group8.karina.R.id.totalTransactionReportButton));
        //Wait for activity: 'group8.karina.presentation.TotalTransactionReportActivity'
		assertTrue("group8.karina.presentation.TotalTransactionReportActivity is not found!", solo.waitForActivity(group8.karina.presentation.TotalTransactionReportActivity.class));
        //Press menu back key
		solo.goBack();
        //Press menu back key
		solo.goBack();
        //Click on Expense
		solo.clickOnView(solo.getView(group8.karina.R.id.expenseListButton));
        //Wait for activity: 'group8.karina.presentation.ExpenseList'
		assertTrue("group8.karina.presentation.ExpenseList is not found!", solo.waitForActivity(group8.karina.presentation.ExpenseList.class));
        //Click on $440.0 on 2016-06-11
		solo.clickOnView(solo.getView(android.R.id.text1));
        //Wait for activity: 'group8.karina.presentation.ExpenseActivity'
		assertTrue("group8.karina.presentation.ExpenseActivity is not found!", solo.waitForActivity(group8.karina.presentation.ExpenseActivity.class));
        //Click on Delete
		solo.clickOnView(solo.getView(group8.karina.R.id.deleteButton));
        //Click on Delete
		solo.clickOnView(solo.getView(group8.karina.R.id.deleteButton));
        //Wait for activity: 'group8.karina.presentation.ExpenseList'
		assertTrue("group8.karina.presentation.ExpenseList is not found!", solo.waitForActivity(group8.karina.presentation.ExpenseList.class));
        //Press menu back key
		solo.goBack();
        //Click on Reports
		solo.clickOnView(solo.getView(group8.karina.R.id.reportsButton));
        //Wait for activity: 'group8.karina.presentation.ReportsActivity'
		assertTrue("group8.karina.presentation.ReportsActivity is not found!", solo.waitForActivity(group8.karina.presentation.ReportsActivity.class));
        //Click on Total Income and Expense
		solo.clickOnView(solo.getView(group8.karina.R.id.totalTransactionReportButton));
        //Wait for activity: 'group8.karina.presentation.TotalTransactionReportActivity'
		assertTrue("group8.karina.presentation.TotalTransactionReportActivity is not found!", solo.waitForActivity(group8.karina.presentation.TotalTransactionReportActivity.class));
	}
}
