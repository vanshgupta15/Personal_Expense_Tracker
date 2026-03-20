import java.awt.*;
import javax.swing.*;

public class MonthlySummaryFrame extends JFrame {

    public MonthlySummaryFrame(ExpenseManager manager, BudgetManager budgetManager) {

        setTitle("Monthly Summary 📊");
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 1, 10, 10));
        panel.setBackground(new Color(155, 89, 182)); // Purple Theme
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Input Fields
        JTextField monthField = new JTextField();
        JTextField yearField = new JTextField();

        JButton generateBtn = new JButton("Generate Summary");
        generateBtn.setBackground(Color.WHITE);
        generateBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Output Labels
        JLabel incomeLabel = createLabel("Income: ");
        JLabel expenseLabel = createLabel("Expense: ");
        JLabel balanceLabel = createLabel("Balance: ");
        JLabel budgetLabel = createLabel("Budget Status: ");

        // Action Button
        generateBtn.addActionListener(e -> {

            try {
                int month = Integer.parseInt(monthField.getText());
                int year = Integer.parseInt(yearField.getText());

                if (month < 1 || month > 12) {
                    JOptionPane.showMessageDialog(this, "⚠ Enter valid month (1-12)");
                    return;
                }

                MonthlyReportGenerator gen = new MonthlyReportGenerator();

                double income = gen.calculateMonthlyIncome(
                        manager.getAllTransactions(),
                        manager.getTransactionCount(),
                        month, year);

                double expense = gen.calculateMonthlyExpense(
                        manager.getAllTransactions(),
                        manager.getTransactionCount(),
                        month, year);

                double balance = income - expense;

                // Update Labels
                incomeLabel.setText("Income: ₹ " + income);
                expenseLabel.setText("Expense: ₹ " + expense);
                balanceLabel.setText("Balance: ₹ " + balance);

                // ✅ Updated Budget Logic (based on balance)
                double budget = budgetManager.getBudget();

                if (budget == 0) {
                    budgetLabel.setText("⚠ Budget Not Set");
                }
                else if (balance < 0) {
                    budgetLabel.setText("⚠ Budget Exceeded! Deficit: ₹ " + (-balance));
                }
                else {
                    budgetLabel.setText("✅ Budget Open | Balance: ₹ " + balance);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Invalid Input!");
            }
        });

        // Add Components
        panel.add(createLabel("Enter Month (1-12):"));
        panel.add(monthField);

        panel.add(createLabel("Enter Year:"));
        panel.add(yearField);

        panel.add(generateBtn);

        panel.add(incomeLabel);
        panel.add(expenseLabel);
        panel.add(balanceLabel);
        panel.add(budgetLabel);

        add(panel);
        setVisible(true);
    }

    // Styled Label
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return label;
    }
}