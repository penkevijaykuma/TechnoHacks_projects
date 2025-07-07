import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class CalculatorApp extends JFrame implements ActionListener {

    private JTextField displayField;
    private String currentInput = "";
    private double result = 0;
    private String lastOperation = "";
    private boolean newNumber = true;

    public CalculatorApp() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Display field
        displayField = new JTextField("0");
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        add(displayField, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10)); // 5 rows, 4 columns, with gaps

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                         "C"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        // Add padding
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // If it's a number or a decimal
        if (command.matches("[0-9.]")) { // Java regex: [0-9.] matches digits or a literal dot
            if (newNumber) {
                currentInput = command;
                newNumber = false;
            } else {
                currentInput += command;
            }
            displayField.setText(currentInput);
        } else if (command.equals("C")) { // Clear button
            currentInput = "";
            result = 0;
            lastOperation = "";
            newNumber = true;
            displayField.setText("0");
        } else if (command.matches("[+\\-*/]")) { // If it's an operator (Java regex: [+\\-*/] matches +, -, *, /)
            if (!currentInput.isEmpty() && !newNumber) {
                calculate();
                lastOperation = command;
                newNumber = true;
            } else if (newNumber && !displayField.getText().equals("0")) {
                // If an operator is pressed after a calculation and before a new number
                lastOperation = command;
            }
        } else if (command.equals("=")) { // Equals button
            calculate();
            lastOperation = ""; // Reset operation after equals
            newNumber = true;
        }
    }

    private void calculate() {
        if (currentInput.isEmpty() && lastOperation.isEmpty()) {
            return; // No input or operation yet
        }

        try {
            double currentValue = Double.parseDouble(currentInput);

            if (lastOperation.isEmpty()) {
                result = currentValue;
            } else {
                switch (lastOperation) {
                    case "+":
                        result += currentValue;
                        break;
                    case "-":
                        result -= currentValue;
                        break;
                    case "*":
                        result *= currentValue;
                        break;
                    case "/":
                        if (currentValue == 0) {
                            displayField.setText("Error: Div by zero");
                            result = 0;
                            currentInput = "";
                            newNumber = true;
                            lastOperation = "";
                            return;
                        }
                        result /= currentValue;
                        break;
                }
            }
            // Format and remove trailing zeros (Java regex: \.?0*$)
            displayField.setText(String.format("%.8f", result).replaceAll("\\.?0*$", ""));
            currentInput = String.valueOf(result); // Set current input to result for chained operations
        } catch (NumberFormatException ex) {
            displayField.setText("Error");
            result = 0;
            currentInput = "";
            newNumber = true;
            lastOperation = "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculatorApp().setVisible(true);
        });
    }
}