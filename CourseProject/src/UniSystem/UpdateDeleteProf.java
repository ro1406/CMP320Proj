/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniSystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class UpdateDeleteProf extends javax.swing.JFrame {

    private myDBCon mycon;
    ResultSet rs;
    PreparedStatement prepStatement;
    /**
     * Creates new form UpdateDeleteProf
     */
    public UpdateDeleteProf() {
        mycon = new myDBCon();
        initComponents();
        this.setLocationRelativeTo(null);
        IDError.setVisible(false);
        NameError.setVisible(false);
        AgeError.setVisible(false);
        cmbGender.addItem("Male");
        cmbGender.addItem("Female");
        cmbCollege.addItem("CAAD");
        cmbCollege.addItem("SBA");
        cmbCollege.addItem("CAS");
        cmbCollege.addItem("CEN");
        getNewData();
    }
    
    private void getNewData() {

        try {
            rs = mycon.getstate().executeQuery("SELECT * FROM professors ORDER BY pid ASC ");
            rs.beforeFirst();
            rs.first();
            populateFields();
        } catch (SQLException e) {
            javax.swing.JLabel label = new javax.swing.JLabel("SQL Error - Display selected professor ID.");
            label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
            JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void populateFields() {
        try {
            txtID.setText(rs.getString("pid"));
            txtName.setText(rs.getString("name"));
            cmbCollege.setSelectedItem(rs.getString("college"));
            if(rs.getString("sex").equals('M'))
                cmbGender.setSelectedItem("Male");
            else
                cmbGender.setSelectedItem("Female");
            txtAge.setText(rs.getString("age"));
            EnableDisableButtons();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateDeleteProf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void EnableDisableButtons() {
        try {
            if (rs.isFirst()) {
                btnPrevious.setEnabled(false);
            } else {
                btnPrevious.setEnabled(true);
            }
            if (rs.isLast()) {
                btnNext.setEnabled(false);
            } else {
                btnNext.setEnabled(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateDeleteProf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void MoveNext() {
        try {
            // TODO add your handling code here:

            if (!rs.isLast()) {

                rs.next();
                populateFields();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateDeleteProf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void MovePrevious() {
        try {
            // TODO add your handling code here:

            if (!rs.isFirst()) {
                rs.previous();
                populateFields();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateDeleteProf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isInteger(String s) {
        try {
                Integer.parseInt(s);
                return true;
        } catch (NumberFormatException ex) {
                return false;
        }
    }

    public boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    
    void clearErrorLabels() {
        IDError.setText("");
        IDError.setVisible(false);
        NameError.setText("");
        NameError.setVisible(false);
        AgeError.setText("");
        AgeError.setVisible(false);
    }
    
    boolean isValidData() {

        clearErrorLabels();
        boolean result = true;
        if (txtID.getText().trim().isEmpty() || !isInteger(txtID.getText().trim())) {
            if (txtID.getText().trim().isEmpty()) {
                IDError.setText("Invalid. Cannot be empty.");
            } else if (!isInteger(txtID.getText().trim())) {
                IDError.setText("Invalid. Must be integer.");
            }
            IDError.setVisible(true);
            result = false;
        }

        if (txtName.getText().trim().isEmpty() || (txtName.getText().trim().length() > 10)) {
            if (txtName.getText().trim().isEmpty()) {
                NameError.setText("Invalid. Cannot be empty.");
            } else if ((txtName.getText().trim().length() > 50)) {
                NameError.setText("Invalid. Must be < 50 chars.");
            }
            NameError.setVisible(true);
            result = false;
        }

        if (txtAge.getText().trim().isEmpty() || !(isInteger(txtAge.getText().trim()))) {
            if(txtAge.getText().trim().isEmpty())
                AgeError.setText("Invalid. Cannot be empty.");
            else if(!isInteger(txtAge.getText().trim()))
                AgeError.setText("Invalid. Must be an integer.");
            AgeError.setVisible(true);
            result = false;
        }
        
        return result;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbGender = new javax.swing.JComboBox<>();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        btnNext = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        IDError = new javax.swing.JLabel();
        UpdateDeleteProf = new javax.swing.JLabel();
        NameError = new javax.swing.JLabel();
        IDNo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        AgeError = new javax.swing.JLabel();
        College = new javax.swing.JLabel();
        Gender = new javax.swing.JLabel();
        Age = new javax.swing.JLabel();
        cmbCollege = new javax.swing.JComboBox<>();
        txtName = new javax.swing.JTextField();
        txtAge = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update/Delete Professor");

        cmbGender.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        txtID.setEditable(false);
        txtID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnNext.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnNext.setText("Next >>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrevious.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnPrevious.setText("<< Previous");
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        IDError.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        IDError.setForeground(new java.awt.Color(255, 0, 0));
        IDError.setText("error label");

        UpdateDeleteProf.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        UpdateDeleteProf.setText("Update/Delete Professor");

        NameError.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        NameError.setForeground(new java.awt.Color(255, 0, 0));
        NameError.setText("error label");

        IDNo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        IDNo.setText("ID NO:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("NAME:");

        AgeError.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        AgeError.setForeground(new java.awt.Color(255, 0, 0));
        AgeError.setText("error label");

        College.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        College.setText("COLLEGE:");

        Gender.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Gender.setText("GENDER:");

        Age.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Age.setText("AGE:");

        cmbCollege.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtAge.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnPrevious)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(btnNext))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UpdateDeleteProf)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(IDNo)
                                    .addComponent(College)
                                    .addComponent(Gender)
                                    .addComponent(Age))
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cmbGender, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbCollege, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtID, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtAge, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(IDError, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(NameError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(AgeError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(UpdateDeleteProf)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDNo)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IDError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NameError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(College)
                    .addComponent(cmbCollege, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Gender)
                    .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Age)
                    .addComponent(AgeError)
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnNext)
                    .addComponent(btnPrevious))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:

        try {
            // make the result set scrolable forward/backward updatable
            //            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            if (isValidData()) {
                prepStatement = mycon.getCon().prepareStatement("UPDATE professors name=?, sex=?, college=?, age=? where sid = ?");

                prepStatement.setString(1, txtName.getText());
                if(cmbCollege.getSelectedItem()=="Male")
                    prepStatement.setString(2,"M");
                else
                    prepStatement.setString(2,"F");
                prepStatement.setString(3, cmbCollege.getSelectedItem().toString());
                prepStatement.setString(4, txtAge.getText());
                prepStatement.setInt(5, Integer.parseInt(txtID.getText()));

                int result = prepStatement.executeUpdate();
                if (result > 0) {

                    javax.swing.JLabel label = new javax.swing.JLabel("Professor No " + txtID.getText() + " updated successfully.");
                    label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
                    JOptionPane.showMessageDialog(null, label, "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

                    getNewData();

                } else {
                    // check validation errors
                }
                prepStatement.close();
            } else {

                javax.swing.JLabel label = new javax.swing.JLabel("Please fix validation errors...");
                label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
                JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.ERROR_MESSAGE);

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error updating professor." + e.getMessage());

        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:

        try {
            // make the result set scrolable forward/backward updatable
            prepStatement = mycon.getCon().prepareStatement("DELETE professors WHERE pid = " + txtID.getText().trim());
            int result = prepStatement.executeUpdate();
            if (result > 0) {
                javax.swing.JLabel label = new javax.swing.JLabel("Prof No " + txtID.getText().trim() + " deleted successfully.");
                label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
                JOptionPane.showMessageDialog(null, label, "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                getNewData();
            }
            prepStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting professor.");

        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        MoveNext();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        // TODO add your handling code here:
        MovePrevious();
    }//GEN-LAST:event_btnPreviousActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Age;
    private javax.swing.JLabel AgeError;
    private javax.swing.JLabel College;
    private javax.swing.JLabel Gender;
    private javax.swing.JLabel IDError;
    private javax.swing.JLabel IDNo;
    private javax.swing.JLabel NameError;
    private javax.swing.JLabel UpdateDeleteProf;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbCollege;
    private javax.swing.JComboBox<String> cmbGender;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
