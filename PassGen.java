import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.util.Random;

public class PassGen {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Password Generator");
        frame.setSize(420, 350);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.decode("#FEF9E1")); // Light background
        frame.setLocationRelativeTo(null);

        JLabel lengthLabel = new JLabel("Length:");
        lengthLabel.setBounds(30, 20, 70, 30);
        frame.add(lengthLabel);

        JTextField lengthField = new JTextField();
        lengthField.setBounds(100, 20, 200, 30);
        frame.add(lengthField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 60, 70, 30);
        frame.add(passwordLabel);

        JTextField passwordField = new JTextField();
        passwordField.setBounds(100, 60, 200, 30);
        passwordField.setEditable(false);
        frame.add(passwordField);

        Color purple = new Color(128, 0, 128);
        Color selectedColor = Color.decode("#EFEFEF");

        JToggleButton uppercaseButton = createStyledToggle("Uppercase", purple, selectedColor);
        uppercaseButton.setBounds(50, 100, 100, 30);
        frame.add(uppercaseButton);

        JToggleButton lowercaseButton = createStyledToggle("Lowercase", purple, selectedColor);
        lowercaseButton.setBounds(160, 100, 100, 30);
        lowercaseButton.setSelected(true);
        lowercaseButton.setBackground(selectedColor);
        lowercaseButton.setForeground(Color.BLACK);
        frame.add(lowercaseButton);

        JToggleButton numbersButton = createStyledToggle("Numbers", purple, selectedColor);
        numbersButton.setBounds(270, 100, 100, 30);
        frame.add(numbersButton);

        JToggleButton specialCharsButton = createStyledToggle("Special Chars", purple, selectedColor);
        specialCharsButton.setBounds(50, 140, 150, 30);
        frame.add(specialCharsButton);

        JButton generateButton = new JButton("Generate");
        generateButton.setBounds(100, 190, 200, 30);
        generateButton.setBackground(Color.BLUE);
        generateButton.setForeground(Color.WHITE);
        frame.add(generateButton);

        JButton copyButton = new JButton("Copy");
        copyButton.setBounds(100, 230, 200, 30);
        copyButton.setBackground(Color.RED);
        copyButton.setForeground(Color.WHITE);
        frame.add(copyButton);

        generateButton.addActionListener((ActionEvent e) -> {
            try {
                int length = Integer.parseInt(lengthField.getText());
                if (length <= 0) {
                    JOptionPane.showMessageDialog(frame, "Length must be a positive integer.");
                    return;
                }

                String password = generatePassword(
                        length,
                        uppercaseButton.isSelected(),
                        lowercaseButton.isSelected(),
                        numbersButton.isSelected(),
                        specialCharsButton.isSelected()
                );

                passwordField.setText(password);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid length input.");
            }
        });

        copyButton.addActionListener((ActionEvent e) -> {
            String password = passwordField.getText();
            if (!password.isEmpty()) {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(password), null);
                JOptionPane.showMessageDialog(frame, "Password copied to clipboard!");
            } else {
                JOptionPane.showMessageDialog(frame, "No password to copy!");
            }
        });

        frame.setVisible(true);
    }

    private static JToggleButton createStyledToggle(String label, Color unselectedColor, Color selectedColor) {
        JToggleButton button = new JToggleButton(label);
        button.setBackground(unselectedColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setOpaque(true);

        button.addActionListener(e -> {
            if (button.isSelected()) {
                button.setBackground(selectedColor);
                button.setForeground(Color.BLACK);
            } else {
                button.setBackground(unselectedColor);
                button.setForeground(Color.WHITE);
            }
        });

        return button;
    }

    private static String generatePassword(int length, boolean useUppercase, boolean useLowercase,
                                           boolean useNumbers, boolean useSpecialChars) {
        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String numberChars = "0123456789";
        String specialChars = "!@#$%^&*()-_+=<>?/{}[]|";

        StringBuilder allowedChars = new StringBuilder();
        if (useUppercase) allowedChars.append(uppercaseChars);
        if (useLowercase) allowedChars.append(lowercaseChars);
        if (useNumbers) allowedChars.append(numberChars);
        if (useSpecialChars) allowedChars.append(specialChars);

        if (allowedChars.length() == 0) {
            return ""; // No character set selected
        }

        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(randomIndex));
        }

        return password.toString();
    }
}
