package com.manulaiko.psfucker.net.orbitreborn

import com.manulaiko.psfucker.net.SocketClient

import java.net.Socket

/**
 * OrbitReborn based server connection manager
 *
 * Used to interact with orbit reborn servers
 *
 * @author Manulaiko
 * @package com.manulaiko.net.orbitreborn
 */
class ConnectionManager
/**
 * Constructor
 *
 * @param socket
 */
(socket: Socket) : SocketClient(socket) {
    private var isLogged: Boolean = false

    /**
     * Method to handle packets
     *
     * @param packet
     */
    override fun onPacket(packet: String) {
        val sPacket = packet.split("|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (!this.isLogged) {
            if (sPacket[0] === "0") {
                this.isLogged = true
                this.executeCallback("on_login")
            }
        }
    }

    /**
     * Sends necessary data scanner order to login
     *
     * @param userID
     * @param sessionID
     */
    fun login(userID: Int, sessionID: String) {
        this.send("<policy-file-request/>")
        this.send("LOGIN|$userID|$sessionID")
    }
}
