import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Theme {
    // Colors
    public static final Color BACKGROUND = new Color(18, 18, 18);
    public static final Color CARD_BACKGROUND = new Color(30, 30, 30);
    public static final Color TEXT_PRIMARY = new Color(255, 255, 255);
    public static final Color TEXT_SECONDARY = new Color(179, 179, 179);
    
    public static final Color INCOME_COLOR = new Color(46, 204, 113);
    public static final Color EXPENSE_COLOR = new Color(231, 76, 60);
    public static final Color PRIMARY_COLOR = new Color(58, 123, 213);
    public static final Color SECONDARY_COLOR = new Color(155, 89, 182);
    public static final Color WARNING_COLOR = new Color(241, 196, 15);
    
    // Fonts
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 24);
    public static final Font FONT_HEADER = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font FONT_REGULAR = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_BOLD = new Font("Segoe UI", Font.BOLD, 14);

    public static void applyBaseTheme(JFrame frame) {
        frame.getContentPane().setBackground(BACKGROUND);
    }

    public static JLabel createLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    public static JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(bgColor.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(bgColor.brighter());
                } else {
                    g2.setColor(bgColor);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFont(FONT_BOLD);
        btn.setForeground(TEXT_PRIMARY);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));
        return btn;
    }

    public static JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(FONT_REGULAR);
        field.setBackground(new Color(45, 45, 45));
        field.setForeground(TEXT_PRIMARY);
        field.setCaretColor(TEXT_PRIMARY);
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(70, 70, 70), 1, true),
                new EmptyBorder(8, 10, 8, 10)
        ));
        return field;
    }

    public static JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> box = new JComboBox<>(items);
        box.setFont(FONT_REGULAR);
        box.setBackground(new Color(45, 45, 45));
        box.setForeground(TEXT_PRIMARY);
        // Note: Default combo box dropdown styling might still look native, 
        // but customizing the main display helps blend with the dark theme.
        return box;
    }

    public static JPanel createCardPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(CARD_BACKGROUND);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(50, 50, 50), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        return panel;
    }
}
