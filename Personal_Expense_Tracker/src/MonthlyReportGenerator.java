public class MonthlyReportGenerator 
{
    public double calculateMonthlyIncome(Transaction[] arr, int count, int month, int year) 
    {
        double total = 0;
        for (int i = 0; i < count; i++) 
        {
            if (arr[i].getMonth() == month && arr[i].getYear() == year && arr[i].getType().equals("INCOME")) 
            {
                total += arr[i].getAmount();
            }
        }
        return total;
    }
    public double calculateMonthlyExpense(Transaction[] arr, int count, int month, int year) 
    {
        double total = 0;
        for (int i = 0; i < count; i++) 
        {
            if (arr[i].getMonth() == month && arr[i].getYear() == year && arr[i].getType().equals("EXPENSE")) 
            {
                total += arr[i].getAmount();
            }
        }
        return total;
    }
    public double calculateMonthlyBalance(Transaction[] arr, int count, int month, int year) 
    {
        return calculateMonthlyIncome(arr, count, month, year) -calculateMonthlyExpense(arr, count, month, year);
    }
}