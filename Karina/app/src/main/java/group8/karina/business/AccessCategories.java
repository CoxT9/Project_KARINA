package group8.karina.business;

import java.util.ArrayList;

import group8.karina.application.Services;
import group8.karina.objects.Category;
import group8.karina.persistence.DataAccessStub;

public class AccessCategories
{
    private DataAccessStub dataAccess;
    private ArrayList<Category> Categories;
    private Category Category;
    private boolean isExpense;

    public AccessCategories()
    {
        dataAccess = Services.getDataAccess();
    }

    public ArrayList<Category> getCategories()
    {
        return dataAccess.getCategorySequential();
    }

    public String insertCategory(Category currentCategory)
    {
        if(currentCategory.getCategoryName() == null || currentCategory.getCategoryName().isEmpty())
        {
            throw new IllegalArgumentException("Null or empty value for Category");
        }

        return dataAccess.insertCategory(currentCategory);
    }
    public String updateCategory(Category currentCategory)
    {
        return dataAccess.updateCategory(currentCategory);
    }

    public String deleteCategory(Category currentCategory)
    {
        return dataAccess.deleteCategory(currentCategory);
    }
}
