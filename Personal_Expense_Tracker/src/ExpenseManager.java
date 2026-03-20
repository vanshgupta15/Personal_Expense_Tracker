public class ExpenseManager 
{
    int transactionCount;
    int maxSize;
    Transaction transactions[];
    int idCounter = 1;
    public ExpenseManager(int size) 
    {
        maxSize = size;
        transactions = new Transaction[maxSize];
        transactionCount = 0;
    }
    public void addIncome(String title, double amount, String category, int month, int year) 
    {
        if (transactionCount < maxSize) {
            transactions[transactionCount++] =
                new Transaction(idCounter++, title, amount, "INCOME", category, month, year);
        }
    }
    public void addExpense(String title, double amount, String category, int month, int year) 
    {
        if (transactionCount < maxSize) {
            transactions[transactionCount++] =
                new Transaction(idCounter++, title, amount, "EXPENSE", category, month, year);
        }
    }
    public Transaction[] getAllTransactions() 
    {
        return transactions;
    }
    public int getTransactionCount()
    {
        return transactionCount;
    }
    public double getTotalIncome() 
    {
        double total = 0;
        for (int i = 0; i < transactionCount; i++) 
        {
            if (transactions[i].getType().equals("INCOME")) 
            {
                total += transactions[i].getAmount();
            }
        }
        return total;
    }
    public double getTotalExpense() 
    {
        double total = 0;
        for (int i = 0; i < transactionCount; i++) 
        {
            if (transactions[i].getType().equals("EXPENSE")) 
            {
                total += transactions[i].getAmount();
            }
        }
        return total;
    }
    public double getTotalBalance() 
    {
        return getTotalIncome() - getTotalExpense();
    }
}