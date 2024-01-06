import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminGUI extends JPanel implements ActionListener {

    JTextField textField;
    JButton addButton = new JButton("Add Schedule");
    JButton showButton = new JButton("Show Schedule");
    JButton deleteButton = new JButton("Delete Schedule");
    JButton editButton = new JButton("Edit Schedule");
    JPanel panel;

    public AdminGUI () {
        panel = new JPanel();
        panel.setSize(1200, 800);
        panel.setVisible(true);
        panel.setLayout(null);

        addButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(panel, "ERROR: " + "message", "Calculation Error", JOptionPane.ERROR_MESSAGE);
        });

        panel.add(addButton);
        panel.add(showButton);
        panel.add(deleteButton);
        panel.add(editButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
