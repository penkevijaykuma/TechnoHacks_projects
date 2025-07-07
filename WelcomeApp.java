import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeApp extends JFrame {
    private JLabel messageLabel;
    private JButton welcomeButton;

    public WelcomeApp() {
        // Set up the frame
        setTitle("Welcome Application");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create components
        messageLabel = new JLabel("Click the button to see a welcome message!");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeButton = new JButton("Show Welcome Message");

        // Set layout
        setLayout(new BorderLayout(10, 10));

        // Add components to the frame
        add(messageLabel, BorderLayout.CENTER);
        add(welcomeButton, BorderLayout.SOUTH);

        // Add padding
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add button click listener
        welcomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageLabel.setText("Welcome to Java GUI Programming!");
                messageLabel.setForeground(new Color(0, 102, 204)); // Change text color to blue
            }
        });
    }

    public static void main(String[] args) {
        // Run the application
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomeApp().setVisible(true);
            }
        });
    }
} 