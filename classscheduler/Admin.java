package classscheduler;
//import classscheduler.acp.AddSchedule; // subject to change
//import classscheduler.acp.RemoveSchedule; // subject to change
//import classscheduler.AddSchedule;
import javax.swing.JOptionPane;

public class Admin extends javax.swing.JFrame {

    //private AddSchedule addScheduleFrame; // Declare an instance of AddSchedule
    //private RemoveSchedule removeScheduleFrame; //Declare an instance of RemoveSchedule
    
    public Admin() {
        initComponents();
        //addScheduleFrame = new AddSchedule(); // Initialize the AddSchedule instance
        //removeScheduleFrame = new RemoveSchedule(); // Initialize the RemoveSchedule instance
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        addScheduler = new javax.swing.JButton();
        removeScheduler = new javax.swing.JButton();
        editScheduler = new javax.swing.JButton();
        viewScheduler = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Admin Control Panel");

        addScheduler.setText("ADD SCHEDULE");
        addScheduler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSchedulerActionPerformed(evt);
            }
        });

        removeScheduler.setText("REMOVE SCHEDULE");
        removeScheduler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSchedulerActionPerformed(evt);
            }
        });

        editScheduler.setText("EDIT SCHEDULE");
        editScheduler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSchedulerActionPerformed(evt);
            }
        });

        viewScheduler.setText("VIEW SCHEDULE");
        viewScheduler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewSchedulerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(88, 88, 88))
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeScheduler, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(addScheduler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editScheduler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(viewScheduler, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addComponent(addScheduler)
                .addGap(18, 18, 18)
                .addComponent(removeScheduler)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editScheduler)
                .addGap(18, 18, 18)
                .addComponent(viewScheduler)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void viewSchedulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewSchedulerActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This functionality will be implemented soon.", "User Functionality", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_viewSchedulerActionPerformed

    private void editSchedulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSchedulerActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "This functionality will be implemented soon.", "User Functionality", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_editSchedulerActionPerformed

    private void removeSchedulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSchedulerActionPerformed
        // TODO add your handling code here:
        //removeScheduleFrame.setVisible(true);
        
        // Create an instance of RemoveSchedule
    RemoveSchedule removeScheduleFrame = new RemoveSchedule();
    
    // Make the AddSchedule frame visible
    removeScheduleFrame.setVisible(true);
        
    }//GEN-LAST:event_removeSchedulerActionPerformed

    private void addSchedulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSchedulerActionPerformed
        // TODO add your handling code here:
        //addScheduleFrame.setVisible(true);
        
        // Create an instance of AddSchedule
    AddSchedule addScheduleFrame = new AddSchedule();
    
    // Make the AddSchedule frame visible
    addScheduleFrame.setVisible(true);
    
    }//GEN-LAST:event_addSchedulerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addScheduler;
    private javax.swing.JButton editScheduler;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton removeScheduler;
    private javax.swing.JButton viewScheduler;
    // End of variables declaration//GEN-END:variables
}