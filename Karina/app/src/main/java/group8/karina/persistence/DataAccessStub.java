package group8.karina.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.Exceptions.unfoundResourceException;
import group8.karina.objects.Category;
import group8.karina.objects.Transaction;
import group8.karina.objects.User;


public class DataAccessStub implements Database
{
	private String dbName;
	private String dbType = "stub";

	private ArrayList<User> users;
	private ArrayList<Category> categories;
	private ArrayList<Transaction> transactions;

	private int nextCategoryID;
	private int nextUserID;
	private int nextTransactionID;


	public DataAccessStub(String dbName)
	{
		this.dbName = dbName;
		nextCategoryID = 1;
		nextTransactionID = 1;
		nextUserID = 1;
	}

	public void open(String dbName)
	{
		User user;
		Category category;
		Transaction myTransaction;
		users = new ArrayList<User>();
		categories = new ArrayList<Category>();
		transactions = new ArrayList<Transaction>();
		try
		{
			user = new User("Default");
			insertUser(user);

			user = new User("Jon");
			insertUser(user);

			user = new User("Bran");
			insertUser(user);

			user = new User("Aria");
			insertUser(user);

			user = new User("Sansa");
			insertUser(user);

			category = new Category("Default", true);
			insertCategory(category);

			category = new Category("groceries", true);
			insertCategory(category);
			category = new Category("weapons", true);
			insertCategory(category);
			category = new Category("entertainment", true);
			insertCategory(category);
			category = new Category("income", false);
			insertCategory(category);
			category = new Category("Default", false);
			insertCategory(category);


			myTransaction = new Transaction(new Date(), 1, true, 50, 1, null);
			insertTransaction(myTransaction);
			myTransaction = new Transaction(new Date(), 4, true, 30, 3, null);
			insertTransaction(myTransaction);
			myTransaction = new Transaction(new Date(), 3, true, 80, 2, null);
			insertTransaction(myTransaction);
			myTransaction = new Transaction(new Date(), 2, true, 12.75, 1, null);
			insertTransaction(myTransaction);
			myTransaction = new Transaction(new Date(), 1, false, 100, 5, null);
			insertTransaction(myTransaction);
			myTransaction = new Transaction(new Date(), 4, false, 51.34, 5, null);
			insertTransaction(myTransaction);

		} catch (DuplicateEntryException e)
		{
			e.printStackTrace();
		}
		System.out.println("Opened " + dbType + " database " + dbName);
	}

	public void close()
	{
		System.out.println("Closed " + dbType + " database " + dbName);
	}

	public List<User> getAllUsers()
	{
		List<User> result = new ArrayList<User>();
		for (User u : users)
		{
			result.add((User)u.clone());
		}
		return result;
	}


	public void insertUser(User currentUser) throws DuplicateEntryException
	{
		User found = getUserByName(currentUser.getUserName());

		if (found != null)
		{
			throw new DuplicateEntryException("Duplicate detected when entering user into database");
		}
		currentUser.setUserID(nextUserID);
		nextUserID++;
		users.add(currentUser);
	}

	public User getUserByName(String targetName)
	{
		for (User u : users)
		{
			if (u.getUserName().equals(targetName))
			{
				return u;
			}
		}

		return null;
	}

	public User getUserById(int uID)
	{
		for (User u : users)
		{
			if(u.getUserID() == uID)
			{
				return u;
			}
		}
		return null;
	}

	public Category getCategoryById(int cID)
	{
		for(Category c : categories)
		{
			if(c.getCategoryID() == cID)
			{
				return c;
			}
		}
		return null;
	}

	public List<Category> getAllCategories()
	{
		List<Category> result = new ArrayList<Category>();
		for (Category cat : categories)
		{
			result.add((Category)cat.clone());
		}
		return result;
	}

	public List<Category> getIncomeCategories()
	{
		List<Category> incomeCategories = getAllCategories();
		int count = 0;

		while (count < incomeCategories.size())
		{
			if (incomeCategories.get(count).isExpense())
			{
				incomeCategories.remove(count);
			} else
			{
				count++;
			}
		}

		return (incomeCategories);
	}

	public List<Category> getExpenseCategories()
	{
		List<Category> expenseCategories = getAllCategories();
		int count = 0;

		while (count < expenseCategories.size())
		{
			if (!expenseCategories.get(count).isExpense())
			{
				expenseCategories.remove(count);
			} else
			{
				count++;
			}
		}

		return (expenseCategories);
	}

	public Category getCategoryByNameAndIsExpense(String targetName, boolean isExpense)
	{
		for (Category cat : categories)
		{
			if (cat.getCategoryName().equals(targetName) && cat.isExpense() == isExpense)
			{
				return cat;
			}
		}

		return null;
	}

