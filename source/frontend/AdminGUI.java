package source.frontend;

import javax.swing.*;

public class AdminGUI {
    JFrame frame;
    JTextField textField;
    JButton[] menuButtons = new JButton[4]; // Add, Show, Edit, Delete
    JPanel panel;

    public AdminGUI () {
        frame = new JFrame("Admin Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setVisible(true);
        frame.setLayout(null);

        panel = new JPanel();
        

    }
}
