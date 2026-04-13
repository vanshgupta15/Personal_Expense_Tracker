import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DashboardFrame extends JFrame {
    private ExpenseManager manager;
    private BudgetManager budgetManager;

    public DashboardFrame(ExpenseManager manager, BudgetManager budgetManager) {
        this.manager = manager;
        this.budgetManager = budgetManager;
        
        setTitle("Personal Expense Tracker");
        setSize(550, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        Theme.applyBaseTheme(this);
        initializeUI();
    }

    public void initializeUI() {
        // Main Container Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 20)); // Spacing between header and buttons
        mainPanel.setBackground(Theme.BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

        // Header Panel (Gradient)
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, Theme.PRIMARY_COLOR, getWidth(), getHeight(), new Color(30, 60, 114));
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Rounded Header
                g2d.dispose();
            }
        };
        headerPanel.setPreferredSize(new Dimension(getWidth(), 120));
        headerPanel.setLayout(new GridBagLayout());
        headerPanel.setOpaque(false);

        JLabel title = new JLabel("Overview Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Theme.TEXT_PRIMARY);
        headerPanel.add(title);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 1, 15, 15));
        buttonsPanel.setBackground(Theme.BACKGROUND);

        JButton addIncome = Theme.createButton("➕ Add Income", Theme.INCOME_COLOR);
        JButton addExpense = Theme.createButton("➖ Add Expense", Theme.EXPENSE_COLOR);
        JButton view = Theme.createButton("📊 View Transactions", Theme.PRIMARY_COLOR);
        JButton summary = Theme.createButton("📅 Monthly Summary", Theme.SECONDARY_COLOR);
        JButton balance = Theme.createButton("💰 Show Balance", Theme.WARNING_COLOR);

        // Actions
        addIncome.addActionListener(e -> new AddIncomeFrame(manager));
        addExpense.addActionListener(e -> new AddExpenseFrame(manager));
        view.addActionListener(e -> new TransactionViewerFrame(manager));
        summary.addActionListener(e -> new MonthlySummaryFrame(manager, budgetManager));
        balance.addActionListener(e -> {
            UIManager.put("OptionPane.background", Theme.CARD_BACKGROUND);
            UIManager.put("Panel.background", Theme.CARD_BACKGROUND);
            UIManager.put("OptionPane.messageForeground", Theme.TEXT_PRIMARY);
            JOptionPane.showMessageDialog(this,
                    "Total Balance: ₹ " + manager.getTotalBalance(),
                    "Balance Information", JOptionPane.INFORMATION_MESSAGE);
        });

        buttonsPanel.add(addIncome);
        buttonsPanel.add(addExpense);
        buttonsPanel.add(view);
        buttonsPanel.add(summary);
        buttonsPanel.add(balance);

        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }
}