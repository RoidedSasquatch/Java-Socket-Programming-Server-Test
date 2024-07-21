/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.algonquin.lab10;

import com.algonquin.lab10.server.Server;

/**
 *
 * @author Dan
 */
public class Run {

    public static void main(String[] args) {
        Server server = new Server();
        boolean connected = false;
        boolean isFinished = false;
        
        System.out.println("Awaiting Client Connection...");
        while(!connected) {
            connected = server.acceptConnection();
        }
        
        server.createInputStream();
        server.createOutputStream();
        
        while(!isFinished) {
            System.out.println("\nAwaiting message...");
            String message = server.receiveMessage();
            System.out.println("\nClient sent: " + message);
            
            System.out.println("\nSending response...");
            server.sendMessage(message);
            
            if(message.equalsIgnoreCase("Finish")) {
                isFinished = true;
            }
        }
        
    }
}
