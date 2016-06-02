package group8.karina.objects;

public class Category
{
    private int catID;
    private String catName;
    private boolean catExpense;


    public Category( String newCatName, Boolean exp)
    {
        catName = newCatName;
        catExpense = exp;
        catID = -1;

    }

    public int getCategoryID()
    {
        return catID;
    }

    public void setCategoryID(int newID)
    {
        catID = newID;
    }

    public String getCategoryName()
    {
        return catName;
    }
    
    public boolean isExpense() {return catExpense;}

    public Category clone() { return new Category( catName, catExpense ); }

    public boolean equals(Object object)
    {
        boolean result;
        Category c;

        result = false;

        if (object instanceof Category)
        {
            c = (Category) object;
            if (c.catID==catID)
            {
                result = true;
            }
        }
        return result;
    }
}
