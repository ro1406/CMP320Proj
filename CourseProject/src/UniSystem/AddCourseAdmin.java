/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniSystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Prem Rajendran
 */
public class AddCourseAdmin extends javax.swing.JFrame {

    private myDBCon mycon ;
    
    Statement statement;
    PreparedStatement prepStatement;
    PreparedStatement prepStatement2;
    ResultSet rs;
    /**
     * Creates new form AddCourseAdmin
     */
    public AddCourseAdmin() {
        mycon = new myDBCon();
        initComponents();
        this.setLocationRelativeTo(null);
        CodeError.setVisible(false);
        NameError.setVisible(false);
        CreditsError.setVisible(false);
        try
        {
            rs = mycon.getstate().executeQuery("select course_name from courses order by course_name");
            while(rs.next())
            {
                cmbPreReq.addItem(rs.getString("course_name"));
            }
            rs.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    void clearInputBoxes() {
        txtCode.setText("");
        txtName.setText("");
        cmbPreReq.setSelectedIndex(0);
        txtCredits.setText("");
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
        CodeError.setText("");
        CodeError.setVisible(false);
        NameError.setText("");
        NameError.setVisible(false);
        CreditsError.setText("");
        CreditsError.setVisible(false);
    }

    boolean isValidData() {

        clearErrorLabels();
        boolean result = true;
        if (txtCode.getText().trim().isEmpty() || (txtCode.getText().trim().length() > 10)) {
            if (txtCode.getText().trim().isEmpty()) {
                CodeError.setText("Invalid. Cannot be empty.");
            } else if ((txtName.getText().trim().length() > 10)) {
                NameError.setText("Invalid. Must be < 10 chars.");
            }
            CodeError.setVisible(true);
            result = false;
        }

        if (txtName.getText().trim().isEmpty() || (txtName.getText().trim().length() > 50)) {
            if (txtName.getText().trim().isEmpty()) {
                NameError.setText("Invalid. Cannot be empty.");
            } else if ((txtName.getText().trim().length() > 50)) {
                NameError.setText("Invalid. Must be < 50 chars.");
            }
            NameError.setVisible(true);
            result = false;
        }

        if (txtCredits.getText().trim().isEmpty() || !(isInteger(txtCredits.getText().trim())) || (Integer.parseInt(txtCredits.getText().trim()) < 1) || (Integer.parseInt(txtCredits.getText().trim()) > 6) ) {
            if (txtCredits.getText().trim().isEmpty()) {
                CreditsError.setText("Invalid. Cannot be empty.");
            } else if (!(isInteger(txtCredits.getText().trim()))) {
                CreditsError.setText("Invalid. Must be an integer number.");
            } else if ((Integer.parseInt(txtCredits.getText().trim()) < 1) || (Integer.parseInt(txtCredits.getText().trim()) > 6)) {
                CreditsError.setText("Invalid. Must be between 1 and 6");
            }
            CreditsError.setVisible(true);
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

        cmbPreReq = new javax.swing.JComboBox<>();
        btnAddNewCourse = new javax.swing.JButton();
        Credits = new javax.swing.JLabel();
        CodeError = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        Name = new javax.swing.JLabel();
        PreRequisite = new javax.swing.JLabel();
        NameError = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        Title = new javax.swing.JLabel();
        CreditsError = new javax.swing.JLabel();
        Code = new javax.swing.JLabel();
        txtCredits = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmbPreReq.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnAddNewCourse.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAddNewCourse.setText("Add New");
        btnAddNewCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewCourseActionPerformed(evt);
            }
        });

        Credits.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Credits.setText("CREDITS:");

        CodeError.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        CodeError.setForeground(new java.awt.Color(255, 0, 0));
        CodeError.setText("error label");

        txtCode.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        Name.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Name.setText("NAME:");

        PreRequisite.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        PreRequisite.setText("PRE-REQUISITE:");

        NameError.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        NameError.setForeground(new java.awt.Color(255, 0, 0));
        NameError.setText("error label");

        txtName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        Title.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        Title.setText("Add New Course");

        CreditsError.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        CreditsError.setForeground(new java.awt.Color(255, 0, 0));
        CreditsError.setText("error label");

        Code.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Code.setText("CODE:");

        txtCredits.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCredits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCreditsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Name)
                            .addComponent(Code)
                            .addComponent(PreRequisite)
                            .addComponent(Credits))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CodeError, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtCredits, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbPreReq, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(NameError, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(CreditsError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(Title)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAddNewCourse)
                .addGap(211, 211, 211))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Title)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Code)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CodeError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Name)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NameError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPreReq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PreRequisite))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Credits)
                    .addComponent(txtCredits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreditsError))
                .addGap(18, 18, 18)
                .addComponent(btnAddNewCourse)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddNewCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewCourseActionPerformed
        // TODO add your handling code here:

        try {
            // make the result set scrolable forward/backward updatable

            if (isValidData()) {
                prepStatement = mycon.getCon().prepareStatement("INSERT INTO courses (course_code, course_name, credits) VALUES (?,?,?)");
                prepStatement.setString(1, txtCode.getText());
                prepStatement.setString(2, txtName.getText());
                prepStatement.setInt(3, Integer.parseInt(txtCredits.getText()));
                
                prepStatement2 = mycon.getCon().prepareStatement("INSERT INTO courses_prerequisites (course_code, prereq_code) values (?,?)");
                prepStatement2.setString(1, txtCode.getText());
                prepStatement2.setString(2, cmbPreReq.getSelectedItem().toString());
                
                int result1 = prepStatement.executeUpdate();
                int result2 = prepStatement2.executeUpdate();
                if (result1 > 0 && result2 > 0) {

                    javax.swing.JLabel label = new javax.swing.JLabel("New course added successfully.");
                    label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
                    JOptionPane.showMessageDialog(null, label, "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    clearInputBoxes();
                } else {
                    // check validation errors
                }
                prepStatement.close();
                prepStatement2.close();
            } else {
                javax.swing.JLabel label = new javax.swing.JLabel("Please fix validation errors...");
                label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
                JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding new student.");
        }
    }//GEN-LAST:event_btnAddNewCourseActionPerformed

    private void txtCreditsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCreditsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCreditsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Code;
    private javax.swing.JLabel CodeError;
    private javax.swing.JLabel Credits;
    private javax.swing.JLabel CreditsError;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel NameError;
    private javax.swing.JLabel PreRequisite;
    private javax.swing.JLabel Title;
    private javax.swing.JButton btnAddNewCourse;
    private javax.swing.JComboBox<String> cmbPreReq;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtCredits;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
