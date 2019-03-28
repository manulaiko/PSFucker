package com.manulaiko.psfucker

import java.util.TreeMap

import com.manulaiko.psfucker.utils.Tools

/**
 * Base interface for all servers fuckers
 *
 * @author Manulaiko
 * @package com.manulaiko
 */
abstract class Fucker
/**
 * Constructor
 * @param fp
 * @param server
 */
(fp: Fingerprinter<*>, name: String, server: String) {
    /**
     * Server fingerprinter
     *
     * @var com.manulaiko.Fingerprinter
     */
    var fingerprinter: Fingerprinter<*>? = null

    /**
     * Options that the fucker provides
     *
     * The key is the name of the option that user should send as input
     * and the value is an Option object containing the fuck method
     */
    var options: Map<String, Option> = TreeMap()

    /**
     * Server address
     */
    var server = ""

    /**
     * Server name
     */
    var name = ""

    init {
        this.fingerprinter = fp
        this.name = name
        this.server = server
    }

    /**
     * Fucks the server itself
     */
    fun fuck() {
        var option = "undefined"

        while (!option.equals("exit", ignoreCase = true) || !option.equals("quit", ignoreCase = true)) {
            println("Fucking " + this.name + " based server")
            println("Choose something from the list:")

            var i = 0
            for ((key, value) in this.options) {
                i++
                println(" " + i + ") " + key + ": " + value.description)
            }
            println(" " + ++i + ") exit: Stops fucking the server")

            print("What you do you want to do? ")
            option = Tools.scanner.nextLine()

            if (option.equals("exit", ignoreCase = true)) {
                return
            }

            for ((key, value) in this.options) {
                if (key.equals(option, ignoreCase = true)) {
                    value.fuck()
                }
            }
        }
    }

    /**
     * Sets server options
     */
    abstract fun setOptions()
}
