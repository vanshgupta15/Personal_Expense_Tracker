import java.awt.*;
import javax.swing.*;
public class TransactionViewerFrame extends JFrame 
{
    public TransactionViewerFrame(ExpenseManager manager) 
    {
        setTitle("All Transactions");
        setSize(600, 400);
        setLocationRelativeTo(null);
        Transaction[] arr = manager.getAllTransactions();
        int count = manager.getTransactionCount();
        // Table Data
        String[][] data = new String[count][6];
        for (int i = 0; i < count; i++) 
        {
            data[i][0] = String.valueOf(arr[i].getTransactionId());
            data[i][1] = arr[i].getTitle();
            data[i][2] = String.valueOf(arr[i].getAmount());
            data[i][3] = arr[i].getType();
            data[i][4] = arr[i].getCategory();
            data[i][5] = arr[i].getMonth() + "/" + arr[i].getYear();
        }
        String[] columns = {"ID", "Title", "Amount", "Type", "Category", "Date"};
        JTable table = new JTable(data, columns);
        // Styling
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(52, 152, 219));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setBackground(new Color(236, 240, 241));
        table.setGridColor(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(table);
        // Panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(52, 73, 94));
        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);
        setVisible(true);
    }
}