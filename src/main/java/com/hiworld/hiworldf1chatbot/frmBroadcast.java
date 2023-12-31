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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author ACER NITRO
 */
public class frmBroadcast extends javax.swing.JFrame {

    KoneksiMysql kon = new KoneksiMysql();
    Connection conn;
    ResultSet RsBc;
    Statement stm;
    private Object[][] dataTable = null;
    private String[] header = { "Broadcast Message" };

    /** Creates new form frmBroadcast */
    public frmBroadcast() {
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
	    conn = kon.getConnection();
	    stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    RsBc = stm.executeQuery("SELECT * FROM broadcast");
	    ResultSetMetaData meta = RsBc.getMetaData();
	    int col = meta.getColumnCount();
	    int baris = 0;
	    while (RsBc.next()) {
		baris = RsBc.getRow();
	    }
	    dataTable = new Object[baris][col];
	    int x = 0;
	    RsBc.beforeFirst();
	    while (RsBc.next()) {
		dataTable[x][0] = RsBc.getString("bc_msg");
		x++;
	    }
	    tblBc.setModel(new DefaultTableModel(dataTable, header));
	} catch (SQLException e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    private void setField() {
	int row = tblBc.getSelectedRow();
	txtBc.setText((String) tblBc.getValueAt(row, 0));
    }

    private void kosong() {
	txtBc.setText("");
    }

    private void setTombol(boolean t) {
	btnUpdate.setEnabled(t);
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
        tblBc = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtBc = new javax.swing.JTextArea();
        btnKirim = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

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
        jLabel1.setText("Broadcast Management");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(321, 321, 321)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        tblBc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Broadcast Message"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBcMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBc);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(34, 40, 49));
        jLabel2.setText("Broadcast Pesan");

        txtBc.setColumns(20);
        txtBc.setRows(5);
        jScrollPane2.setViewportView(txtBc);

        btnKirim.setText("Kirim");
        btnKirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKirimActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnKirim))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnKirim)
                            .addComponent(btnUpdate)
                            .addComponent(btnDelete))))
                .addGap(0, 25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
	new frmMenu().setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void btnKirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimActionPerformed
        // TODO add your handling code here:
	String bc = txtBc.getText();
	HiWorldF1ChatBot bcBot = new HiWorldF1ChatBot();
	try {
	    conn = kon.getConnection();
	    stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    RsBc = stm.executeQuery("SELECT * FROM member");
	    while(RsBc.next()){
		SendMessage responseBc = new SendMessage();
		responseBc.setChatId(Long.valueOf(RsBc.getString("user_id")));
		responseBc.setText(bc);
		bcBot.responseBc(responseBc, RsBc.getString("first_name"), RsBc.getString("username"));
	    }
	    stm.executeUpdate("INSERT INTO broadcast (bc_msg) VALUES ('"+bc+"')");
	    tblBc.setModel(new DefaultTableModel(dataTable, header));

	    getData();
	    kosong();
	    setTombol(false);
	} catch (SQLException e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }//GEN-LAST:event_btnKirimActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
	int row = tblBc.getSelectedRow();
	String bcMsg = (String) tblBc.getValueAt(row, 0);
	try {
	    stm.executeUpdate("DELETE FROM broadcast WHERE bc_msg='"+bcMsg+"'");

	    getData();
	    setTombol(false);
	    kosong();
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	    setTombol(false);
	}
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
	int row = tblBc.getSelectedRow();
	String oldBc = ((String) tblBc.getValueAt(row, 0));
	String tBc = txtBc.getText();
	try {
	    stm.executeUpdate("UPDATE broadcast SET bc_msg='"+tBc+"' WHERE bc_msg='"+oldBc+"'");
	    tblBc.setModel(new DefaultTableModel(dataTable, header));

	    getData();
	    kosong();
	    setTombol(false);
	} catch (SQLException e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblBcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBcMouseClicked
        // TODO add your handling code here:
	setField();
	setTombol(true);
    }//GEN-LAST:event_tblBcMouseClicked

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
	    java.util.logging.Logger.getLogger(frmBroadcast.class.getName()).log(java.util.logging.Level.SEVERE, null,
		    ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(frmBroadcast.class.getName()).log(java.util.logging.Level.SEVERE, null,
		    ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(frmBroadcast.class.getName()).log(java.util.logging.Level.SEVERE, null,
		    ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(frmBroadcast.class.getName()).log(java.util.logging.Level.SEVERE, null,
		    ex);
	}
	// </editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new frmBroadcast().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnKirim;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblBc;
    private javax.swing.JTextArea txtBc;
    // End of variables declaration//GEN-END:variables

}
