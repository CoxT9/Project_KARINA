package group8.karina.tests.persistanceTests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import group8.karina.persistence.*;

public class DataAccessObjectTests
{
	@Test
	public void openTest()
	{
		Database test = new DataAccessObject("database");

		test.open("database/database");

		assertEquals(test.getUserSequential().get(1).getUserName(), "Aria");
	}
}
