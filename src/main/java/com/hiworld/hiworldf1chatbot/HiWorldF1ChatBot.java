/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.hiworld.hiworldf1chatbot;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author ACER NITRO
 */
public class HiWorldF1ChatBot extends TelegramLongPollingBot {

    KoneksiMysql kon = new KoneksiMysql();
    Statement stmHistory, stmCommand, stmMember;
    ResultSet RsMember, RsCommand;

    @Override
    public void onUpdateReceived(Update update) {

	LocalDateTime currentTime = LocalDateTime.now();
	String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

	try (Connection conn = kon.getConnection()) {
	    saveHistory(formattedTime, update.getMessage().getChatId(), update.getMessage().getFrom().getFirstName(),
		    update.getMessage().getFrom().getUserName(), update.getMessage().getText());
	    stmMember = conn.createStatement();
	    RsMember = stmMember
		    .executeQuery("SELECT COUNT(*) FROM member WHERE user_id=" + update.getMessage().getChatId());

	    RsMember.next();
	    int count = RsMember.getInt(1);

	    if (count > 0) {
		sendResponse(update.getMessage().getChatId(), responseKeyword(update.getMessage().getText()));
		saveHistory(formattedTime, update.getMessage().getChatId(), "", getBotUsername(),
			responseKeyword(update.getMessage().getText()));
	    } else {
		if (update.getMessage().getText().equals("/start")) {
		    String response = "Selamat Datang di Bot F1 Daily by HiWorld Project.\n"
			    + "Pada bot ini akan menyajikan informasi seputar Formula 1.\n"
			    + "Ketik /register untuk dapat menggunakan Bot ini.\n"
			    + "Ketik /help untuk mengetahui apa saja informasi yang bisa bot berikan kepada anda.";
		    sendResponse(update.getMessage().getChatId(), response);
		    saveHistory(formattedTime, update.getMessage().getChatId(), "", getBotUsername(), response);
		} else if (update.getMessage().getText().equals("/register")) {
		    registerMember(update.getMessage().getChatId(), update.getMessage().getFrom().getUserName(),
			    update.getMessage().getFrom().getFirstName(), update.getMessage().getFrom().getLastName());
		    sendResponse(update.getMessage().getChatId(), responseKeyword(update.getMessage().getText()));
		    saveHistory(formattedTime, update.getMessage().getChatId(), "", getBotUsername(),
			    responseKeyword(update.getMessage().getText()));
		} else {
		    String response = "Maaf anda belum terdaftar pada sistem.\n"
			    + "Silahkan ketik /register terlebih dahulu untuk bisa menggunakan bot ini.\n";
		    sendResponse(update.getMessage().getChatId(), response);
		    saveHistory(formattedTime, update.getMessage().getChatId(), "", getBotUsername(), response);
		}
	    }
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    @Override
    public String getBotUsername() {
	return "hiracing_f1_bot";
    }

    @Override
    public String getBotToken() {
	return "5810881319:AAEeBSJWD7Al4mkIzOE-KSwksjMAR3ggvpA";
    }

    private String responseKeyword(String keyword) {
	String response = "";
	try (Connection conn = kon.getConnection()) {
	    stmCommand = conn.createStatement();
	    RsCommand = stmCommand.executeQuery("SELECT * FROM command WHERE keyword='" + keyword + "'");
	    if (RsCommand.next()) {
		response = RsCommand.getString("response_msg");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return response;
    }

    public void responseBc(SendMessage responseBc, String firstName, String username) throws SQLException {
	LocalDateTime currentTime = LocalDateTime.now();
	String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	try {
	    execute(responseBc);
	    saveHistory(formattedTime, Long.valueOf(responseBc.getChatId()), firstName, username, responseBc.getText());
	} catch (TelegramApiException e) {
	    JOptionPane.showMessageDialog(null, "Error sending message: " + e.getMessage());
	}
    }

    private void registerMember(long chatId, String username, String firstName, String lastName) {
	try (Connection conn = kon.getConnection()) {
	    stmMember = conn.createStatement();
	    stmMember.executeUpdate("INSERT INTO member (user_id, username, first_name, last_name) VALUES (" + chatId
		    + ", '" + username + "', '" + firstName + "', '" + lastName + "')");
	} catch (SQLException e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    private void saveHistory(String time, long chatId, String firstName, String username, String msgReceived) {
	try (Connection conn = kon.getConnection()) {
	    stmHistory = conn.createStatement();
	    stmHistory.executeUpdate("INSERT INTO history (time, user_id, first_name, username, message) VALUES('"
		    + time + "', " + chatId + ", '" + firstName + "', '" + username + "', '" + msgReceived + "')");

	} catch (SQLException e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    private void sendResponse(long chatId, String msgRes) {
	SendMessage message = new SendMessage();
	message.setChatId(chatId);
	message.setText(msgRes);
	try {
	    execute(message);
	} catch (TelegramApiException e) {
	    JOptionPane.showMessageDialog(null, "Error sending message: " + e.getMessage());
	}
    }
}
