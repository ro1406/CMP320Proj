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
 * @author Prem Rajendran
 */
public class UpdateDeleteSection extends javax.swing.JFrame {

    private myDBCon mycon;
    ResultSet rs;
    PreparedStatement prepStatement;
    /**
     * Creates new form UpdateDeleteSection
     */
    public UpdateDeleteSection() {
        mycon = new myDBCon();
        initComponents();
        this.setLocationRelativeTo(null);
        CRNError.setVisible(false);
        SemError.setVisible(false);
        LocError.setVisible(false);
        TimeError.setVisible(false);
        try
        {
            rs = mycon.getstate().executeQuery("Select course_code from courses order by course_code");
            while(rs.next())
            {
                cmbCode.addItem(rs.getString("course_code"));
            }
            
            rs = mycon.getstate().executeQuery("Select pid from professors order by pid");
            while(rs.next())
            {
                cmbProfID.addItem(rs.getString("pid"));
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    private void getNewData() {

        try {
            rs = mycon.getstate().executeQuery("SELECT * FROM courses_sections ORDER BY crn ASC ");
            rs.beforeFirst();
            rs.first();
            populateFields();
        } catch (SQLException e) {
            javax.swing.JLabel label = new javax.swing.JLabel("SQL Error - Display selected section CRN.");
            label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
            JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void populateFields() {
        try {
            txtCRN.setText(rs.getString("CRN"));
            cmbCode.setSelectedItem(rs.getString("course_code"));
            cmbProfID.setSelectedItem(rs.getString("pid"));
            txtSem.setText(rs.getString("semester"));
            txtLoc.setText(rs.getString("location"));
            txtTime.setText(rs.getString("time"));
            
            EnableDisableButtons();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateDeleteSection.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateDeleteSection.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateDeleteSection.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateDeleteSection.class.getName()).log(Level.SEVERE, null, ex);
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
        CRNError.setText("");
        CRNError.setVisible(false);
        SemError.setText("");
        SemError.setVisible(false);
        LocError.setText("");
        LocError.setVisible(false);
        TimeError.setText("");
        TimeError.setVisible(false);
    }
    
    boolean isValidData() {

        clearErrorLabels();
        boolean result = true;
        if (txtCRN.getText().trim().isEmpty() || !isInteger(txtCRN.getText().trim())) {
            if (txtCRN.getText().trim().isEmpty()) {
                CRNError.setText("Invalid. Cannot be empty.");
            } else if (!isInteger(txtCRN.getText().trim())) {
                CRNError.setText("Invalid. Must be integer.");
            }
            CRNError.setVisible(true);
            result = false;
        }

        if (txtSem.getText().trim().isEmpty() || (txtSem.getText().trim().length() > 25)) {
            if(txtSem.getText().trim().isEmpty())
                SemError.setText("Invalid. Cannot be empty.");
            else if(txtSem.getText().trim().length() > 25)
                SemError.setText("Invalid. Must be < 25 chars.");
            SemError.setVisible(true);
            result = false;
        }
        
        if (txtLoc.getText().trim().isEmpty() || (txtLoc.getText().trim().length() > 50)) {
            if(txtLoc.getText().trim().isEmpty())
                LocError.setText("Invalid. Cannot be empty.");
            else if(txtLoc.getText().trim().length() > 50)
                LocError.setText("Invalid. Must be < 50 chars.");
            LocError.setVisible(true);
            result = false;
        }
        
        if (txtTime.getText().trim().isEmpty() || (txtTime.getText().trim().length() > 50)) {
            if(txtTime.getText().trim().isEmpty())
                TimeError.setText("Invalid. Cannot be empty.");
            else if(txtTime.getText().trim().length() > 50)
                TimeError.setText("Invalid. Must be < 50 chars.");
            TimeError.setVisible(true);
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

        cmbProfID = new javax.swing.JComboBox<>();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtSem = new javax.swing.JFormattedTextField();
        txtCRN = new javax.swing.JTextField();
        btnNext = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        CRNError = new javax.swing.JLabel();
        UpdateDeleteStudent = new javax.swing.JLabel();
        CRNNo = new javax.swing.JLabel();
        SemError = new javax.swing.JLabel();
        LocError = new javax.swing.JLabel();
        Code = new javax.swing.JLabel();
        TimeError = new javax.swing.JLabel();
        ProfID = new javax.swing.JLabel();
        Sem = new javax.swing.JLabel();
        Loc = new javax.swing.JLabel();
        Time = new javax.swing.JLabel();
        cmbCode = new javax.swing.JComboBox<>();
        txtLoc = new javax.swing.JTextField();
        txtTime = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cmbProfID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

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

        txtSem.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd-MMM-yyyy"))));
        txtSem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtCRN.setEditable(false);
        txtCRN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

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

        CRNError.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        CRNError.setForeground(new java.awt.Color(255, 0, 0));
        CRNError.setText("error label");

        UpdateDeleteStudent.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        UpdateDeleteStudent.setText("Update/Delete Section");

        CRNNo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        CRNNo.setText("CRN NO:");

        SemError.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        SemError.setForeground(new java.awt.Color(255, 0, 0));
        SemError.setText("error label");

        LocError.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        LocError.setForeground(new java.awt.Color(255, 0, 0));
        LocError.setText("error label");

        Code.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Code.setText("COURSE CODE:");

        TimeError.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        TimeError.setForeground(new java.awt.Color(255, 0, 0));
        TimeError.setText("error label");

        ProfID.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ProfID.setText("PROFESSOR ID:");

        Sem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Sem.setText("SEMESTER:");

        Loc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Loc.setText("LOCATION:");

        Time.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Time.setText("TIME:");

        cmbCode.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtLoc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtTime.setEditable(false);
        txtTime.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(CRNNo)
                                    .addComponent(Code)
                                    .addComponent(ProfID)
                                    .addComponent(Sem)
                                    .addComponent(Loc)
                                    .addComponent(Time)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnPrevious)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNext))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cmbProfID, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbCode, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCRN, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSem, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtLoc, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CRNError, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(SemError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(LocError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TimeError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(UpdateDeleteStudent)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(UpdateDeleteStudent)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CRNNo)
                    .addComponent(txtCRN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CRNError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Code)
                    .addComponent(cmbCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProfID)
                    .addComponent(cmbProfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sem)
                    .addComponent(txtSem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SemError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Loc)
                    .addComponent(txtLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LocError))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Time)
                    .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TimeError))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnNext)
                    .addComponent(btnPrevious))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:

        try {
            // make the result set scrolable forward/backward updatable
            //            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            if (isValidData()) {
                prepStatement = mycon.getCon().prepareStatement("UPDATE courses_sections course_code=?, pid=?, semester=?, location=?, time=? where crn = ?");

                prepStatement.setString(1, cmbCode.getSelectedItem().toString());
                prepStatement.setString(2, cmbProfID.getSelectedItem().toString());
                prepStatement.setString(3, txtSem.getText());
                prepStatement.setString(4, txtLoc.getText());
                prepStatement.setString(5, txtTime.getText());
                prepStatement.setInt(6, Integer.parseInt(txtCRN.getText()));

                int result = prepStatement.executeUpdate();
                if (result > 0) {

                    javax.swing.JLabel label = new javax.swing.JLabel("Section No " + txtCRN.getText() + " updated successfully.");
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

            JOptionPane.showMessageDialog(null, "Error updating section." + e.getMessage());

        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:

        try {
            // make the result set scrolable forward/backward updatable
            prepStatement = mycon.getCon().prepareStatement("DELETE courses_sections WHERE crn = " + txtCRN.getText().trim());
            int result = prepStatement.executeUpdate();
            if (result > 0) {
                javax.swing.JLabel label = new javax.swing.JLabel("Section No " + txtCRN.getText().trim() + " deleted successfully.");
                label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
                JOptionPane.showMessageDialog(null, label, "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                getNewData();
            }
            prepStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting section.");

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
    private javax.swing.JLabel CRNError;
    private javax.swing.JLabel CRNNo;
    private javax.swing.JLabel Code;
    private javax.swing.JLabel Loc;
    private javax.swing.JLabel LocError;
    private javax.swing.JLabel ProfID;
    private javax.swing.JLabel Sem;
    private javax.swing.JLabel SemError;
    private javax.swing.JLabel Time;
    private javax.swing.JLabel TimeError;
    private javax.swing.JLabel UpdateDeleteStudent;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbCode;
    private javax.swing.JComboBox<String> cmbProfID;
    private javax.swing.JTextField txtCRN;
    private javax.swing.JTextField txtLoc;
    private javax.swing.JFormattedTextField txtSem;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
