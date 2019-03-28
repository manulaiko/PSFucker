package com.manulaiko.psfucker.utils

import java.util.Random
import java.util.Scanner

/**
 * Utils class
 *
 * Contains random shit
 *
 * @author Manulaiko
 * @package com.manulaiko.utils
 */
object Tools {
    /**
     * Scanner object
     *
     * Used o read user's input
     *
     * @var java.util.Scanner
     */
    var scanner = Scanner(System.`in`)
    var r: Random? = null

    /**
     * Clears console
     */
    fun clearConsole() {
        try {
            val os = System.getProperty("os.name")

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls")
            } else {
                Runtime.getRuntime().exec("clear")
            }
        } catch (e: Exception) {
            println("Couldn't clear console!")
            println(e.message)
            if (com.manulaiko.psfucker.Main.DEBUG) {
                e.printStackTrace()
            }
        }

        System.out.println("PSFucker " + com.manulaiko.psfucker.Main.version + " by Manulaiko")
        println("----------------------------")
    }

    /**
     * Parses a string so it can be executed through URL
     *
     * @param code String to parse
     * @return Parsed string
     */
    fun toURL(code: String): String {
        return code.replace(" ", "%20")
                .replace("!", "%21")
                .replace("\"", "%22")
                .replace("#", "%23")
                .replace("$", "%24")
                .replace("%", "%25")
                .replace("&", "%26")
                .replace("\'", "%27")
                .replace("(", "%28")
                .replace(")", "%29")
    }
}