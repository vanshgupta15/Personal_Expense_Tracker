import java.awt.*;
import javax.swing.*;

public class AddIncomeFrame extends JFrame {

    public AddIncomeFrame(ExpenseManager manager) {

        setTitle("Add Income");
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.setBackground(new Color(46, 204, 113)); // Green Theme
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fields
        JTextField titleField = new JTextField();
        JTextField amountField = new JTextField();

        String[] categories = {"Salary", "Business", "Investment", "Other"};
        JComboBox<String> categoryBox = new JComboBox<>(categories);

        JTextField monthField = new JTextField();
        JTextField yearField = new JTextField();

        JButton addButton = new JButton("Add Income");
        addButton.setBackground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Labels
        JLabel l1 = createLabel("Title:");
        JLabel l2 = createLabel("Amount:");
        JLabel l3 = createLabel("Category:");
        JLabel l4 = createLabel("Month:");
        JLabel l5 = createLabel("Year:");

        // Action
        addButton.addActionListener(e -> {
            try {
                String title = titleField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String category = (String) categoryBox.getSelectedItem();
                int month = Integer.parseInt(monthField.getText());
                int year = Integer.parseInt(yearField.getText());

                if (title.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "⚠ Title cannot be empty");
                    return;
                }

                if (month < 1 || month > 12) {
                    JOptionPane.showMessageDialog(this, "⚠ Enter valid month (1-12)");
                    return;
                }

                manager.addIncome(title, amount, category, month, year);

                JOptionPane.showMessageDialog(this, "✅ Income Added Successfully!");

                // Clear fields
                titleField.setText("");
                amountField.setText("");
                monthField.setText("");
                yearField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Invalid Input!");
            }
        });

        // Add Components
        panel.add(l1); panel.add(titleField);
        panel.add(l2); panel.add(amountField);
        panel.add(l3); panel.add(categoryBox);
        panel.add(l4); panel.add(monthField);
        panel.add(l5); panel.add(yearField);
        panel.add(new JLabel("")); panel.add(addButton);

        add(panel);
        setVisible(true);
    }

    // Styled Label
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        return label;
    }
}