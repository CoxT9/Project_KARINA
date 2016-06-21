package group8.karina.persistence;

import android.support.annotation.NonNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.ListIterator;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.Exceptions.unfoundResourceException;
import group8.karina.objects.Category;
import group8.karina.objects.Transaction;
import group8.karina.objects.User;

public class DataAccessObject implements Database
{
	private Statement st1;
	private Connection c1;
	private ResultSet rs2;


	private String dbName;
	private String dbType;
	private String cmdString;
	private List result;
	private static String EOF = "  ";

	public DataAccessObject(String dbName)
	{
		this.dbName = dbName;
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
		user = getUserByName(currentUser.getUserName());
		try
		{
			if (user == null)
			{
				cmdString = "Insert into Users (UserName) Values('"+ currentUser.getUserName()+"')";
				rs2 = st1.executeQuery(cmdString);
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
			cmdString = "Select * from Users Where userName = '"+ targetName+"'";
			rs2 = st1.executeQuery(cmdString);

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
		Category category;
		String catName;
		int  catID;
		boolean catIsExpense;
		result=new ArrayList<Category>();

		try
		{
			cmdString = "Select * from Categories";
			rs2 = st1.executeQuery(cmdString);
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		try
		{
			while (rs2.next())
			{
				catID = rs2.getInt("categoryID");
				catName = rs2.getString("categoryName");
				catIsExpense = rs2.getBoolean("categoryIsExpense");
				category = new Category(catID, catName, catIsExpense);
				result.add(category);
			}
			rs2.close();
		}
		catch (Exception e)
		{
			System.out.println(processSQLError(e));
		}

		return result;
	}
	public List getIncomeCategories()
	{
		Category category;
		String catName;
		int  catID;
		boolean catIsExpense;
		result=new ArrayList<Category>();

		try
		{
			cmdString = "Select * from Categories where categoryIsExpense = FALSE";
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
				catID = rs2.getInt("categoryID");
				catName = rs2.getString("categoryName");
				catIsExpense = rs2.getBoolean("categoryIsExpense");
				category = new Category(catID, catName, catIsExpense);
				result.add(category);
			}
			rs2.close();
		}
		catch (Exception e)
		{
			System.out.println(processSQLError(e));
		}

		return result;
	}
	public List getExpenseCategories()
	{
		Category category;
		String catName;
		int  catID;
		boolean catIsExpense;
		result=new ArrayList<Category>();

		try
		{
			cmdString = "Select * from Categories where categoryIsExpense = TRUE";
			rs2 = st1.executeQuery(cmdString);
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		try
		{
			while (rs2.next())
			{
				catID = rs2.getInt("categoryID");
				catName = rs2.getString("categoryName");
				catIsExpense = rs2.getBoolean("categoryIsExpense");
				category = new Category(catID, catName, catIsExpense);
				result.add(category);
			}
			rs2.close();
		}
		catch (Exception e)
		{
			System.out.println(processSQLError(e));
		}
		return result;
	}
	public Category getCategoryByNameAndIsExpense(String targetName, boolean isExpense)
	{
		Category category = null;
		String catName;
		boolean catIsExpense;
		int catID;

		rs2 = null;
		try
		{
			cmdString = "Select * from Categories Where categoryName = '"+ targetName + "' And CategoryIsExpense = "+ isExpense;
			rs2 = st1.executeQuery(cmdString);

			if (rs2.next())
			{
				catID = rs2.getInt("categoryID");
				catName = rs2.getString("categoryName");
				catIsExpense = rs2.getBoolean("categoryIsExpense");
				category = new Category(catID, catName, catIsExpense);
			}
			rs2.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}

		return category;
	}
	public void insertCategory(Category currentCategory) throws DuplicateEntryException
	{
		Category category;
		rs2 = null;

		category = getCategoryByNameAndIsExpense(currentCategory.getCategoryName(), currentCategory.isExpense());

		try
		{
			if (category == null)
			{
				cmdString = "Insert into Categories (categoryName, categoryIsExpense) Values('"+ currentCategory.getCategoryName()+ "', "+ currentCategory.isExpense()+")";
				rs2 = st1.executeQuery(cmdString);
			}
			else
			{
				throw new DuplicateEntryException("Category already created");
			}
			rs2.close();
		}
		catch (SQLException e)
		{
			processSQLError(e);
		}
	}


	public void deleteCategoryById(int categoryId)
	{
		try
		{
			cmdString = "Delete from Categories where CategoryID=" +categoryId;
			st1.executeUpdate(cmdString);
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
	}

	@Override
	public void updateCategory(Category category) throws unfoundResourceException
	{
		if (getCategoryById(category.getCategoryID())==null)
		{
			throw new unfoundResourceException("Category "+ category.getCategoryID()+ "not found.");
		}
		else
		{
			try
			{
				cmdString = "Update Categories Set categoryName='" + category.getCategoryName() + "', categoryIsExpense="
						+ category.isExpense() + " where categoryID=" + category.getCategoryID();
				st1.executeUpdate(cmdString);
			} catch (Exception e)
			{
				processSQLError(e);
			}
		}
	}

	public void insertTransaction(Transaction currentTransaction)
	{
		try
		{
			cmdString = "Insert into Transactions (transDate, transAmount, transIsExpense, transComment, transCategoryID, transUserID) Values('"
					+ new java.sql.Date(currentTransaction.getDate().getTime())+"', "+ currentTransaction.getAmount()+ ", "+ currentTransaction.isExpense()+", '"
					+ currentTransaction.getComments()+"', " +currentTransaction.getCategoryID()+", "+currentTransaction.getUserID()+")";
			rs2 = st1.executeQuery(cmdString);

			rs2.close();
		}
		catch (SQLException e)
		{
			processSQLError(e);
		}
	}
	public List<Transaction> getTransactionsByType(boolean isExpense)
	{
		Transaction transaction;
		double transAmount;
		String transComment;
		int transUserID;
		int transCategoryID;
		int  transID;
		boolean transIsExpense;
		Date transDate;
		String categoryName;
		String userName;

		result=new ArrayList<Transaction>();

		try
		{
			cmdString = "Select transAmount,transComment,transUserID,transCategoryID,transID,transIsExpense,transDate,categoryName,userName from Transactions t inner join Categories c on c.categoryID = t.transCategoryID inner join Users u on t.transUserID = u.userID where transIsExpense = " + isExpense;
			rs2 = st1.executeQuery(cmdString);

		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		try
		{
			while (rs2.next())
			{
				transAmount = rs2.getDouble("transAmount");
				transComment = rs2.getString("transComment");
				transUserID = rs2.getInt("transUserID");
				transCategoryID = rs2.getInt("transCategoryID");
				transID = rs2.getInt("transID");
				transIsExpense = rs2.getBoolean("transIsExpense");
				transDate = rs2.getDate("transDate");
				categoryName = rs2.getString("categoryName");
				userName = rs2.getString("userName");

				transaction = new Transaction(transID, transDate, transUserID, transIsExpense, transAmount, transCategoryID, transComment);
				transaction.setCategoryName(categoryName);
				transaction.setUserName(userName);

				result.add(transaction);
			}
			rs2.close();
		}
		catch (Exception e)
		{
			System.out.println(processSQLError(e));
		}

		return result;
	}

	public List<Transaction> getOrderedTransactionsByDate()
	{
		Transaction transaction;
		double transAmount;
		String transComment;
		int transUserID;
		int transCategoryID;
		int  transID;
		boolean transIsExpense;
		Date transDate;

		result=new ArrayList<Transaction>();

		try
		{
			cmdString = "Select * from Transactions Order By transDate ASC";
			rs2 = st1.executeQuery(cmdString);

		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		try
		{
			while (rs2.next())
			{
				transAmount = rs2.getDouble("transAmount");
				transComment = rs2.getString("transComment");
				transUserID = rs2.getInt("transUserID");
				transCategoryID = rs2.getInt("transCategoryID");
				transID = rs2.getInt("transID");
				transIsExpense = rs2.getBoolean("transIsExpense");
				transDate = rs2.getDate("transDate");
				transaction = new Transaction(transID, transDate, transUserID, transIsExpense, transAmount, transCategoryID, transComment);
				result.add(transaction);
			}
			rs2.close();
		}
		catch (Exception e)
		{
			System.out.println(processSQLError(e));
		}

		return result;
	}
	public Transaction getTransactionByID(int id)
	{
		Transaction transaction=null;
		double transAmount;
		String transComment;
		int transUserID;
		int transCategoryID;
		int  transID;
		boolean transIsExpense;
		Date transDate;

		rs2 = null;
		try
		{
			cmdString = "Select * from Transactions Where transID = "+ id;
			rs2 = st1.executeQuery(cmdString);

			if (rs2.next())
			{
				transAmount = rs2.getDouble("transAmount");
				transComment = rs2.getString("transComment");
				transUserID = rs2.getInt("transUserID");
				transCategoryID = rs2.getInt("transCategoryID");
				transID = rs2.getInt("transID");
				transIsExpense = rs2.getBoolean("transIsExpense");
				transDate = rs2.getDate("transDate");
				transaction = new Transaction(transID, transDate, transUserID, transIsExpense, transAmount, transCategoryID, transComment);

			}
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		return transaction;
	}
	public void deleteUserById(int userId)
	{
		try
		{
			cmdString = "Delete from Users where UserID=" +userId;
			st1.executeUpdate(cmdString);
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
	}

	public void updateUser(User user) throws unfoundResourceException
	{
		if (getUserById(user.getUserID())==null)
		{
			throw new unfoundResourceException("User "+ user.getUserID()+ "not found.");
		}
		else
		{
			try
			{
				cmdString = "Update Users Set userName='" + user.getUserName() + "' where UserID=" + user.getUserID();
				st1.executeUpdate(cmdString);
			} catch (Exception e)
			{
				processSQLError(e);
			}
		}
	}
	public void deleteTransactionsByUserID(int userId)
	{
		try
		{
			cmdString = "Delete from Transactions where transUserID=" +userId;
			st1.executeUpdate(cmdString);
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
	}

	@Override
	public void deleteTransactionsByCategoryID(int categoryId)
	{
		try
		{
			cmdString = "Delete from Transactions where transCategoryID=" +categoryId;
			st1.executeUpdate(cmdString);
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
	}

	@Override
	public void unassignTransactionsByUserID(int userID)
	{
		try
		{
			cmdString = "Update Transactions Set transUserID = 1 where transUserID=" +userID;
			st1.executeUpdate(cmdString);
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
	}

	@Override
	public void unassignTransactionsByCategoryID(int categoryID)
	{
		try
		{
			cmdString = "Update Transactions Set transCategoryID = 1 where transCategoryID=" +categoryID;
			st1.executeUpdate(cmdString);
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
	}

	@Override
	public void deleteTransactionByID(int transID)
	{
		try
		{
			cmdString = "Delete from Transactions where transID=" +transID;
			st1.executeUpdate(cmdString);
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
	}

	@Override
	public void updateTransaction(Transaction trans) throws unfoundResourceException
	{
		if (getTransactionByID(trans.getTransactionID())==null)
		{
			throw new unfoundResourceException("User "+ trans.getTransactionID()+ "not found.");
		}
		else
		{
			try
			{
				cmdString = "Update Transactions Set transDate = '"+ new java.sql.Date(trans.getDate().getTime())
						+ "', transAmount = " + trans.getAmount() + ", transIsExpense = "+ trans.isExpense()
						+  ", transComment = '"+ trans.getComments() + "', transCategoryID = "+ trans.getCategoryID()
						+ ", transUserID = " + trans.getUserID() + " where transID = " + trans.getTransactionID();
				rs2 = st1.executeQuery(cmdString);

				rs2.close();
			}
			catch (SQLException e)
			{
				processSQLError(e);
			}
		}
	}

	public String processSQLError(Exception e)
	{
		String result = "*** SQL Error: " + e.getMessage();

		// Remember, this will NOT be seen by the user!
		e.printStackTrace();

		return result;
	}

}
