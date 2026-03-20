public class MainApp {

    public static void main(String[] args) 
    {

        ExpenseManager manager = new ExpenseManager(500);
        BudgetManager budgetManager = new BudgetManager();

        new DashboardFrame(manager, budgetManager);
    }
}