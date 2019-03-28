package com.manulaiko.psfucker;

import java.net.*;
import java.io.*;

import com.manulaiko.psfucker.utils.*;

/**
 * Main PSFucker class
 * 
 * This class will actually fuck the ps
 * 
 * Well actually this class is never used, yet...
 * 
 * @author Manulaiko
 * @package com.manulaiko
 */
public class PSFucker extends Thread
{
    /**
     * Socket
     */
    public Socket socket;

    /**
     * Constructor
     * 
     * @param server Server IP
     */
    public void PSFucker(String server)
    {
        try {
			System.out.println("Establishing connection to "+ server +" on port 8080...");
			socket = new Socket(server, 8080);
			System.out.println("Connected to "+ server +" on port 8080!");
			
			//Load thread to read packets
    		Thread received = new Thread()
    		{
    			public void run()
    			{
    				try {
    					String packet = "";
    					char[] packetChar = new char[1];
    					BufferedReader in = new BufferedReader(
    								new InputStreamReader(
    										socket.getInputStream()
    										)
    								);
    					while(in.read(packetChar, 0, 1) != -1)
    					{
    						if(packetChar[0] != '\u0000' && packetChar[0] != '\n' && packetChar[0] != '\r') {
    							packet += packetChar[0];
    						} else if (!packet.isEmpty()) {
    						    System.out.println("Received: " + packet);
    							packet = "";
    						}
    					}
    				} catch (Exception e) {
    					System.out.println("Couldn't read packet!");
    					System.out.println(e.getMessage());
    				}
    			}
    		};
    		received.start();
    		
			this.start();
        } catch(Exception e) {
            System.out.println("Couldn't connect to server!");
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Sends a packet
     * 
     * @param packet Packet to send
     */
    public void send(String packet)
    {
        try {
			//First get the PrintWriter object from the socket
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			//And then print the packet to the PrintWriter object
			out.print((packet)+(char)0x00);
			//Flush it and you sent your packet!
			out.flush();
			
			System.out.println("Sent: "+ packet);
		} catch (IOException e) {
			//We couldn't sent this packet! that's bad...
			System.out.println("Couldn't send packet!");
			System.out.println(e.getMessage());
		}
    }
    
    /**
     * Reads options and sends packets
     */
    @Override
    public void run()
    {
        System.out.println("Sending login packet, let's throw the bait...");
        send("LOGIN|FUCKYOU|FUCKINGPS|YOLO"); //Totally not intended to throw exceptions on server
        
        String option = "";
        while(!option.equalsIgnoreCase("disconnect")) {
            System.out.println("What do you want to do?");
            System.out.println(" -'disconnect' Disconnect from server");
            System.out.println(" -'send' Sends a packet to the client, not usefull");
            System.out.println(" -'jump' Performs a jump, idk why you would need to jump");
            System.out.println(" -'delay' No fucking idea what it does, but it's on source");
            System.out.println(" -'godmode' Test godmode");
            System.out.println(" -'ship' Changes ship");
            System.out.println(" -'hp' Changes hp");
            System.out.println(" -'shield' Changes shield");
            System.out.println(" -'speed' Changes speed");
            System.out.println(" -'damage' Changes damage");
            System.out.println(" -'restart' Closes server :D");
            System.out.println(" -'pet' Pet for you (only packet)");
            System.out.println(" -'PET' Pet for everyone!");
            System.out.println(" -'Spaceball' Activates spaceball");
            System.out.println(" -'ID' Might throw an exception at server");
            System.out.println(" -'sendall' Sends a packet to everyone :)");
            
            option = Tools.scanner.nextLine();
            String packet = "";
            
            switch(option)
            {
                case "godmode":
                    this.godmode();
                    break;
                
                case "sendall":
                    Tools.clearConsole();
                    System.out.print("Enter packet to send: ");
                    packet = Tools.scanner.nextLine();
                    send("sendall|"+packet);
                    break;
                
                case "discconect":
                    break;
                
                default:
                    System.out.println("Dude, can't you read?");
                    break;
            }
        }
        
        System.out.println("Bye bye");
        send("Okay, I'm done with you for today :)");
        
        try {
            socket.close();
        } catch(Exception e) {
            //Awesome handling catch block
        }
    }
    
    /**
     * Godmode
     * 
     * Sends godmode packets
     */
    public void godmode()
    {
        Tools.clearConsole();
        System.out.println("Which packet you want me to mess with?");
        System.out.println(" -'activate' Activates godmode on server");
        System.out.println(" -'deactivate' Same as above but clearly different");
        System.out.println(" -'SMB' Spams with 'smart'bombs");
        System.out.println(" -'ISH' Spams with instant shields");
        System.out.println(" -'EMP' Spams with emps");
        System.out.println(" -'BRB' Spams with battle repair robots");
        System.out.println(" -'SBU' Spams with shield backups");
        
        String option = Tools.scanner.nextLine();
        
        switch(option)
        {
            case "activate":
                send("godmode|on");
                System.out.println("Godmode activated :)");
                break;
                
            case "deactivate":
                send("godmode|off");
                System.out.println("Godmode deactivated :(");
                break;
            
            case "SMB":
            case "ISH":
            case "EMP":
            case "BRB":
            case "SBU":
                send("godmode|"+ option);
                System.out.println("Spamming "+ option +"!");
                break;
            
            default:
                System.out.println("Dude, can't you read?");
                break;
        }
    }
}