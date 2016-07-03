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
	List<Category> getAllCategories();
	List<Category> getIncomeCategories();
	List<Category> getExpenseCategories();
	Category getCategoryByNameAndIsExpense(String targetName, boolean isExpense);
	void insertCategory(Category currentCategory) throws DuplicateEntryException;
	void deleteCategoryById(int categoryId);
	void updateCategory(Category category) throws unfoundResourceException;
	void insertTransaction(Transaction currentTransaction);
	List<Transaction> getTransactionsByType(boolean isExpense);
	Transaction getTransactionByID(int id);
	void deleteUserById(int UserId);
	void updateUser(User user) throws unfoundResourceException;
	void deleteTransactionsByUserID(int userId);
	void deleteTransactionsByCategoryID(int categoryId);
	void unassignTransactionsByUserID(int userID);
	void unassignTransactionsByCategoryID(int categoryID);
	void deleteTransactionByID(int transID);
	void updateTransaction(Transaction trans) throws unfoundResourceException;
	List<Transaction> getOrderedTransactionsByDate();
	List<Transaction> getOrderedTransactionsByDate(boolean isExpense);
	List<Transaction> getOrderedTransactionsByUser(boolean isExpense);
	List<Transaction> getOrderedTransactionsByCategory(boolean isExpense);
	List<Transaction> getTotalTransactionsByCategory(boolean isExpense);
	List<Transaction> getTotalTransactionsByUser(boolean isExpense);
}