import java.awt.*;
import javax.swing.*;

public class AddIncomeFrame extends JFrame {
    public AddIncomeFrame(ExpenseManager manager) {
        setTitle("Add Income");
        setSize(450, 400);
        setLocationRelativeTo(null);
        Theme.applyBaseTheme(this);

        // Main Container
        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        mainPanel.setBackground(Theme.BACKGROUND);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Header Label
        JLabel headerLabel = Theme.createLabel("➕ Record Income", Theme.FONT_TITLE, Theme.INCOME_COLOR);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Form Panel (Card styling)
        JPanel formPanel = Theme.createCardPanel();
        formPanel.setLayout(new GridLayout(5, 2, 15, 15));

        // Fields
        JTextField titleField = Theme.createTextField();
        JTextField amountField = Theme.createTextField();
        String[] categories = {"Salary", "Business", "Investment", "Other"};
        JComboBox<String> categoryBox = Theme.createComboBox(categories);
        JTextField monthField = Theme.createTextField();
        JTextField yearField = Theme.createTextField();

        // Labels
        formPanel.add(Theme.createLabel("Title:", Theme.FONT_BOLD, Theme.TEXT_PRIMARY));
        formPanel.add(titleField);
        formPanel.add(Theme.createLabel("Amount (₹):", Theme.FONT_BOLD, Theme.TEXT_PRIMARY));
        formPanel.add(amountField);
        formPanel.add(Theme.createLabel("Category:", Theme.FONT_BOLD, Theme.TEXT_PRIMARY));
        formPanel.add(categoryBox);
        formPanel.add(Theme.createLabel("Month (1-12):", Theme.FONT_BOLD, Theme.TEXT_PRIMARY));
        formPanel.add(monthField);
        formPanel.add(Theme.createLabel("Year:", Theme.FONT_BOLD, Theme.TEXT_PRIMARY));
        formPanel.add(yearField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Button
        JButton addButton = Theme.createButton("Add Income", Theme.INCOME_COLOR);
        
        // Action
        addButton.addActionListener(e -> {
            try {
                String title = titleField.getText().trim();
                if (title.isEmpty()) {
                    showError("Title cannot be empty");
                    return;
                }
                double amount = Double.parseDouble(amountField.getText().trim());
                String category = (String) categoryBox.getSelectedItem();
                int month = Integer.parseInt(monthField.getText().trim());
                int year = Integer.parseInt(yearField.getText().trim());

                if (month < 1 || month > 12) {
                    showError("Enter a valid month (1-12)");
                    return;
                }
                
                manager.addIncome(title, amount, category, month, year);
                
                JOptionPane.showMessageDialog(this, "✅ Income Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close form after success instead of just clearing
                
            } catch (NumberFormatException ex) {
                showError("Invalid Number Format! Please check Amounts and Dates.");
            } catch (Exception ex) {
                showError("An error occurred: " + ex.getMessage());
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Theme.BACKGROUND);
        buttonPanel.add(addButton);
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