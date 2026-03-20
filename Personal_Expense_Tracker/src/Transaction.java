public class Transaction 
{
    int transactionId;
    String title;
    double amount;
    String type;
    String category;
    int month;
    int year;
    public Transaction(int transactionId, String title, double amount, String type, String category, int month, int year) 
    {
        this.transactionId = transactionId;
        this.title = title;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.month = month;
        this.year = year;
    }
    public int getTransactionId() { return transactionId; }
    public String getTitle() { return title; }
    public double getAmount() { return amount; }
    public String getType() { return type; }
    public String getCategory() { return category; }
    public int getMonth() { return month; }
    public int getYear() { return year; }
}