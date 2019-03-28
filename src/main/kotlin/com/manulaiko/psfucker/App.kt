package com.manulaiko.psfucker

import com.manulaiko.psfucker.fingerprinters.*
import com.manulaiko.psfucker.utils.*
import java.util.*

/**
 * PSFucker main class
 * ===================
 *
 * App's entry point.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
class App {
    /**
     * Version
     *
     * @var String
     */
    private var version = "v1.0.0"

    /**
     * Server ip
     *
     * @var String
     */
    var server = ""

    /**
     * Fingerprinters.
     *
     * @var Map
     */
    private val fingerprinters: MutableMap<String, Fingerprinter> = TreeMap()

    /**
     * Main method.
     *
     * Starts the application.
     */
    fun start() {
        println("PSFucker $version by Manulaiko")
        println("----------------------------")

        println("Loading fingerprinters...")
        loadFingerprinters()
        println(fingerprinters.size.toString() + " servers loaded!")

        run()
    }

    /**
     * Loads the fingerprinters.
     */
    private fun loadFingerprinters() {
        fingerprinters["OrbitReborn"] = OrbitReborn()
        fingerprinters["Global"] = Global()
    }

    /**
     * Fingerprints the server
     *
     * Will try to fingerprint the server or return the global one.
     *
     * @param server Server to fingerprint
     *
     * @return Fingerprinter object
     */
    private fun fingerprint(server: String): Fingerprinter {
        val matches = TreeMap<String, Fingerprinter>()

        fingerprinters.forEach { k, f ->
            if (f.identify(server)) {
                matches[k] = f
            }
        }

        if (matches.size <= 0) {
            println("Couldn't identify server :(")

            return fingerprinters["Global"]!!
        }

        println(matches.size.toString() + " matches found!")
        println("Choose which fucker you want to use from the list:")

        matches.forEach { k, _ -> println(" -$k based server fucker") }

        print("Enter the name of the server: ")
        val match = Tools.scanner.nextLine()

        if (!matches.containsKey(match)) {
            println("You entered a wrong value!")
            println("Please, choose one of the following servers... let's see if this time you can do it")

            fingerprint(server)
        }

        return matches[match]!!
    }

    /**
     * Starts PSFucker
     *
     * Ask for the server, fingerprints the server and fucks the server
     *
     * Once server has been fucked it will ask for the server again
     */
    private fun run() {
        print("Enter server IP: ")
        server = Tools.scanner.nextLine()

        println("Fingerprinting server...")
        val fServer = fingerprint(server)

        println("Fucking server $server!")

        fServer.fucker?.fuck()

        run()
    }
}

fun main() {
    App().start()
}
