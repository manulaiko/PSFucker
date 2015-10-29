package com.manulaiko.psfucker.net;

import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

/**
 * Socket Client class
 *
 * Used to interact with server through sockets
 *
 * @author Manulaiko
 * @package com.manulaiko.net
 */
public abstract class SocketClient extends Thread
{
    /**
     * Connection socket
     *
     * @var java.net.Socket
     */
    private Socket socket;

    /**
     * Callbacks map
     *
     * @var java.util.TreeMap
     */
    private Map<String, Callback> callbacks = new TreeMap<>();

    /**
     * Last packet received
     *
     * @var String
     */
    public String lastPacket = "";

    /**
     * Constructor
     *
     * @param socket
     */
    public SocketClient(Socket socket)
    {
        this.socket = socket;
    }

    /**
     * Returns whether socket stills connected
     */
    public boolean isConnected()
    {
        return this.socket.isConnected();
    }

    /**
     * Runs the thread
     */
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

                    this.lastPacket = new String(packet.getBytes(), "UTF8");
                    this.onPacket(lastPacket);
                }
            }
        } catch (Exception e) {
            System.out.println("Couldn't read packet!");
            System.out.println(e.getMessage());
        }
    }

    public abstract void onPacket(String packet);

    /**
     * Sends packet to the server
     *
     * @param packet: the packet that we will send
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
     * Adds a callback to the array
     *
     * @param id ID of callback
     * @param callback Callback to add to array
     */
    public void addCallback(String id, Callback callback)
    {
        this.callbacks.put(id, callback);
    }

    /**
     * Executes a callback
     *
     * @param id Callback's id
     */
    public Object executeCallback(String id)
    {
        if(this.callbacks.containsKey(id)) {
            return this.callbacks.get(id).call(this);
        }
        return null;
    }

    /**
     * Executes various callback
     *
     * @param ids Callbacks' id
     */
    public void executeCallbacks(String[] ids)
    {
        for(String id : ids) {
            if (this.callbacks.containsKey(id)) {
                this.callbacks.get(id).call(this);
            }
        }
    }

    /**
     * Executes all callbacks
     */
    public void executeAllCallbacks()
    {
        for(Map.Entry<String, Callback> callback : this.callbacks.entrySet()) {
            callback.getValue().call(this);
        }
    }
}
