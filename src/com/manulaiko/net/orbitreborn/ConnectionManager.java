package com.manulaiko.net.orbitreborn;

import com.manulaiko.net.SocketClient;

import java.net.Socket;

/**
 * OrbitReborn based server connection manager
 *
 * Used to interact with orbit reborn servers
 *
 * @author Manulaiko
 * @package com.manulaiko.net.orbitreborn
 */
public class ConnectionManager extends SocketClient
{
    private boolean isLogged;

    /**
     * Constructor
     *
     * @param socket
     */
    public ConnectionManager(Socket socket)
    {
        super(socket);
    }

    /**
     * Method to handle packets
     *
     * @param packet
     */
    public void onPacket(String packet)
    {
        String[] sPacket = packet.split("|");
        if(!this.isLogged) {
            if(sPacket[0] == "0") {
                this.isLogged = true;
                this.executeCallback("on_login");
            }
        }
    }

    /**
     * Sends necessary data in order to login
     *
     * @param userID
     * @param sessionID
     */
    public void login(int userID, String sessionID)
    {
        this.send("<policy-file-request/>");
        this.send("LOGIN|"+userID+"|"+sessionID);
    }
}
