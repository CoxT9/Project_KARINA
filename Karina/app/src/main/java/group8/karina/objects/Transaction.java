package group8.karina.objects;
import java.util.Date;

public class Transaction
{
    private int transID;
    private Boolean expense;
    private double amount;
    private int categoryID;
    private int userID;
    private Date date;
    private String comments;

    public Transaction( Date newDate, int usr, Boolean exp, double amt, int cat,String com)
    {
        date=newDate;
        expense=exp;
        amount=amt;
        categoryID=cat;
        userID=usr;
        comments = com;
    }

    public int getTransactionID()
    {
        return (transID);
    }

    public Date getDate(){ return (date); }

    public int getUserID()
    {
        return (userID);
    }

    public Boolean isExpense()
    {
        return (expense);
    }

    public double getAmount()
    {
        return (amount);
    }

    public int getCategoryID()
    {
        return (categoryID);
    }

    public Transaction clone(){
        return new Transaction( date, userID, expense, amount, categoryID ,comments);
    }

    public void setTransactionID(int transID)
    {
        this.transID = transID;
    }

    public String getComments()
    {
        return this.comments;
    }
}
