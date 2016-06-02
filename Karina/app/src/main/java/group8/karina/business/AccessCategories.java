package group8.karina.business;

import java.util.List;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.application.Services;
import group8.karina.objects.Category;
import group8.karina.persistence.DataAccessStub;

public class AccessCategories
{
	private DataAccessStub dataAccess;

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


	public void insertCategory(Category currentCategory) throws DuplicateEntryException
	{
		if (currentCategory.getCategoryName() == null || currentCategory.getCategoryName().isEmpty())
		{
			throw new IllegalArgumentException("Null or empty value for Category");
		}

		dataAccess.insertCategory(currentCategory);
	}
}
