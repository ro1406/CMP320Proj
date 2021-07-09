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
public class UpdateDeleteStudent extends javax.swing.JFrame {

    private myDBCon mycon;
    ResultSet rs;
    PreparedStatement prepStatement;
    /**
     * Creates new form UpdateDeleteStudent
     */
    public UpdateDeleteStudent() {
        mycon = new myDBCon();
        initComponents();
        this.setLocationRelativeTo(null);
        IDError.setVisible(false);
        NameError.setVisible(false);
        AdmitTermError.setVisible(false);
        CreditsError.setVisible(false);
        StandingError.setVisible(false);
        GPAError.setVisible(false);
        cmbMajor.addItem("CAAD");
        cmbMajor.addItem("SBA");
        cmbMajor.addItem("CAS");
        cmbMajor.addItem("CEN");
        cmbGender.addItem("Male");
        cmbGender.addItem("Female");
        getNewData();
    }
    
    private void getNewData() {

        try {
            String str;
            rs = mycon.getstate().executeQuery("SELECT * FROM STUDENTS ORDER BY sid ASC ");
            rs.beforeFirst();
            rs.first();
            populateFields();
        } catch (SQLException e) {
            javax.swing.JLabel label = new javax.swing.JLabel("SQL Error - Display selected empno.");
            label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
            JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void populateFields() {
        try {
            txtID.setText(rs.getString("sid"));
            txtName.setText(rs.getString("name"));
            cmbMajor.setSelectedItem(rs.getString("major"));
            if(rs.getString("sex").equals('M'))
                cmbGender.setSelectedItem("Male");
            else
                cmbGender.setSelectedItem("Female");
            txtAdmitTerm.setText(rs.getString("start_sem"));
            txtCredits.setText(rs.getString("credits"));
            txtStanding.setText(rs.getString("standing"));
            txtGPA.setText(rs.getString("GPA"));
            EnableDisableButtons();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateDeleteStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void findStanding()
    {
        if((Integer.parseInt(txtCredits.getText()) >= 0) && (Integer.parseInt(txtCredits.getText()) < 30))
        {
            txtStanding.setText("Freshman");
        }
        else if((Integer.parseInt(txtCredits.getText()) >= 30) && (Integer.parseInt(txtCredits.getText()) < 60))
        {
            txtStanding.setText("Sophomore");
        }
        else if((Integer.parseInt(txtCredits.getText()) >= 60) && (Integer.parseInt(txtCredits.getText()) < 90))
        {
            txtStanding.setText("Junior");
        }
        else
        {
            txtStanding.setText("Senior");
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
            Logger.getLogger(UpdateDeleteStudent.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateDeleteStudent.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateDeleteStudent.class.getName()).log(Level.SEVERE, null, ex);
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
        AdmitTermError.setText("");
        AdmitTermError.setVisible(false);
        CreditsError.setText("");
        CreditsError.setVisible(false);
        StandingError.setText("");
        StandingError.setVisible(false);
        GPAError.setText("");
        GPAError.setVisible(false);
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

        if (txtAdmitTerm.getText().trim().isEmpty() || (txtAdmitTerm.getText().trim().length() > 25)) {
            if(txtAdmitTerm.getText().trim().isEmpty())
                AdmitTermError.setText("Invalid. Cannot be empty.");
            else if(txtAdmitTerm.getText().trim().length() > 25)
                AdmitTermError.setText("Invalid. Must be < 25 chars.");
            AdmitTermError.setVisible(true);
            result = false;
        }

        if (txtCredits.getText().trim().isEmpty() || !(isInteger(txtCredits.getText().trim())) || (Integer.parseInt(txtCredits.getText().trim()) < 0) ) {
            if (txtCredits.getText().trim().isEmpty()) {
                CreditsError.setText("Invalid. Cannot be empty.");
            } else if (!(isInteger(txtCredits.getText().trim()))) {
                CreditsError.setText("Invalid. Must be an integer number.");
            } else if (Integer.parseInt(txtCredits.getText().trim()) < 0) {
                CreditsError.setText("Invalid. Must be a positive integer number");
            }
            CreditsError.setVisible(true);
            result = false;
        }

        if (txtStanding.getText().trim().isEmpty() || (txtStanding.getText().trim().length() > 10) ) {
            if(txtAdmitTerm.getText().trim().isEmpty())
                StandingError.setText("Invalid. Cannot be empty.");
            else if(txtAdmitTerm.getText().trim().length() > 25)
                StandingError.setText("Invalid. Must be < 10 chars.");
            StandingError.setVisible(true);
            result = false;
        }

        if(txtGPA.getText().trim().isEmpty() || !(isDouble(txtGPA.getText().trim()))) {
            if (txtGPA.getText().trim().isEmpty()) {
                GPAError.setText("Invalid. Cannot be empty.");
            } else if (!(isDouble(txtGPA.getText().trim()))) {
                GPAError.setText("Invalid. Must be floating point number.");
            }
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

        txtComm1 = new javax.swing.JTextField();
        CreditsError = new javax.swing.JLabel();
        StandingError = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtCredits = new javax.swing.JTextField();
        txtStanding = new javax.swing.JTextField();
        cmbGender = new javax.swing.JComboBox<>();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtAdmitTerm = new javax.swing.JFormattedTextField();
        txtID = new javax.swing.JTextField();
        btnNext = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        IDError = new javax.swing.JLabel();
        UpdateDeleteStudent = new javax.swing.JLabel();
        NameError = new javax.swing.JLabel();
        IDNo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        AdmitTermError = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        GPAError = new javax.swing.JLabel();
        txtGPA = new javax.swing.JTextField();
        cmbMajor = new javax.swing.JComboBox<>();

        txtComm1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Update/Delete Student");

        CreditsError.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        CreditsError.setForeground(new java.awt.Color(255, 0, 0));
        CreditsError.setText("error label");

        StandingError.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        StandingError.setForeground(new java.awt.Color(255, 0, 0));
        StandingError.setText("error label");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("ADMIT TERM:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("CREDITS:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("STANDING:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("GPA:");

        txtName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtCredits.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtStanding.setEditable(false);
        txtStanding.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

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

        txtAdmitTerm.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd-MMM-yyyy"))));
        txtAdmitTerm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

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

        UpdateDeleteStudent.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        UpdateDeleteStudent.setText("Update/Delete Student");

        NameError.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        NameError.setForeground(new java.awt.Color(255, 0, 0));
        NameError.setText("error label");

        IDNo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        IDNo.setText("ID NO:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("NAME:");

        AdmitTermError.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        AdmitTermError.setForeground(new java.awt.Color(255, 0, 0));
        AdmitTermError.setText("error label");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("MAJOR:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("GENDER:");

        GPAError.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        GPAError.setForeground(new java.awt.Color(255, 0, 0));
        GPAError.setText("error label");

        txtGPA.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        cmbMajor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

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
                                    .addComponent(jLabel3)
                                    .addComponent(IDNo)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)))
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
                                    .addComponent(cmbGender, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbMajor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtID, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAdmitTerm, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtStanding, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtCredits, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGPA, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(IDError, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(NameError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(AdmitTermError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CreditsError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(StandingError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(GPAError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(UpdateDeleteStudent)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(UpdateDeleteStudent)
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
                    .addComponent(jLabel4)
                    .addComponent(cmbMajor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAdmitTerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AdmitTermError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCredits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreditsError))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtStanding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StandingError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(GPAError)
                    .addComponent(txtGPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnNext)
                    .addComponent(btnPrevious))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:

        try {
            // make the result set scrolable forward/backward updatable
            //            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            if (isValidData()) {
                findStanding();
                prepStatement = mycon.getCon().prepareStatement("UPDATE students name=?, sex=?, start_sem=?, major=?, credits=?, standing=?, gpa=? where sid = ?");
                
                prepStatement.setString(1, txtName.getText());
                if(cmbMajor.getSelectedItem()=="Male")
                    prepStatement.setString(2,"M");
                else
                    prepStatement.setString(2,"F");
                prepStatement.setString(3, txtAdmitTerm.getText());
                prepStatement.setString(4, cmbMajor.getSelectedItem().toString());
                prepStatement.setInt(5, Integer.parseInt(txtCredits.getText()));
                prepStatement.setString(6, txtStanding.getText());
                prepStatement.setDouble(7, Double.parseDouble(txtGPA.getText()));
                prepStatement.setInt(8, Integer.parseInt(txtID.getText()));
                
                int result = prepStatement.executeUpdate();
                if (result > 0) {

                    javax.swing.JLabel label = new javax.swing.JLabel("Student No " + txtID.getText() + " updated successfully.");
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

            JOptionPane.showMessageDialog(null, "Error updating employee." + e.getMessage());

        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:

        try {
            // make the result set scrolable forward/backward updatable
            prepStatement = mycon.getCon().prepareStatement("DELETE student WHERE sid = " + txtID.getText().trim());
            int result = prepStatement.executeUpdate();
            if (result > 0) {
                javax.swing.JLabel label = new javax.swing.JLabel("Student No " + txtID.getText().trim() + " deleted successfully.");
                label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
                JOptionPane.showMessageDialog(null, label, "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                getNewData();
            }
            prepStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding new student.");

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
    private javax.swing.JLabel AdmitTermError;
    private javax.swing.JLabel CreditsError;
    private javax.swing.JLabel GPAError;
    private javax.swing.JLabel IDError;
    private javax.swing.JLabel IDNo;
    private javax.swing.JLabel NameError;
    private javax.swing.JLabel StandingError;
    private javax.swing.JLabel UpdateDeleteStudent;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbGender;
    private javax.swing.JComboBox<String> cmbMajor;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JFormattedTextField txtAdmitTerm;
    private javax.swing.JTextField txtComm1;
    private javax.swing.JTextField txtCredits;
    private javax.swing.JTextField txtGPA;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtStanding;
    // End of variables declaration//GEN-END:variables
}
