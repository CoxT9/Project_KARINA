package group8.karina.tests.objectsTests;

import org.junit.Before;

import group8.karina.objects.Transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

public class TransactionTests extends junit.framework.TestCase
{
	private final int TRANS_ORIGINAL_ID = 1;
	private final boolean TRANS_ORIGINAL_ISEXP = true;
	private final double TRANS_ORIGINAL_AMOUNT = 100.5;
	private final int TRANS_ORIGINAL_CAT_ID = 1;
	private final int TRANS_ORIGINAL_USER_ID = 1;
	private final Date TRANS_ORIGINAL_DATE = new Date();
	private final String TRANS_ORIGINAL_COMMENT = "HI THERE";
	private final int TRANS_INITIAL_ID = -1;

	private Transaction trans;
	private Transaction otherTrans;
	private Object cloneTrans;

	@Before
	public void setUp()
	{
		trans = new Transaction(TRANS_ORIGINAL_DATE, TRANS_ORIGINAL_USER_ID, TRANS_ORIGINAL_ISEXP, TRANS_ORIGINAL_AMOUNT, TRANS_ORIGINAL_CAT_ID, TRANS_ORIGINAL_COMMENT);
		trans.setTransactionID(TRANS_ORIGINAL_ID);

	}

	public void testEquality()
	{

		//equals compares transaction ids
		otherTrans = new Transaction(TRANS_ORIGINAL_DATE, 14, TRANS_ORIGINAL_ISEXP, TRANS_ORIGINAL_AMOUNT, TRANS_ORIGINAL_CAT_ID, TRANS_ORIGINAL_COMMENT);
		otherTrans.setTransactionID(TRANS_ORIGINAL_ID);

		assertTrue(otherTrans.equals(trans));
		otherTrans.setTransactionID(2);
		assertFalse(otherTrans.equals(trans));

		assertFalse(trans.equals(TRANS_ORIGINAL_ID));
		assertFalse(trans.equals(null));
	}

	public void testGettersGetCorrectValues()
	{
		otherTrans = new Transaction(TRANS_ORIGINAL_DATE, 14, TRANS_ORIGINAL_ISEXP, TRANS_ORIGINAL_AMOUNT, TRANS_ORIGINAL_CAT_ID, TRANS_ORIGINAL_COMMENT);
		assertEquals(otherTrans.getTransactionID(), TRANS_INITIAL_ID);

		assertEquals(trans.isExpense(), TRANS_ORIGINAL_ISEXP);
		assertEquals(trans.getTransactionID(), TRANS_ORIGINAL_ID);
		assertEquals(trans.getAmount(), TRANS_ORIGINAL_AMOUNT, 1);
		assertEquals(trans.getCategoryID(), TRANS_ORIGINAL_CAT_ID);
		assertEquals(trans.getUserID(), TRANS_ORIGINAL_USER_ID);
		assertEquals(trans.getDate(), TRANS_ORIGINAL_DATE);
	}

	public void testSetIDSetsIDs()
	{
		trans.setTransactionID(2);
		assertNotEquals(trans.getTransactionID(), TRANS_ORIGINAL_ID);
		assertEquals(trans.getTransactionID(), 2);

		trans.setTransactionID(TRANS_ORIGINAL_ID);

	}

	public void testCloning() {
		cloneTrans = trans.clone();
		assertTrue(cloneTrans instanceof Transaction);
		otherTrans = (Transaction)cloneTrans;
		assertEquals(otherTrans.isExpense(), TRANS_ORIGINAL_ISEXP);
		assertEquals(otherTrans.getTransactionID(), TRANS_ORIGINAL_ID);
		assertEquals(otherTrans.getAmount(), TRANS_ORIGINAL_AMOUNT, 1);
		assertEquals(otherTrans.getCategoryID(), TRANS_ORIGINAL_CAT_ID);
		assertEquals(otherTrans.getUserID(), TRANS_ORIGINAL_USER_ID);
		assertEquals(otherTrans.getDate(), TRANS_ORIGINAL_DATE);
		assertNotSame(trans, otherTrans);

		otherTrans.setTransactionID(2);//separate from original
		assertFalse(trans.equals(otherTrans));
	}
}
