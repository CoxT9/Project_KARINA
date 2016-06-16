package group8.karina.business;

import java.util.List;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.Exceptions.unfoundResourceException;
import group8.karina.application.Services;
import group8.karina.objects.Category;
import group8.karina.persistence.Database;

public class AccessCategories
{
	private Database dataAccess;

	public AccessCategories()
	{
		dataAccess = Services.getDataAccess();
	}

	public List<Category> getAllCategories()
	{
		return dataAccess.getAllCategories();
	}

	public List<Category> getIncomeCategories()
	{
		return dataAccess.getIncomeCategories();
	}

	public List<Category> getExpenseCategories()
	{
		return dataAccess.getExpenseCategories();
	}

	public Category getCategoryByID(int categoryID) { return dataAccess.getCategoryById(categoryID); }


	public void insertCategory(Category currentCategory) throws DuplicateEntryException
	{
		if (currentCategory.getCategoryName() == null || currentCategory.getCategoryName().isEmpty())
		{
			throw new IllegalArgumentException("Null or empty value for Category");
		}
		dataAccess.insertCategory(currentCategory);
	}

	public void deleteCategoryById(int categoryID)
	{
		dataAccess.deleteCategoryById(categoryID);
	}

	public void updateCategory(Category category) throws unfoundResourceException
	{
		dataAccess.updateCategory(category);
	}
}
