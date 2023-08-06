/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hiworld.hiworldf1chatbot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.meta.api.objects.*;

/**
 *
 * @author ACER NITRO
 */
public class Main {
    public static void main(String[] args) {
	try {
	    TelegramBotsApi botAPI = new TelegramBotsApi(DefaultBotSession.class);
	    botAPI.registerBot(new HiWorldF1ChatBot());
	    new frmLogin().setVisible(true);
	} catch (TelegramApiException e) {
	    e.printStackTrace();
	}
    }
}
