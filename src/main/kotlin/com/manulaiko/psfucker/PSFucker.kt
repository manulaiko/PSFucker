package com.manulaiko.psfucker

import java.net.*
import java.io.*

import com.manulaiko.psfucker.utils.*

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
class PSFucker : Thread() {
    /**
     * Socket
     */
    var socket: Socket

    /**
     * Constructor
     *
     * @param server Server IP
     */
    fun PSFucker(server: String) {
        try {
            println("Establishing connection to $server on port 8080...")
            socket = Socket(server, 8080)
            println("Connected to $server on port 8080!")

            //Load thread to read packets
            val received = object : Thread() {
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
                                packet = ""
                            }
                        }
                    } catch (e: Exception) {
                        println("Couldn't read packet!")
                        println(e.message)
                    }

                }
            }
            received.start()

            this.start()
        } catch (e: Exception) {
            println("Couldn't connect to server!")
            println(e.message)
        }

    }

    /**
     * Sends a packet
     *
     * @param packet Packet to send
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
     * Reads options and sends packets
     */
    override fun run() {
        println("Sending login packet, let's throw the bait...")
        send("LOGIN|FUCKYOU|FUCKINGPS|YOLO") //Totally not intended to throw exceptions on server

        var option = ""
        while (!option.equals("disconnect", ignoreCase = true)) {
            println("What do you want to do?")
            println(" -'disconnect' Disconnect from server")
            println(" -'send' Sends a packet to the client, not usefull")
            println(" -'jump' Performs a jump, idk why you would need to jump")
            println(" -'delay' No fucking idea what it does, but it's on source")
            println(" -'godmode' Test godmode")
            println(" -'ship' Changes ship")
            println(" -'hp' Changes hp")
            println(" -'shield' Changes shield")
            println(" -'speed' Changes speed")
            println(" -'damage' Changes damage")
            println(" -'restart' Closes server :D")
            println(" -'pet' Pet for you (only packet)")
            println(" -'PET' Pet for everyone!")
            println(" -'Spaceball' Activates spaceball")
            println(" -'ID' Might throw an exception at server")
            println(" -'sendall' Sends a packet to everyone :)")

            option = Tools.scanner.nextLine()
            var packet = ""

            when (option) {
                "godmode" -> this.godmode()

                "sendall" -> {
                    Tools.clearConsole()
                    print("Enter packet to send: ")
                    packet = Tools.scanner.nextLine()
                    send("sendall|$packet")
                }

                "discconect" -> {
                }

                else -> println("Dude, can't you read?")
            }
        }

        println("Bye bye")
        send("Okay, I'm done with you for today :)")

        try {
            socket.close()
        } catch (e: Exception) {
            //Awesome handling catch block
        }

    }

    /**
     * Godmode
     *
     * Sends godmode packets
     */
    fun godmode() {
        Tools.clearConsole()
        println("Which packet you want me to mess with?")
        println(" -'activate' Activates godmode on server")
        println(" -'deactivate' Same as above but clearly different")
        println(" -'SMB' Spams with 'smart'bombs")
        println(" -'ISH' Spams with instant shields")
        println(" -'EMP' Spams with emps")
        println(" -'BRB' Spams with battle repair robots")
        println(" -'SBU' Spams with shield backups")

        val option = Tools.scanner.nextLine()

        when (option) {
            "activate" -> {
                send("godmode|on")
                println("Godmode activated :)")
            }

            "deactivate" -> {
                send("godmode|off")
                println("Godmode deactivated :(")
            }

            "SMB", "ISH", "EMP", "BRB", "SBU" -> {
                send("godmode|$option")
                println("Spamming $option!")
            }

            else -> println("Dude, can't you read?")
        }
    }
}