package group8.karina.businessTests.objectsTests;

import org.junit.Before;
import org.junit.Test;

import group8.karina.objects.Transaction;
import group8.karina.objects.Transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import java.util.Date;

import group8.karina.objects.User;

import static org.junit.Assert.*;

public class TransactionTests {
    private final int TRANS_ORIGINAL_ID = 1;
    private final boolean TRANS_ORIGINAL_ISEXP = true;
    private final double TRANS_ORIGINAL_AMOUNT = 100.5;
    private final int TRANS_ORIGINAL_CAT_ID = 1;
    private final int TRANS_ORIGINAL_USER_ID = 1;
    private final Date TRANS_ORIGINAL_DATE = new Date();

    private Transaction trans;
    private Transaction otherTrans;

    @Before
    public void setUp() {
        trans = new Transaction(TRANS_ORIGINAL_DATE, TRANS_ORIGINAL_USER_ID, TRANS_ORIGINAL_ISEXP, TRANS_ORIGINAL_AMOUNT, TRANS_ORIGINAL_CAT_ID);
        trans.setTransactionID(TRANS_ORIGINAL_ID);

    }

    @Test
    public void equality() {

        //equals compares transaction ids
        otherTrans = new Transaction("otherstuff", false);
        otherTrans.setTransactionID(TRANS_ORIGINAL_ID);

        assertTrue(otherTrans.equals(trans));
        otherTrans.setTransactionID(2);
        assertFalse(otherTrans.equals(trans));

        assertFalse(trans.equals(TRANS_ORIGINAL_ID));
        assertFalse(trans.equals(null));
    }

    @Test
    public void GettersGetCorrectValues() {
        otherTrans = new Transaction("name", true);
        assertEquals(otherTrans.getTransactionID(), INITIAL_TRANS_ID);

        assertEquals(trans.isExpense(), TRANS_ORIGINAL_ISEXP);
        assertEquals(trans.getTransactionID(), TRANS_ORIGINAL_ID);
        assertEquals(trans.getAmount(), TRANS_ORIGINAL_NAME);
        assertEquals(trans.getCategoryID(), TRANS_ORIGINAL_NAME);
        assertEquals(trans.getUserID(), TRANS_ORIGINAL_NAME);
        assertEquals(trans.getDate(), TRANS_ORIGINAL_NAME);
    }

    @Test
    public void SetIDSetsIDs() {
        trans.setTransactionID(2);
        assertNotEquals(trans.getTransactionID(), TRANS_ORIGINAL_ID);
        assertEquals(trans.getTransactionID(), 2);

        trans.setTransactionID(TRANS_ORIGINAL_ID);

    }

    @Test
    public void testCloning() {
        otherTrans = trans.clone();
        assertEquals(trans.isExpense(), TRANS_ORIGINAL_ISEXP);
        assertEquals(trans.getTransactionID(), TRANS_ORIGINAL_ID);
        assertEquals(trans.getTransactionName(), TRANS_ORIGINAL_NAME);

        otherTrans.setTransactionID(2);//separate from original
        assertFalse(trans.equals(otherTrans));
    }
}
