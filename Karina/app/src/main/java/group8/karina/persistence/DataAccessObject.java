package group8.karina.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.Exceptions.unfoundResourceException;
import group8.karina.objects.Category;
import group8.karina.objects.Transaction;
import group8.karina.objects.User;

public class DataAccessObject implements Database
{
	private Statement st1, st2, st3;
	private Connection c1;
	private ResultSet rs2, rs3, rs4, rs5;


	private String dbName;
	private String dbType;
	private String cmdString;
	private int updateCount;
	private List result;
	private static String EOF = "  ";

	private int nextUser;
	private int nextCategory;
	private int nextTransaction;

	public DataAccessObject(String dbName)
	{
		this.dbName = dbName;
		nextCategory = 0;
		nextTransaction = 0;
		nextUser = 3;
	}

	public void open(String dbPath)
	{
		String url;
		try
		{
			dbType = "HSQL";
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
			c1 = DriverManager.getConnection(url, "SA", "");
			st1 = c1.createStatement();
			st2 = c1.createStatement();
			st3 = c1.createStatement();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		System.out.println("Opened " +dbType +" database " +dbPath);
	}
	public void close()
	{
		try
		{	// commit all changes to the database
			cmdString = "shutdown compact";
			rs2 = st1.executeQuery(cmdString);
			c1.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		System.out.println("Closed " +dbType +" database " +dbName);
	}
	public List<User> getUserSequential()
	{
		User user;
		String userName;
		int  userID;
		userName = EOF;
		userID = -1;
		result=new ArrayList<User>();

		try
		{
			cmdString = "Select * from Users";
			rs2 = st1.executeQuery(cmdString);
			//ResultSetMetaData md2 = rs2.getMetaData();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		try
		{
			while (rs2.next())
			{
				userID = rs2.getInt("userID");
				userName = rs2.getString("userName");
				user = new User(userID, userName);
				result.add(user);
			}
			rs2.close();
		}
		catch (Exception e)
		{
			System.out.println(processSQLError(e));
		}

		return result;
	}
	public void insertUser(User currentUser) throws DuplicateEntryException
	{
		User user;
		rs2 = null;
		try
		{
			cmdString = "Select userName from Users Where userName = "+ currentUser.getUserName();
			rs2 = st1.executeQuery(cmdString);
			//ResultSetMetaData md2 = rs2.getMetaData();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		try
		{
			if (!rs2.next())
			{
				cmdString = "Insert into Users (userID, UserName) Values("+ nextUser +", "+ currentUser.getUserName()+")";
				rs2 = st1.executeQuery(cmdString);
				user = new User(nextUser, currentUser.getUserName());
				nextUser++;

			}
			else
			{
				throw new DuplicateEntryException("User already created");
			}
			rs2.close();
		}
		catch (SQLException e)
		{
			processSQLError(e);
		}
	}
	public
	User getUserByName(String targetName)
	{
		User user=null;
		String userName;
		int userID;

		rs2 = null;
		try
		{
			cmdString = "Select * from Users Where userName = "+ targetName;
			rs2 = st1.executeQuery(cmdString);
			//ResultSetMetaData md2 = rs2.getMetaData();

			if (rs2.next())
			{
				userID = rs2.getInt("userID");
				userName = rs2.getString("userName");
				user = new User(userID, userName);
			}
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		return user;
	}
	public User getUserById(int uID)
	{
		User user=null;
		String userName;
		int userID;

		rs2 = null;
		try
		{
			cmdString = "Select * from Users Where userID = "+ uID;
			rs2 = st1.executeQuery(cmdString);
			//ResultSetMetaData md2 = rs2.getMetaData();

			if (rs2.next())
			{
				userID = rs2.getInt("userID");
				userName = rs2.getString("userName");
				user = new User(userID, userName);
			}
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		return user;
	}
	public Category getCategoryById(int cID)
	{
		Category category=null;
		String catName;
		int catID;
		boolean catIsExpense;

		rs2 = null;
		try
		{
			cmdString = "Select * from Categories Where CategoryID = "+ cID;
			rs2 = st1.executeQuery(cmdString);
			//ResultSetMetaData md2 = rs2.getMetaData();

			if (rs2.next())
			{
				catID = rs2.getInt("categoryID");
				catName = rs2.getString("categoryName");
				catIsExpense = rs2.getBoolean("categoryIsExpense");
				category = new Category(catID, catName, catIsExpense);
			}
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		return category;
	}
	public List getAllCategories()
	{
		return null;
	}
	public List getIncomeCategories()
	{
		return null;
	}
	public List getExpenseCategories()
	{
		return null;
	}
	public Category getCategoryByNameAndIsExpense(String targetName, boolean isExpense)
	{
		return null;
	}
	public void insertCategory(Category currentCategory) throws DuplicateEntryException
	{

	}
	public void insertTransaction(Transaction currentTransaction)
	{

	}
	public List<Transaction> getTransactionsByType(boolean isExpense)
	{
		return null;
	}
	public Transaction getTransactionByID(int id)
	{
		return null;
	}
	public void deleteUserById(int UserId)
	{

	}
	public void updateUser(User user) throws unfoundResourceException
	{

	}
	public void deleteTransactionsByUserID(int userId)
	{

	}

	public String processSQLError(Exception e)
	{
		String result = "*** SQL Error: " + e.getMessage();

		// Remember, this will NOT be seen by the user!
		e.printStackTrace();

		return result;
	}
}
