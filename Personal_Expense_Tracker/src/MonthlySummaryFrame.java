import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MonthlySummaryFrame extends JFrame {
    public MonthlySummaryFrame(ExpenseManager manager, BudgetManager budgetManager) {
        setTitle("Monthly Summary");
        setSize(500, 500);
        setLocationRelativeTo(null);
        Theme.applyBaseTheme(this);

        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        mainPanel.setBackground(Theme.BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Header
        JLabel headerLabel = Theme.createLabel("📅 Monthly Summary", Theme.FONT_TITLE, Theme.SECONDARY_COLOR);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = Theme.createCardPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 10, 10));
        JTextField monthField = Theme.createTextField();
        JTextField yearField = Theme.createTextField();
        inputPanel.add(Theme.createLabel("Month (1-12):", Theme.FONT_BOLD, Theme.TEXT_PRIMARY));
        inputPanel.add(monthField);
        inputPanel.add(Theme.createLabel("Year:", Theme.FONT_BOLD, Theme.TEXT_PRIMARY));
        inputPanel.add(yearField);

        // Results Panel
        JPanel resultsPanel = Theme.createCardPanel();
        resultsPanel.setLayout(new GridLayout(4, 1, 10, 10));
        JLabel incomeLabel = Theme.createLabel("Income: --", Theme.FONT_HEADER, Theme.INCOME_COLOR);
        JLabel expenseLabel = Theme.createLabel("Expense: --", Theme.FONT_HEADER, Theme.EXPENSE_COLOR);
        JLabel balanceLabel = Theme.createLabel("Balance: --", Theme.FONT_HEADER, Theme.TEXT_PRIMARY);
        JLabel budgetLabel = Theme.createLabel("Budget Status: --", Theme.FONT_HEADER, Theme.WARNING_COLOR);

        resultsPanel.add(incomeLabel);
        resultsPanel.add(expenseLabel);
        resultsPanel.add(balanceLabel);
        resultsPanel.add(budgetLabel);

        // Center Wrapper
        JPanel centerWrapper = new JPanel(new BorderLayout(0, 20));
        centerWrapper.setBackground(Theme.BACKGROUND);
        centerWrapper.add(inputPanel, BorderLayout.NORTH);
        centerWrapper.add(resultsPanel, BorderLayout.CENTER);
        
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        // Generate Button
        JButton generateBtn = Theme.createButton("Generate Summary", Theme.SECONDARY_COLOR);
        generateBtn.addActionListener(e -> {
            try {
                int month = Integer.parseInt(monthField.getText().trim());
                int year = Integer.parseInt(yearField.getText().trim());
                
                if (month < 1 || month > 12) {
                    showError("Enter a valid month (1-12)");
                    return;
                }
                
                MonthlyReportGenerator gen = new MonthlyReportGenerator();
                double income = gen.calculateMonthlyIncome(
                        manager.getAllTransactions(), manager.getTransactionCount(), month, year);
                double expense = gen.calculateMonthlyExpense(
                        manager.getAllTransactions(), manager.getTransactionCount(), month, year);
                double balance = income - expense;

                incomeLabel.setText("Income: ₹ " + income);
                expenseLabel.setText("Expense: ₹ " + expense);
                balanceLabel.setText("Balance: ₹ " + balance);
                
                // Colorize balance
                balanceLabel.setForeground(balance >= 0 ? Theme.INCOME_COLOR : Theme.EXPENSE_COLOR);

                double budget = budgetManager.getBudget();
                if (budget == 0) {
                    budgetLabel.setText("Budget Not Set");
                    budgetLabel.setForeground(Theme.TEXT_SECONDARY);
                } else if (balance <= 0) {
                    budgetLabel.setText("Budget Exhausted! (" + budget + ")");
                    budgetLabel.setForeground(Theme.EXPENSE_COLOR);
                } else {
                    budgetLabel.setText("Budget Open (" + budget + ")");
                    budgetLabel.setForeground(Theme.INCOME_COLOR);
                }

            } catch (NumberFormatException ex) {
                showError("Invalid Input! Please enter numeric values.");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Theme.BACKGROUND);
        buttonPanel.add(generateBtn);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void showError(String message) {
        UIManager.put("OptionPane.background", Theme.CARD_BACKGROUND);
        UIManager.put("Panel.background", Theme.CARD_BACKGROUND);
        UIManager.put("OptionPane.messageForeground", Theme.TEXT_PRIMARY);
        JOptionPane.showMessageDialog(this, "⚠ " + message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}