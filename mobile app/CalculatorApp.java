import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdvancedCalculatorApp extends JFrame {
    private JTextField textField;

    public AdvancedCalculatorApp() {
        // Set up the frame
        setTitle("Advanced Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 500));
        setLayout(new BorderLayout());

        // Create the text field for input and display
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        add(textField, BorderLayout.NORTH);

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        // Define the button labels
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "√", "x^2", "1/x", "C"
        };

        // Add buttons to the panel
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        // Pack and display the frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();

            if (buttonText.equals("=")) {
                // Calculate and display result
                try {
                    String expression = textField.getText();
                    double result = evaluateExpression(expression);
                    textField.setText(Double.toString(result));
                } catch (ArithmeticException ex) {
                    textField.setText("Error");
                }
            } else if (buttonText.equals("C")) {
                // Clear the input
                textField.setText("");
            } else {
                // Append button text to the input
                textField.setText(textField.getText() + buttonText);
            }
        }

        private double evaluateExpression(String expression) {
            // Use a ScriptEngineManager to evaluate the expression
            // Note: This is a simple example and does not handle all possible cases
            // For a more robust solution, consider using a library like Apache Commons Math
            javax.script.ScriptEngineManager mgr = new javax.script.ScriptEngineManager();
            javax.script.ScriptEngine engine = mgr.getEngineByName("JavaScript");

            try {
                return (double) engine.eval(expression);
            } catch (javax.script.ScriptException e) {
                throw new ArithmeticException("Invalid expression");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdvancedCalculatorApp());
    }
}
