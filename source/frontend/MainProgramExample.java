import javax.swing.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainProgramExample{
    
    public static void main(String[] args) {
        new WelcomeGUI();
    }

    static class WelcomeGUI implements ActionListener {
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
    
            JPanel cardPanel = new JPanel(new CardLayout());

            AdminGUI adminGUI = new AdminGUI();            

            panel = new JPanel();
            panel.setLayout(null);
            panel.setBounds(747, 0, 453, 795);
    
            adminButton.setBounds(82, 265, buttonWidth, buttonHeight);
            panel.add(adminButton);
            
            userButton.setBounds(82, 357, buttonWidth, buttonHeight);
            panel.add(userButton);
                      
            panel.setBackground(Color.decode("#949494"));

            cardPanel.add(panel, "panel");
            cardPanel.add(adminGUI, "adminGUI");
            
            frame.add(cardPanel);

            adminButton.addActionListener(e -> {
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "adminGUI");
            });
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {

        }
}}
