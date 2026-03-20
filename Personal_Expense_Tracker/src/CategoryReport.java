public class CategoryReport 
{
    String[] categories = {"Food", "Transport", "Shopping", "Bills", "Other"};
    double[] totals = new double[categories.length];
    public void calculateCategorySpending(Transaction[] arr, int count) 
    {
        for (int i = 0; i < totals.length; i++) 
        {
            totals[i] = 0;
        }
        for (int i = 0; i < count; i++) 
        {
            if (arr[i].getType().equals("EXPENSE")) 
            {
                for (int j = 0; j < categories.length; j++) 
                {
                    if (arr[i].getCategory().equals(categories[j])) 
                    {
                        totals[j] += arr[i].getAmount();
                    }
                }
            }
        }
    }
    public String[] getCategories() { return categories;}
    public double[] getCategoryTotals() { return totals; }
}