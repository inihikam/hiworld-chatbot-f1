/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package com.hiworld.hiworldf1chatbot;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ACER NITRO
 */
public class frmHistory extends javax.swing.JFrame {

    KoneksiMysql kon = new KoneksiMysql();
    Connection conn;
    ResultSet RsHst;
    Statement stm;
    private Object[][] dataTable = null;
    private String[] header = { "Time", "Username", "No Telp", "Message" };

    /** Creates new form frmHistory */
    public frmHistory() {
	initComponents();
	initUI();
	getData();
	setTombol(false);
    }

    private void initUI() {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension windowSize = getSize();

	int dx = (screenSize.width - windowSize.width) / 2;
	int dy = (screenSize.height - windowSize.height) / 2;

	setLocation(dx, dy);
    }

    private void getData() {
	try {
	    java.util.Date selectedDate = dtCari.getDate();
	    conn = kon.getConnection();
	    stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    if (selectedDate == null) {
		RsHst = stm.executeQuery("SELECT * FROM history");
		ResultSetMetaData meta = RsHst.getMetaData();
		int col = meta.getColumnCount();
		int baris = 0;
		while (RsHst.next()) {
		    baris = RsHst.getRow();
		}
		dataTable = new Object[baris][col];
		int x = 0;
		RsHst.beforeFirst();
		while (RsHst.next()) {
		    dataTable[x][0] = RsHst.getTimestamp("time");
		    dataTable[x][1] = RsHst.getString("user_id");
		    dataTable[x][2] = RsHst.getString("username");
		    dataTable[x][3] = RsHst.getString("message");
		    x++;
		}
		tblHistory.setModel(new DefaultTableModel(dataTable, header));
	    } else {
		java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
		LocalDate date = sqlDate.toLocalDate();
		String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		RsHst = stm.executeQuery("SELECT * FROM history WHERE DATE(time)='" + formattedDate + "'");
		ResultSetMetaData meta = RsHst.getMetaData();
		int col = meta.getColumnCount();
		int baris = 0;
		while (RsHst.next()) {
		    baris = RsHst.getRow();
		}
		dataTable = new Object[baris][col];
		int x = 0;
		RsHst.beforeFirst();
		while (RsHst.next()) {
		    dataTable[x][0] = RsHst.getTimestamp("time");
		    dataTable[x][1] = RsHst.getString("user_id");
		    dataTable[x][2] = RsHst.getString("username");
		    dataTable[x][3] = RsHst.getString("message");
		    x++;
		}
		tblHistory.setModel(new DefaultTableModel(dataTable, header));
	    }

	} catch (SQLException e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    private void setTombol(boolean t) {
	btnDelete.setEnabled(t);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        btnCari = new javax.swing.JButton();
        dtCari = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(238, 238, 238));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(57, 62, 70));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(238, 238, 238));
        jLabel1.setText("History Management");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(337, 337, 337)
                .addComponent(jLabel1)
                .addContainerGap(336, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        tblHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Username", "No Telp", "Message"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHistoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHistory);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCari)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCari)
                    .addComponent(dtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
	new frmMenu().setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
	int row = tblHistory.getSelectedRow();
	Timestamp time = (Timestamp) tblHistory.getValueAt(row, 0);
	try {
	    stm.executeUpdate("DELETE FROM history WHERE time='"+time+"'");

	    getData();
	    setTombol(false);
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	    setTombol(false);
	}
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHistoryMouseClicked
        // TODO add your handling code here:
	setTombol(true);
    }//GEN-LAST:event_tblHistoryMouseClicked

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
	getData();
    }//GEN-LAST:event_btnCariActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
	/* Set the Nimbus look and feel */
	// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
	// (optional) ">
	/*
	 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
	 * look and feel. For details see
	 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
	 */
	try {
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(frmHistory.class.getName()).log(java.util.logging.Level.SEVERE, null,
		    ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(frmHistory.class.getName()).log(java.util.logging.Level.SEVERE, null,
		    ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(frmHistory.class.getName()).log(java.util.logging.Level.SEVERE, null,
		    ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(frmHistory.class.getName()).log(java.util.logging.Level.SEVERE, null,
		    ex);
	}
	// </editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new frmHistory().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnDelete;
    private com.toedter.calendar.JDateChooser dtCari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblHistory;
    // End of variables declaration//GEN-END:variables

}
