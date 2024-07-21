/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.lab10.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dan
 */
public class Server {
    ServerSocket server;
    Socket client;
    DataOutputStream os;
    DataInputStream is;
    String serverName;
    
    public Server() {
        try {
            server = new ServerSocket(1254);
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
        } 
    }
    
    public boolean acceptConnection() {
         try {
            client = server.accept();
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
        }
         return client != null;
    } 
    
    public DataInputStream createInputStream() {
        try {
            is = new DataInputStream(new BufferedInputStream(client.getInputStream()));
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
        } 
        return is;
    }
    
    public DataOutputStream createOutputStream() {
        try {
            os = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
        } 
        return os;
    }
    
    public String receiveMessage() {
        String message = "";
        try {
            message = is.readUTF();
        } catch (IOException e) {
            System.out.println(e);
        }
        return message;
    }
    
    public void sendMessage(String message) {
        try {
            os.writeUTF(message); 
            os.flush();
        } catch (IOException e){
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
        }
    }
}
