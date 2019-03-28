package com.manulaiko.psfucker.net.global

import com.manulaiko.psfucker.net.SocketClient

import java.net.Socket

/**
 * Connection manager for global servers
 *
 * @author Manulaiko
 * @package com.manulaiko.net.global
 */
class ConnectionManager
/**
 * Constructor
 */
(socket: Socket) : SocketClient(socket) {
    /**
     * On packet method
     *
     * Actually does nothing
     *
     * @param packet
     */
    override fun onPacket(packet: String) {
        //do nothing
    }
}
