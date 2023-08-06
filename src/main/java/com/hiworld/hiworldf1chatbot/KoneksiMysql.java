package com.hiworld.hiworldf1chatbot;

import java.sql.*;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ACER NITRO
 */
public class KoneksiMysql {
    public Connection getConnection() {
	Connection con = null;
	try {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/hiworld-chatbot", "root", "");
	} catch (ClassNotFoundException e) {
	    JOptionPane.showMessageDialog(null, "Error #1 : " + e.getMessage());
	} catch (SQLException e) {
	    JOptionPane.showMessageDialog(null, "Error #2 : " + e.getMessage());
	}
	return con;
    }
}
