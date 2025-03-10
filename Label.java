package password;

import javax.swing.*;
import java.awt.*;

public class LabelPanel extends JPanel {
    JTextField lengthField;
    JTextArea passwordArea;

    public LabelPanel() {
        setLayout(new FlowLayout());

        JLabel lengthLabel = new JLabel("Enter password length: ");
        lengthField = new JTextField(10);
        passwordArea = new JTextArea(3, 20);
        passwordArea.setEditable(false);
        passwordArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        passwordArea.setBackground(Color.LIGHT_GRAY);

        add(lengthLabel);
        add(lengthField);
        add(new JScrollPane(passwordArea));
    }

    public JTextField getLengthField() {
        return lengthField;
    }

    public JTextArea getPasswordArea() {
        return passwordArea;
    }
}
