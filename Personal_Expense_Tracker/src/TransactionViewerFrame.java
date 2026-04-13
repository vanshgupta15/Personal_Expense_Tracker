import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class TransactionViewerFrame extends JFrame {
    public TransactionViewerFrame(ExpenseManager manager) {
        setTitle("All Transactions");
        setSize(700, 500);
        setLocationRelativeTo(null);
        Theme.applyBaseTheme(this);

        JPanel mainPanel = new JPanel(new BorderLayout(0, 15));
        mainPanel.setBackground(Theme.BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel headerLabel = Theme.createLabel("📊 Transaction History", Theme.FONT_TITLE, Theme.PRIMARY_COLOR);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        Transaction[] arr = manager.getAllTransactions();
        int count = manager.getTransactionCount();

        // Table Data
        String[][] data = new String[count][6];
        for (int i = 0; i < count; i++) {
            data[i][0] = String.valueOf(arr[i].getTransactionId());
            data[i][1] = arr[i].getTitle();
            data[i][2] = "₹ " + arr[i].getAmount();
            data[i][3] = arr[i].getType();
            data[i][4] = arr[i].getCategory();
            data[i][5] = arr[i].getMonth() + "/" + arr[i].getYear();
        }

        String[] columns = {"ID", "Title", "Amount", "Type", "Category", "Date"};
        JTable table = new JTable(data, columns);
        
        // Table Styling
        table.setFont(Theme.FONT_REGULAR);
        table.setForeground(Theme.TEXT_PRIMARY);
        table.setBackground(Theme.CARD_BACKGROUND);
        table.setGridColor(new Color(50, 50, 50));
        table.setRowHeight(35);
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFocusable(false);

        // Header Styling
        JTableHeader header = table.getTableHeader();
        header.setFont(Theme.FONT_HEADER);
        header.setBackground(Theme.PRIMARY_COLOR);
        header.setForeground(Theme.TEXT_PRIMARY);
        header.setPreferredSize(new Dimension(100, 40));

        // Cell Renderer for Income/Expense coloring
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                String type = (String) table.getModel().getValueAt(row, 3);
                if ("Income".equalsIgnoreCase(type)) {
                    c.setForeground(Theme.INCOME_COLOR);
                } else if ("Expense".equalsIgnoreCase(type)) {
                    c.setForeground(Theme.EXPENSE_COLOR);
                } else {
                    c.setForeground(Theme.TEXT_PRIMARY); // Default
                }
                
                if (isSelected) {
                    c.setBackground(new Color(60, 60, 60));
                } else {
                    c.setBackground(Theme.CARD_BACKGROUND);
                }
                return c;
            }
        };
        table.setDefaultRenderer(Object.class, renderer);
        
        // Custom Scroll Pane Border
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Theme.BACKGROUND);
        scrollPane.setBorder(BorderFactory.createLineBorder(Theme.PRIMARY_COLOR, 1));
        
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }
}