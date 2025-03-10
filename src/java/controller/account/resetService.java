/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.account;

import controller.account.*;
import controller.club.*;
import controller.event.*;
import controller.setting.*;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 *
 * @author HP
 */
public class resetService {
    private final int LIMIT_MINUS = 10;
    static final String from = "xuanquanyd@gmail.com";
    static final String password = "skgt waor gqiv jpkr";
    
    public String generateToken() {
        return UUID.randomUUID().toString();
    }
        
    public LocalDateTime expireDateTime() {
        return LocalDateTime.now().plusMinutes(LIMIT_MINUS);
    }
    
    public boolean isExpireTime(LocalDateTime time) {
        return LocalDateTime.now().isAfter(time);
    }
    
    
   
}
