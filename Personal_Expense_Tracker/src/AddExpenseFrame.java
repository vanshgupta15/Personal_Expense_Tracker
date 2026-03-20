import java.awt.*;
import javax.swing.*;
public class AddExpenseFrame extends JFrame 
{
    public AddExpenseFrame(ExpenseManager manager) 
    {
        setTitle("Add Expense");
        setSize(400, 300);
        setLocationRelativeTo(null);
        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.setBackground(new Color(231, 76, 60)); 
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Fields
        JTextField titleField = new JTextField();
        JTextField amountField = new JTextField();
        String[] categories = {"Food", "Transport", "Shopping", "Bills", "Other"};
        JComboBox<String> categoryBox = new JComboBox<>(categories);
        JTextField monthField = new JTextField();
        JTextField yearField = new JTextField();
        JButton addButton = new JButton("Add Expense");
        addButton.setBackground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        // Labels (white text for contrast)
        JLabel l1 = createLabel("Title:");
        JLabel l2 = createLabel("Amount:");
        JLabel l3 = createLabel("Category:");
        JLabel l4 = createLabel("Month:");
        JLabel l5 = createLabel("Year:");
        // Add action
        addButton.addActionListener(e -> 
            {
            try {
                String title = titleField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String category = (String) categoryBox.getSelectedItem();
                int month = Integer.parseInt(monthField.getText());
                int year = Integer.parseInt(yearField.getText());

                if (title.isEmpty()) 
                {
                    JOptionPane.showMessageDialog(this, "⚠ Title cannot be empty");
                    return;
                }
                manager.addExpense(title, amount, category, month, year);
                JOptionPane.showMessageDialog(this, "Expense Added Successfully!");
                // Clear fields
                titleField.setText("");
                amountField.setText("");
                monthField.setText("");
                yearField.setText("");
            } 
            catch (Exception ex) 
            {
                JOptionPane.showMessageDialog(this, "❌ Invalid Input!");
            }
        });
        // Add components
        panel.add(l1); panel.add(titleField);
        panel.add(l2); panel.add(amountField);
        panel.add(l3); panel.add(categoryBox);
        panel.add(l4); panel.add(monthField);
        panel.add(l5); panel.add(yearField);
        panel.add(new JLabel("")); panel.add(addButton);
        add(panel);
        setVisible(true);
    }
    // Helper method for styled labels
    private JLabel createLabel(String text) 
    {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        return label;
    }
}