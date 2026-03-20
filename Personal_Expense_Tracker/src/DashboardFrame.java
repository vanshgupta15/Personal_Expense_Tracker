import java.awt.*;
import javax.swing.*;
public class DashboardFrame extends JFrame 
{
    private ExpenseManager manager;
    private BudgetManager budgetManager;
    public DashboardFrame(ExpenseManager manager, BudgetManager budgetManager) {
        this.manager = manager;
        this.budgetManager = budgetManager;
        setTitle("Expense Tracker Dashboard");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeUI();
    }
    public void initializeUI() 
    {
        // Main Panel with Gradient Effect
        JPanel mainPanel = new JPanel() 
        {
            protected void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color c1 = new Color(58, 123, 213);
                Color c2 = new Color(0, 210, 255);
                GradientPaint gp = new GradientPaint(0, 0, c1, 0, getHeight(), c2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new GridLayout(6, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        JLabel title = new JLabel("Budget Manager", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        JButton addIncome = createButton("Add Income", new Color(46, 204, 113));
        JButton addExpense = createButton("Add Expense", new Color(231, 76, 60));
        JButton view = createButton("View Transactions", new Color(38, 80, 70));
        JButton summary = createButton("Monthly Summary", new Color(155, 89, 182));
        JButton balance = createButton("Show Balance", new Color(241, 196, 15));
        // Actions
        addIncome.addActionListener(e -> new AddIncomeFrame(manager));
        addExpense.addActionListener(e -> new AddExpenseFrame(manager));
        view.addActionListener(e -> new TransactionViewerFrame(manager));
        summary.addActionListener(e -> new MonthlySummaryFrame(manager, budgetManager));
        balance.addActionListener(e ->
            JOptionPane.showMessageDialog(this,
                "Total Balance: ₹ " + manager.getTotalBalance())
        );
        mainPanel.add(title);
        mainPanel.add(addIncome);
        mainPanel.add(addExpense);
        mainPanel.add(view);
        mainPanel.add(summary);
        mainPanel.add(balance);
        add(mainPanel);
        setVisible(true);
    }
    // Reusable Button Styling
    private JButton createButton(String text, Color bgColor) 
    {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return btn;
    }
}