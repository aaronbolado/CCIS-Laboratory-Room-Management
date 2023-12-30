package source.frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UserGUI implements ActionListener{

    public UserGUI () {
        JFrame frame = new JFrame();
        JLabel titleLabel = new JLabel("Laboratory Room Management");
        JButton button = new JButton("Change");

        // Manipulating label attributes
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setForeground(Color.decode("#00FF00"));
        titleLabel.setFont(new Font("Sans-serif", Font.BOLD, 20));



        // Displaying on the frame        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.decode("#1F1F1F"));
        frame.add(titleLabel);
        button.addActionListener(this);
        frame.add(button);
    }  
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String buttonText = source.getText();

        if (buttonText.equals("Change")) {
            new AdminGUI();
        }
    }
}
