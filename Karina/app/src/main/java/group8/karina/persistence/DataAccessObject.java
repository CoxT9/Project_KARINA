package group8.karina.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.Exceptions.unfoundResourceException;
import group8.karina.objects.Category;
import group8.karina.objects.Transaction;
import group8.karina.objects.User;

public class DataAccessObject implements Database
{
	private final String GET_ALL = "Select * from ";

	protected Statement st;
	protected Connection conn;
	protected ResultSet rs;

	private String dbName;
	private String dbType;
	private String cmdString;
	private List result;


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
			conn = DriverManager.getConnection(url, "SA", "");
			st = conn.createStatement();
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
			rs = st.executeQuery(cmdString);
			conn.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		System.out.println("Closed " +dbType +" database " +dbName);
	}

	private String queryByAttr(String tableName, String attrName, Object value)
	{
		//value must convert to String
		return String.format("Select * from %s Where %s = %s", tableName, attrName, value);
	}

	protected void updateDatabase(String cmd)
	{
		try
		{
			st.executeUpdate(cmd);
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
	}

	private User getUser()
	{
		User user = null;
		String userName;
		int  userID;
		if (rs != null)
		{
			try
			{
				userID = rs.getInt("userID");
				userName = rs.getString("userName");
				user = new User(userID, userName);
			} catch (SQLException e)
			{
				processSQLError(e);
			}
		}
		return user;
	}

	private Category getCategory()
	{
		Category category=null;
		String catName;
		int catID;
		boolean catIsExpense;

		if (rs != null)
		{
			try
			{
				catID = rs.getInt("categoryID");
				catName = rs.getString("categoryName");
				catIsExpense = rs.getBoolean("categoryIsExpense");
				category = new Category(catID, catName, catIsExpense);
			} catch (SQLException e)
			{
				processSQLError(e);
			}
		}
		return category;
	}

	private Transaction getTransaction()
	{
		Transaction transaction=null;
		double transAmount;
		String transComment;
		int transUserID;
		int transCategoryID;
		int  transID;
		boolean transIsExpense;
		Date transDate;

		if (rs != null)
		{
			try
			{
				transAmount = rs.getDouble("transAmount");
				transComment = rs.getString("transComment");
				transUserID = rs.getInt("transUserID");
				transCategoryID = rs.getInt("transCategoryID");
				transID = rs.getInt("transID");
				transIsExpense = rs.getBoolean("transIsExpense");
				transDate = rs.getDate("transDate");
				transaction = new Transaction(transID, transDate, transUserID, transIsExpense, transAmount, transCategoryID, transComment);
			} catch (SQLException e)
			{
				processSQLError(e);
			}
		}
		return transaction;
	}

	public List<User> getAllUsers()
	{
		User user;

		result=new ArrayList<User>();

		try
		{
			rs = st.executeQuery(GET_ALL + "Users");

			while (rs.next())
			{
				user = getUser();
				result.add(user);
			}
			rs.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}

		return result;
	}
	public void insertUser(User currentUser) throws DuplicateEntryException
	{
		User user = getUserByName(currentUser.getUserName());

		if (user == null)
		{
			if (currentUser.getUserID() == -1)
			{
				cmdString="Insert into Users (UserName) Values('"+ currentUser.getUserName()+"')";
			}
			else
			{
				cmdString= "Insert into Users (UserID, UserName) Values("+ currentUser.getUserID()+" '"+ currentUser.getUserName()+"')";
			}
			updateDatabase(cmdString);
		}
		else
		{
			throw new DuplicateEntryException("User already created");
		}

	}
	public
	User getUserByName(String targetName)
	{
		User user=null;
		rs = null;
		try
		{
			cmdString = queryByAttr("Users", "userName", "'"+targetName+"'");
			rs = st.executeQuery(cmdString);

			if (rs.next())
			{
				user = getUser();
			}
			rs.close();
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
		rs = null;
		try
		{
			cmdString = queryByAttr( "Users", "userID", uID);
			rs = st.executeQuery(cmdString);

			if (rs.next())
			{
				user = getUser();
			}
			rs.close();
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

		rs = null;
		try
		{
			cmdString = queryByAttr("Categories", "categoryID", cID);
			rs = st.executeQuery(cmdString);

			if (rs.next())
			{
				category = getCategory();
			}
			rs.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		return category;
	}
	public List<Category> getAllCategories()
	{
		Category category;
		result=new ArrayList<Category>();
		rs = null;

		try
		{
			rs = st.executeQuery(GET_ALL + "Categories");
			while (rs.next())
			{
				category = getCategory();
				result.add(category);
			}
			rs.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}

		return result;
	}
	public List<Category> getIncomeCategories()
	{
		Category category;
		result=new ArrayList<Category>();
		rs = null;

		try
		{
			cmdString = queryByAttr("Categories", "categoryIsExpense", "FALSE");
			rs = st.executeQuery(cmdString);
			while (rs.next())
			{
				category = getCategory();
				result.add(category);
			}
			rs.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}

		return result;
	}
	public List<Category> getExpenseCategories()
	{
		Category category;
		result=new ArrayList<Category>();
		rs = null;

		try
		{
			cmdString = queryByAttr("Categories", "categoryIsExpense", "TRUE");
			rs = st.executeQuery(cmdString);
			while (rs.next())
			{
				category = getCategory();
				result.add(category);
			}
			rs.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}

		return result;
	}
	public Category getCategoryByNameAndIsExpense(String targetName, boolean isExpense)
	{
		Category category = null;
		rs = null;

		try
		{
			cmdString = queryByAttr("Categories","categoryName", "'"+ targetName + "'")+ "And CategoryIsExpense = "+ isExpense;
			rs = st.executeQuery(cmdString);

			if (rs.next())
			{
				category = getCategory();
			}
			rs.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}

		return category;
	}
	public void insertCategory(Category currentCategory) throws DuplicateEntryException
	{
		Category category = getCategoryByNameAndIsExpense(currentCategory.getCategoryName(), currentCategory.isExpense());

		if (category == null)
		{
			if (currentCategory.getCategoryID() == -1)
			{
				cmdString = "Insert into Categories (categoryName, categoryIsExpense) Values('"+ currentCategory.getCategoryName()+ "', "+ currentCategory.isExpense()+")";

			}
			else
			{
				cmdString = "Insert into Categories (categoryID, categoryName, categoryIsExpense) Values("+ currentCategory.getCategoryID()+"'"+ currentCategory.getCategoryName()+ "', "+ currentCategory.isExpense()+")";

			}
			updateDatabase(cmdString);

		}
		else
		{
			throw new DuplicateEntryException("Category already created");
		}
	}


	public void deleteCategoryById(int categoryId)
	{
		updateDatabase("Delete from Categories where CategoryID=" +categoryId);

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
			cmdString = "Update Categories Set categoryName='" + category.getCategoryName() + "', categoryIsExpense="
				+ category.isExpense() + " where categoryID=" + category.getCategoryID();
			updateDatabase(cmdString);
		}
	}

	public void insertTransaction(Transaction currentTransaction)
	{
		if (currentTransaction.getTransactionID()== -1)
		{
			cmdString = "Insert into Transactions (transDate, transAmount, transIsExpense, transComment, transCategoryID, transUserID) Values('"
					+ new java.sql.Date(currentTransaction.getDate().getTime())+"', "+ currentTransaction.getAmount()+ ", "+ currentTransaction.isExpense()+", '"
					+ currentTransaction.getComments()+"', " +currentTransaction.getCategoryID()+", "+currentTransaction.getUserID()+")";
		}
		else
		{
			cmdString = "Insert into Transactions (transID, transDate, transAmount, transIsExpense, transComment, transCategoryID, transUserID) Values("
					+ currentTransaction.getTransactionID()+", '" + new java.sql.Date(currentTransaction.getDate().getTime())+"', "+ currentTransaction.getAmount()+ ", "+ currentTransaction.isExpense()+", '"
					+ currentTransaction.getComments()+"', " +currentTransaction.getCategoryID()+", "+currentTransaction.getUserID()+")";
		}

		updateDatabase(cmdString);
	}
	public List<Transaction> getTransactionsByType(boolean isExpense)
	{
		Transaction transaction;
		String categoryName;
		String userName;
		result=new ArrayList<Transaction>();
		rs = null;

		try
		{
			cmdString = "Select transAmount,transComment,transUserID,transCategoryID,transID,transIsExpense,transDate,categoryName,userName from Transactions t inner join Categories c on c.categoryID = t.transCategoryID inner join Users u on t.transUserID = u.userID where transIsExpense = " + isExpense;
			rs = st.executeQuery(cmdString);

			while (rs.next())
			{
				categoryName = rs.getString("categoryName");
				userName = rs.getString("userName");

				transaction = getTransaction();

				transaction.setCategoryName(categoryName);
				transaction.setUserName(userName);

				result.add(transaction);
			}
			rs.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}

		return result;
	}

	public List<Transaction> getOrderedTransactionsByDate()
	{
		Transaction transaction = null;
		result=new ArrayList<Transaction>();
		rs = null;

		try
		{
			cmdString = GET_ALL + "Transactions Order By transDate ASC";
			rs = st.executeQuery(cmdString);
			while (rs.next())
			{
				transaction = getTransaction();
				result.add(transaction);
			}
			rs.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}

		return result;
	}

	public List<Transaction> getOrderedTransactionsByDateAndType(boolean isExpense)
	{
		Transaction transaction;
		double transAmount;
		String transComment;
		int transUserID;
		int transCategoryID;
		int  transID;
		boolean transIsExpense;
		Date transDate;
		String userName;
		String categoryName;

		result=new ArrayList<Transaction>();

		try
		{
			cmdString = "Select * from Transactions t inner join Categories c on c.categoryID = t.transCategoryID inner join Users u on t.transUserID = u.userID where transIsExpense = " + isExpense + " Order By transDate ASC";
			System.out.println("cmd str : " + cmdString);
			rs = st.executeQuery(cmdString);

		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		try
		{
			while (rs.next())
			{
				categoryName = rs.getString("categoryName");
				userName = rs.getString("userName");

				transAmount = rs.getDouble("transAmount");
				transComment = rs.getString("transComment");
				transUserID = rs.getInt("transUserID");
				transCategoryID = rs.getInt("transCategoryID");
				transID = rs.getInt("transID");
				transIsExpense = rs.getBoolean("transIsExpense");
				transDate = rs.getDate("transDate");
				transaction = new Transaction(transID, transDate, transUserID, transIsExpense, transAmount, transCategoryID, transComment);
				result.add(transaction);

				transaction.setCategoryName(categoryName);
				transaction.setUserName(userName);
			}
			rs.close();
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
		rs = null;

		try
		{
			cmdString = queryByAttr("Transactions", "transID", id);
			rs = st.executeQuery(cmdString);

			if (rs.next())
			{
				transaction = getTransaction();
			}
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		return transaction;
	}
	public List<Transaction> getOrderedTransactionsByUser(boolean isExpense)
	{
		Transaction transaction;
		double transAmount;
		String transComment;
		int transUserID;
		int transCategoryID;
		int  transID;
		boolean transIsExpense;
		Date transDate;
		String userName;
		String categoryName;

		result=new ArrayList<Transaction>();

		try
		{
			cmdString = "Select * from Transactions t inner join Categories c on c.categoryID = t.transCategoryID inner join Users u on t.transUserID = u.userID where transIsExpense = " + isExpense + " Order By transUserID ASC";
			System.out.println("cmd str : " + cmdString);
			rs = st.executeQuery(cmdString);

		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		try
		{
			while (rs.next())
			{
				categoryName = rs.getString("categoryName");
				userName = rs.getString("userName");

				transAmount = rs.getDouble("transAmount");
				transComment = rs.getString("transComment");
				transUserID = rs.getInt("transUserID");
				transCategoryID = rs.getInt("transCategoryID");
				transID = rs.getInt("transID");
				transIsExpense = rs.getBoolean("transIsExpense");
				transDate = rs.getDate("transDate");
				transaction = new Transaction(transID, transDate, transUserID, transIsExpense, transAmount, transCategoryID, transComment);
				result.add(transaction);

				transaction.setCategoryName(categoryName);
				transaction.setUserName(userName);
			}
			rs.close();
		}
		catch (Exception e)
		{
			System.out.println(processSQLError(e));
		}

		return result;
	}

	@Override
	public List<Transaction> getOrderedTransactionsByCategory(boolean isExpense) {
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
			cmdString = "Select * from Transactions t inner join Categories c on c.categoryID = t.transCategoryID inner join Users u on t.transUserID = u.userID where transIsExpense = " + isExpense + " Order By transCategoryID ASC";
			System.out.println("cmd str : " + cmdString);
			rs = st.executeQuery(cmdString);

		}
		catch (Exception e)
		{
			System.out.println("cat err.");
			processSQLError(e);
		}
		try
		{
			while (rs.next())
			{
				categoryName = rs.getString("categoryName");
				userName = rs.getString("userName");

				transAmount = rs.getDouble("transAmount");
				transComment = rs.getString("transComment");
				transUserID = rs.getInt("transUserID");
				transCategoryID = rs.getInt("transCategoryID");
				transID = rs.getInt("transID");
				transIsExpense = rs.getBoolean("transIsExpense");
				transDate = rs.getDate("transDate");
				transaction = new Transaction(transID, transDate, transUserID, transIsExpense, transAmount, transCategoryID, transComment);

				transaction.setCategoryName(categoryName);
				transaction.setUserName(userName);

				result.add(transaction);
			}
			rs.close();
		}
		catch (Exception e)
		{
			System.out.println(processSQLError(e));
		}

		return result;	}

	public void deleteUserById(int userId)
	{
		updateDatabase("Delete from Users where UserID=" +userId);
	}

	public void updateUser(User user) throws unfoundResourceException
	{
		if (getUserById(user.getUserID())==null)
		{
			throw new unfoundResourceException("User "+ user.getUserID()+ "not found.");
		}
		else
		{
			updateDatabase("Update Users Set userName='" + user.getUserName() + "' where UserID=" + user.getUserID());
		}
	}
	public void deleteTransactionsByUserID(int userId)
	{
		updateDatabase("Delete from Transactions where transUserID=" +userId);
	}

	@Override
	public void deleteTransactionsByCategoryID(int categoryId)
	{
		updateDatabase("Delete from Transactions where transCategoryID=" +categoryId);
	}

	@Override
	public void unassignTransactionsByUserID(int userID)
	{
		updateDatabase("Update Transactions Set transUserID = 1 where transUserID=" +userID);
	}

	@Override
	public void unassignTransactionsByCategoryID(int categoryID)
	{
		updateDatabase("Update Transactions Set transCategoryID = 1 where transCategoryID=" +categoryID);
	}

	@Override
	public void deleteTransactionByID(int transID)
	{
		updateDatabase("Delete from Transactions where transID=" +transID);
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
			cmdString = "Update Transactions Set transDate = '"+ new java.sql.Date(trans.getDate().getTime())
				+ "', transAmount = " + trans.getAmount() + ", transIsExpense = "+ trans.isExpense()
				+  ", transComment = '"+ trans.getComments() + "', transCategoryID = "+ trans.getCategoryID()
				+ ", transUserID = " + trans.getUserID() + " where transID = " + trans.getTransactionID();
			updateDatabase(cmdString);

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