	public void insertCategory(Category currentCategory) throws DuplicateEntryException
	{
		Category found = getCategoryByNameAndIsExpense(currentCategory.getCategoryName(), currentCategory.isExpense());
		if (found == null)
		{
			currentCategory.setCategoryID(nextCategoryID);
			categories.add(currentCategory);
			nextCategoryID++;

		} else
		{
			throw new DuplicateEntryException("Duplicate detected when entering category into database");
		}

	}

	public void deleteCategoryById(int categoryID)
	{
		for(Category c : categories)
		{
			if(c.getCategoryID() == categoryID)
			{
				categories.remove(c);
				break;
			}
		}
	}

	public void updateCategory(Category category) throws unfoundResourceException
	{
		Category editCat = null;

		for(Category c : categories)
		{
			if(c.getCategoryID() == category.getCategoryID())
			{
				editCat = c;
				break;
			}
		}

		if(editCat == null)
		{
			throw new unfoundResourceException("Could not find category ID: "+category.getCategoryID());
		}
		else
		{
			categories.remove(editCat);
			categories.add(category);
		}
	}

	public void insertTransaction(Transaction currentTransaction)
	{
		// don't bother checking for duplicates
		currentTransaction.setTransactionID(nextTransactionID);
		transactions.add(currentTransaction);
		nextTransactionID++;
	}

	public List<Transaction> getTransactionsByType(boolean isExpense)
	{
		ArrayList<Transaction> result = new ArrayList<Transaction>();
		for (Transaction tr : transactions)
		{
			if (isExpense && tr.isExpense())
			{
				result.add((Transaction)tr.clone());
			} else if (!isExpense && !tr.isExpense())
			{
				result.add((Transaction)tr.clone());
			}
		}
		return result;
	}

	public Transaction getTransactionByID(int id)
	{
		for (Transaction tr : transactions)
		{
			if (tr.getTransactionID() == id)
			{
				return (Transaction)tr.clone();
			}
		}

		return null;
	}

	public void deleteUserById(int userId)
	{
		for(User u : users)
		{
			if(u.getUserID() == userId)
			{
				users.remove(u);
				break;
			}
		}
	}

	public void updateUser(User user) throws unfoundResourceException
	{
		User editUser = null;

		for(User u : users)
		{
			if(u.getUserID() == user.getUserID())
			{
				editUser = u;
				break;
			}
		}

		if(editUser == null)
		{
			throw new unfoundResourceException("Could not find user : "+user.getUserName());
		}
		else
		{
			users.remove(editUser);
			users.add(user);
		}
	}

	public void deleteTransactionsByUserID(int userID)
	{
		ArrayList<Transaction> removalTransactions = new ArrayList<Transaction>();

		for(Transaction t : transactions)
		{
			if(t.getUserID() == userID)
			{
				removalTransactions.add(t);
			}
		}

		for(Transaction t : removalTransactions)
		{
			transactions.remove(t);
		}
	}

	public void deleteTransactionsByCategoryID(int categoryId)
	{
		ArrayList<Transaction> removalTransactions = new ArrayList<Transaction>();

		for(Transaction t : transactions)
		{
			if(t.getCategoryID() == categoryId)
			{
				removalTransactions.add(t);
			}
		}

		for(Transaction t : removalTransactions)
		{
			transactions.remove(t);
		}
	}

	@Override
	public void unassignTransactionsByUserID(int userID)
	{
		for(Transaction t : transactions)
		{
			if(t.getUserID() == userID)
			{
				t.setUserID(1);
			}
		}
	}

	@Override
	public void unassignTransactionsByCategoryID(int categoryID)
	{
		for(Transaction t : transactions)
		{
			if(t.getCategoryID() == categoryID)
			{
				t.setCategoryID(1);
			}
		}
	}

	public void deleteTransactionByID(int transID){
		for(Transaction t : transactions)
		{
			if(t.getTransactionID() == transID)
			{
				transactions.remove(t);
			}
		}
	}
	public List<Transaction> getOrderedTransactionsByUser(boolean isExpense)
	{
		return null;
	}

	@Override
	public List<Transaction> getOrderedTransactionsByCategory(boolean isExpense) {
		return null;
	}

	public void updateTransaction(Transaction trans) throws unfoundResourceException
	{
		Transaction editTrans = null;

		for(Transaction t : transactions)
		{
			if(t.getTransactionID() == trans.getTransactionID())
			{
				editTrans = t;
				break;
			}
		}

		if(editTrans == null)
		{
			throw new unfoundResourceException("Could not find transaction #: "+trans.getTransactionID());
		}
		else
		{
			transactions.remove(editTrans);
			transactions.add(trans);
		}
	}

	public List<Transaction> getOrderedTransactionsByDate()
	{
		return null;
	}

	public List<Transaction> getOrderedTransactionsByDateAndType(boolean isExpense)
	{
		return null;
	}
}
