package com.manulaiko.net.global;

import com.manulaiko.net.SocketClient;

import java.net.Socket;

/**
 * Connection manager for global servers
 *
 * @author Manulaiko
 * @package com.manulaiko.net.global
 */
public class ConnectionManager extends SocketClient
{
    /**
     * Constructor
     */
    public ConnectionManager(Socket socket)
    {
        super(socket);
    }
    /**
     * On packet method
     *
     * Actually does nothing
     *
     * @param packet
     */
    public void onPacket(String packet)
    {
        //do nothing
    }
}
