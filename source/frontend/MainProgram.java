package source.frontend;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainProgram {
    public static void main(String[] args) {
    }
}

class WelcomeGUI implements ActionListener {
    JButton adminButton = new JButton("Admin");
    JButton userButton = new JButton("User");
    JFrame frame;
    JPanel panel;

    // Reusable variables
    final int buttonWidth = 287;
    final int buttonHeight = 57;

    WelcomeGUI () {
        frame = new JFrame("Laboratory Room Management Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setVisible(true);
        frame.setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(747, 0, 453, 795);

        adminButton.setBounds(82, 265, buttonWidth, buttonHeight);
        adminButton.addActionListener(this);
        panel.add(adminButton);
        
        userButton.setBounds(82, 357, buttonWidth, buttonHeight);
        userButton.addActionListener(this);
        panel.add(userButton);
        
        panel.setBackground(Color.decode("#949494"));

        frame.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String buttonText = source.getText();

        if (buttonText.equals("Admin")) {
            new AdminGUI();
        }
        else if (buttonText.equals("User")){
            new UserGUI();
        }
    }
}