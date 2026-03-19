import java.awt.*;
import javax.swing.*;

public class MonthlySummaryFrame extends JFrame {

    public MonthlySummaryFrame(ExpenseManager manager, BudgetManager budgetManager) {

        setTitle("Monthly Summary");
        setSize(400, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));
        panel.setBackground(new Color(155, 89, 182)); // Purple Theme
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField monthField = new JTextField();
        JTextField yearField = new JTextField();

        JButton generateBtn = new JButton("Generate Summary");
        generateBtn.setBackground(Color.WHITE);

        JLabel incomeLabel = createLabel("Income: ");
        JLabel expenseLabel = createLabel("Expense: ");
        JLabel balanceLabel = createLabel("Balance: ");
        JLabel budgetLabel = createLabel("Budget Status: ");

        generateBtn.addActionListener(e -> {

            try {
                int month = Integer.parseInt(monthField.getText());
                int year = Integer.parseInt(yearField.getText());

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

                incomeLabel.setText("Income: ₹ " + income);
                expenseLabel.setText("Expense: ₹ " + expense);
                balanceLabel.setText("Balance: ₹ " + balance);

                // Budget check
                if (budgetManager.isBudgetExceeded(expense)) {
                    budgetLabel.setText("⚠ Budget Exceeded!");
                } else {
                    budgetLabel.setText("Within Budget");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Invalid Input");
            }
        });

        panel.add(createLabel("Enter Month:"));
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

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return label;
    }
}