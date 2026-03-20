public class BudgetManager 
{
    double monthlyBudget;
    void setBudget(double amount) 
    {
        monthlyBudget = amount;
    }
    public double getBudget() 
    {
        return monthlyBudget;
    }
    public boolean isBudgetExceeded(double totalExpense) 
    {
        return totalExpense > monthlyBudget;
    }
    public double remainingBudget(double totalExpense) 
    {
        return monthlyBudget - totalExpense;
    }
}