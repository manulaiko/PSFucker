package com.manulaiko.psfucker.net

import javafx.util.Callback

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.util.TreeMap

/**
 * Socket Client class
 *
 * Used to interact with server through sockets
 *
 * @author Manulaiko
 * @package com.manulaiko.net
 */
abstract class SocketClient
/**
 * Constructor
 *
 * @param socket
 */
(
        /**
         * Connection socket
         *
         * @var java.net.Socket
         */
        private val socket: Socket) : Thread() {

    /**
     * Callbacks map
     *
     * @var java.util.TreeMap
     */
    private val callbacks = TreeMap<String, Callback>()

    /**
     * Last packet received
     *
     * @var String
     */
    var lastPacket = ""

    /**
     * Returns whether socket stills connected
     */
    val isConnected: Boolean
        get() = this.socket.isConnected

    /**
     * Runs the thread
     */
    override fun run() {
        try {
            var packet = ""
            val packetChar = CharArray(1)
            val `in` = BufferedReader(
                    InputStreamReader(
                            socket.getInputStream()
                    )
            )
            while (`in`.read(packetChar, 0, 1) != -1) {
                if (packetChar[0] != '\u0000' && packetChar[0] != '\n' && packetChar[0] != '\r') {
                    packet += packetChar[0]
                } else if (!packet.isEmpty()) {
                    println("Received: $packet")

                    this.lastPacket = String(packet.toByteArray(), "UTF8")
                    this.onPacket(lastPacket)
                }
            }
        } catch (e: Exception) {
            println("Couldn't read packet!")
            println(e.message)
        }

    }

    abstract fun onPacket(packet: String)

    /**
     * Sends packet to the server
     *
     * @param packet: the packet that we will send
     */
    fun send(packet: String) {
        try {
            //First get the PrintWriter object from the socket
            val out = PrintWriter(socket.getOutputStream(), true)
            //And then print the packet to the PrintWriter object
            out.print(packet + 0x00.toChar())
            //Flush it and you sent your packet!
            out.flush()

            println("Sent: $packet")
        } catch (e: IOException) {
            //We couldn't sent this packet! that's bad...
            println("Couldn't send packet!")
            println(e.message)
        }

    }

    /**
     * Adds a callback to the array
     *
     * @param id ID of callback
     * @param callback Callback to add to array
     */
    fun addCallback(id: String, callback: Callback) {
        this.callbacks[id] = callback
    }

    /**
     * Executes a callback
     *
     * @param id Callback's id
     */
    fun executeCallback(id: String): Any? {
        return if (this.callbacks.containsKey(id)) {
            this.callbacks[id].call(this)
        } else null
    }

    /**
     * Executes various callback
     *
     * @param ids Callbacks' id
     */
    fun executeCallbacks(ids: Array<String>) {
        for (id in ids) {
            if (this.callbacks.containsKey(id)) {
                this.callbacks[id].call(this)
            }
        }
    }

    /**
     * Executes all callbacks
     */
    fun executeAllCallbacks() {
        for ((_, value) in this.callbacks) {
            value.call(this)
        }
    }
}
