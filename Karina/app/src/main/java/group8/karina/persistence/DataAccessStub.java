package group8.karina.persistence;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.application.KarinaApp;
import group8.karina.application.KarinaApp;
import group8.karina.objects.Category;
import group8.karina.objects.Transaction;
import group8.karina.objects.User;


public class DataAccessStub
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
		nextCategoryID =1;
		nextTransactionID =1;
		nextUserID =1;
	}

	public DataAccessStub()
	{
		this(KarinaApp.dbName);
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
			user = new User("Jon");
			insertUser(user);
			user = new User("Bran");
			insertUser(user);
			user = new User("Aria");
			insertUser(user);
			user = new User("Sansa");
			insertUser(user);
			user = new User("default");
			insertUser(user);

			category = new Category( "groceries", true);
			insertCategory(category);
			category = new Category( "weapons", true);
			insertCategory(category);
			category = new Category( "entertainment", true);
			insertCategory(category);
			category = new Category( "income", false);
			insertCategory(category);
			category = new Category( "default", true);
			insertCategory(category);


			myTransaction = new Transaction( new Date(), 1, true, 50, 1,null);
			insertTransaction(myTransaction);
			myTransaction = new Transaction( new Date(), 4, true, 30, 3,null);
			insertTransaction(myTransaction);
			myTransaction = new Transaction( new Date(), 3, true, 80, 2,null);
			insertTransaction(myTransaction);
			myTransaction = new Transaction( new Date(), 2, true, 12.75, 1,null);
			insertTransaction(myTransaction);
			myTransaction = new Transaction( new Date(), 1, false, 100, 4,null);
			insertTransaction(myTransaction);
			myTransaction = new Transaction( new Date(), 4, false, 51.34, 4,null);
			insertTransaction(myTransaction);

		} catch (DuplicateEntryException e)
		{
			e.printStackTrace();
		}
		System.out.println("Opened " +dbType +" database " +dbName);
	}

	public void close()
	{
		System.out.println("Closed " +dbType +" database " +dbName);
	}

	public List<User> getUserSequential()
	{
		List<User> result = new ArrayList<User>();
		for( User u : users)
		{
			result.add(u.clone());
		}
		return result;
	}


	public String insertUser(User currentUser) throws DuplicateEntryException
	{
        int index = users.indexOf(currentUser);

		if(index >=0 )
		{
			throw new DuplicateEntryException("Duplicate detected when entering user into database");
		}
		currentUser.setUserID(nextUserID);
		nextUserID++;
		users.add(currentUser);

		return null;
	}

	public String updateUser(User currentUser)
	{
		int index;
		
		index = users.indexOf(currentUser);
		if (index >= 0)
		{
			users.set(index, currentUser);
		}
		return null;
	}

	public String deleteUser(User currentUser)
	{
		int index;
		
		index = users.indexOf(currentUser);
		if (index >= 0)
		{
			users.remove(index);
		}
		return null;
	}

	public User getUserByName(String targetName)
	{
		for (User u: users)
		{
			if(u.getUserName().equals(targetName))
			{
				return u;
			}
		}

		return null;
	}

	public List getAllCategories()
	{
		List<Category> result = new ArrayList<Category>();
		for( Category cat : categories)
		{
			result.add(cat.clone());
		}
		return result;
	}

	public List getIncomeCategories()
	{
		List<Category> incomeCategories = getAllCategories();
		int count = 0;

		while (count < incomeCategories.size())
		{
			if (incomeCategories.get(count).isExpense())
			{
				incomeCategories.remove(count);
			}
			else
			{
				count++;
			}
		}

		return(incomeCategories);
	}

	public List getExpenseCategories()
	{
		List<Category> expenseCategories = getAllCategories();
		int count = 0;

		while (count < expenseCategories.size())
		{
			if (!expenseCategories.get(count).isExpense())
			{expenseCategories.remove(count);
			}
			else
			{
				count++;
			}
		}

		return(expenseCategories);
	}

	public Category getCategoryByNameAndIsExpense(String targetName, boolean isExpense)
	{
		for (Category cat: categories)
		{
			if(cat.getCategoryName().equals(targetName) && cat.isExpense() == isExpense)
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

		}
		else
		{
			throw new DuplicateEntryException("Duplicate detected when entering category into database");
		}

	}

	public String updateCategory(Category currentCategory)
	{
		int index;
		
		index = categories.indexOf(currentCategory);
		if (index >= 0)
		{
			categories.set(index, currentCategory);
		}
		return null;
	}

	public String deleteCategory(Category currentCategory)
	{
		int index;
		
		index = categories.indexOf(currentCategory);
		if (index >= 0)
		{
			categories.remove(index);
		}
		return null;
	}

    public ArrayList<Transaction> getTransactionRandom(Transaction currentTransaction)
    {
        ArrayList<Transaction> newTransactions;
        int index;

        newTransactions = new ArrayList<Transaction>();
        index = transactions.indexOf(currentTransaction);
        if (index >= 0)
        {
            newTransactions.add(transactions.get(index));
        }
        return newTransactions;
    }

    public String insertTransaction(Transaction currentTransaction)
    {
        // don't bother checking for duplicates
		currentTransaction.setTransactionID(nextTransactionID);
        transactions.add(currentTransaction);
		nextTransactionID++;
        return null;
    }

    public String updateTransaction(Transaction currentTransaction)
    {
        int index;

        index = transactions.indexOf(currentTransaction);
        if (index >= 0)
        {
            transactions.set(index, currentTransaction);
        }
        return null;
    }

    public String deleteTransaction(Transaction currentTransaction)
    {
        int index;

        index = transactions.indexOf(currentTransaction);
        if (index >= 0)
        {
            transactions.remove(index);
        }
        return null;
    }

	public ArrayList<Transaction> getUserTransactions(Transaction currentTransaction)
	{
		ArrayList<Transaction> newTransactions;
		Transaction transaction;
		int counter;
		
		// get the Transaction objects with the same userID as currentTransaction
		newTransactions = new ArrayList<Transaction>();
		for (counter=0; counter<transactions.size(); counter++)
		{
			transaction = transactions.get(counter);
			if (transaction.getUserID()==currentTransaction.getUserID())
			{
				newTransactions.add(transactions.get(counter));
			}
		}
		return newTransactions;
	}

    public ArrayList<Transaction> getTransactionsByCategory(Category currentCategory)
    {
        ArrayList<Transaction> result = new ArrayList<Transaction>();
		for( Transaction tr : transactions )
		{
			if( tr.getCategoryID() == currentCategory.getCategoryID() )
			{
				result.add(tr.clone());
			}
		}
		return result;
    }

	public List<Transaction> getTransactionsByType(boolean isExpense)
	{
		ArrayList<Transaction> result = new ArrayList<Transaction>();
		for( Transaction tr : transactions)
		{
			if(isExpense && tr.isExpense())
			{
				result.add(tr.clone());
			}
			else if(!isExpense && !tr.isExpense())
			{
				result.add(tr.clone());
			}
		}
		return result;
	}

	public List<Transaction> getAllTransactions()
	{
		ArrayList<Transaction> result = new ArrayList<Transaction>();
		for( Transaction tr : transactions)
		{
			result.add(tr.clone());
		}
		return result;
	}

	public Transaction getTransactionByID(int id)
	{
		for( Transaction tr : transactions)
		{
			if(tr.getTransactionID() == id)
			{
				return tr.clone();
			}
		}

		return null;
	}
}
