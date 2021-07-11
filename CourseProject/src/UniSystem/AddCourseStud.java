package UniSystem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author wissam
 */
public class AddCourseStud extends javax.swing.JFrame {

    /**
     * Creates new form AddEmployee
     */
    String DBURL = "jdbc:oracle:thin:@coeoracle.aus.edu:1521:orcl";
    String DBUSER = "b00061555";
    String DBPASS = "b00061555";

    Connection con;
    Statement statement;
    PreparedStatement prepStatement;
    ResultSet rs;
    private int currUser;
    public AddCourseStud(int id) {
        initComponents();
        this.setLocationRelativeTo(null);
        currUser=id;
        cmbCRN.setVisible(false);
        jLabel6.setVisible(false);
        cmbGrades.setVisible(false);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery("SELECT * FROM course");
            // populate Course combo box
            while (rs.next()) {
                cmbCourse.addItem(rs.getString("Code"));
            }
            
            rs.close();
            statement.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

    }

    private void calculateTotalGradeCredit() throws SQLException{
        rs = statement.executeQuery("select sum(c.credits) sumc" +
                                    " from students_grades sg, courses c, courses_sections cs" +
                                    " where c.course_code = cs.course_code and cs.crn = sg.crn and sg.sid = " + currUser);
        rs.beforeFirst();
        rs.next();
        int totalCredits = rs.getInt("sumc");

        rs = statement.executeQuery("select sg.grade, c.credits" +
                                    " from students_grades sg, courses c, courses_sections cs" +
                                    " where c.course_code = cs.course_code and cs.crn = sg.crn and sg.sid = " + currUser);
        double totalGrade = 0.0;
        rs.beforeFirst();
        while(rs.next()){
            totalGrade += rs.getDouble("grade") * (rs.getInt("credits")/totalCredits);
        }
        totalGrade = (totalGrade/100)*4;

        String currentStanding;
        if (totalCredits > 12) currentStanding = "Senior";
        else if (totalCredits > 12) currentStanding = "Junior";
        else if (totalCredits > 12) currentStanding = "Sophomore";
        else currentStanding = "Freshman";

        rs = statement.executeQuery("update students set gpa = " + totalGrade +
                                    ", credits = " + totalCredits +
                                    ", standing = " + currentStanding +
                                    " where sid = " + currUser);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbCourse = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        lblEmpnoError = new javax.swing.JLabel();
        lblEnameError = new javax.swing.JLabel();
        cmbCRN = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        ConfirmCourse = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cmbGrades = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New Employee");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Add Course Section");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Course:");

        cmbCourse.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lblEmpnoError.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        lblEmpnoError.setForeground(new java.awt.Color(255, 0, 0));

        lblEnameError.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        lblEnameError.setForeground(new java.awt.Color(255, 0, 0));

        cmbCRN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("CRN:");

        ConfirmCourse.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ConfirmCourse.setText("Confirm Course");
        ConfirmCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmCourseActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Grade:");

        cmbGrades.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(204, 204, 204)
                                    .addComponent(lblEnameError, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(104, 104, 104)
                                    .addComponent(lblEmpnoError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(cmbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(ConfirmCourse)))
                            .addComponent(cmbCRN, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbGrades, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(btnAdd)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblEmpnoError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblEnameError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConfirmCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbCRN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbGrades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnAdd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:

        try {
            // make the result set scrolable forward/backward updatable
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            
            prepStatement = con.prepareStatement("INSERT INTO students_grades (SID,CRN,grade) VALUES(?,?,?) ");
            prepStatement.setInt(1, currUser);
            prepStatement.setString(2, cmbCRN.getSelectedItem().toString());
            prepStatement.setDouble(3, Double.parseDouble(cmbGrades.getSelectedItem().toString()));
            int result = prepStatement.executeUpdate();
            if (result > 0) {

                javax.swing.JLabel label = new javax.swing.JLabel("Course added successfully.");
                label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
                JOptionPane.showMessageDialog(null, label, "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "Error adding course.");
            }

            rs.close();
            statement.close();
            prepStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding course.");
        }
        try {
            calculateTotalGradeCredit();
        } catch (SQLException ex) {
            Logger.getLogger(AddCourseStud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void ConfirmCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmCourseActionPerformed
        jLabel6.setVisible(true);
        cmbCRN.setVisible(true);
        cmbGrades.setVisible(true);
        try{
            rs = statement.executeQuery("SELECT CRN FROM Sections WHERE Course_code = '"+cmbCourse.getSelectedItem().toString()+"'");
            rs.beforeFirst();
            while(rs.next()){
                cmbCRN.addItem(rs.getString("CRN"));
            }
            String arr[]={"4","3.7","3.3","3.0","2.7","2.3","2.0","1.7","1.0","0.0"};
            for(String x:arr){
                cmbGrades.addItem(x);
            }
            
        }
        catch (SQLException e) {JOptionPane.showMessageDialog(null, "Error adding course.");}
    }//GEN-LAST:event_ConfirmCourseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ConfirmCourse;
    private javax.swing.JButton btnAdd;
    private javax.swing.JComboBox<String> cmbCRN;
    private javax.swing.JComboBox<String> cmbCourse;
    private javax.swing.JComboBox<String> cmbGrades;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblEmpnoError;
    private javax.swing.JLabel lblEnameError;
    // End of variables declaration//GEN-END:variables
}
