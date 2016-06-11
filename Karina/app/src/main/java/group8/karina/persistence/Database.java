package group8.karina.persistence;

import java.util.List;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.Exceptions.unfoundResourceException;
import group8.karina.objects.Category;
import group8.karina.objects.Transaction;
import group8.karina.objects.User;

public interface Database
{
	void open(String dbName);
	void close();
	List<User> getUserSequential();
	void insertUser(User currentUser) throws DuplicateEntryException;
	User getUserByName(String targetName);
	User getUserById(int uID);
	Category getCategoryById(int cID);
	List getAllCategories();
	List getIncomeCategories();
	List getExpenseCategories();
	Category getCategoryByNameAndIsExpense(String targetName, boolean isExpense);
	void insertCategory(Category currentCategory) throws DuplicateEntryException;
	void insertTransaction(Transaction currentTransaction);
	List<Transaction> getTransactionsByType(boolean isExpense);
	Transaction getTransactionByID(int id);
	void deleteUserById(int UserId);
	void updateUser(User user) throws unfoundResourceException;
	void deleteTransactionsByUserID(int userId);
}