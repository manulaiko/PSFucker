package com.manulaiko.psfucker.net

import java.net.*
import java.io.*
import java.util.*

/**
 * HTTP Client
 *
 * Socket client for executing simple HTTP request
 *
 * @author Manulaiko
 * @package com.manulaiko.net
 */
class HTTPClient
/**
 * Constructor
 */
(server: String) {
    /* End response codes */
    /**
     * Cookies
     */
    var cookies: List<String>

    /**
     * The connection object
     */
    private var conn: HttpURLConnection? = null

    /**
     * User-Agent header
     */
    var userAgent = "Mozilla/5.0"

    /**
     * Server's url
     */
    private val server = ""

    /**
     * Last executed request result
     */
    var lastResponseCode = 0

    init {
        var server = server
        if (!server.startsWith("http://") || !server.startsWith("https://")) {
            server = "http://$server"
        }
        if (!server.endsWith("/")) {
            server = "$server/"
        }

        this.server = server
    }

    /**
     * Returns whether a path/file scanner the server exists or not
     *
     * @param path Path to check
     * @return True if exists, false if not
     */
    fun pageExists(path: String): Boolean {
        this.execute("GET", path)
        return this.lastResponseCode != RESPONSE_CODE_NOT_FOUND
    }

    /**
     * Executes a request to the server
     *
     * @param method Request method
     * @param path
     * @param headers
     * @param cookies
     */
    fun execute(path: String, method: String, headers: Map<String, String>, cookies: List<String>?): Boolean {

        val url = this.server + path
        try {
            if (com.manulaiko.psfucker.Main.DEBUG) {
                println("Sending 'GET' request to URL: $url")
            }

            val obj = URL(url)
            conn = obj.openConnection() as HttpURLConnection

            // default is GET
            conn!!.requestMethod = method
            conn!!.useCaches = false

            // act like a browser
            for ((key, value) in headers) {
                conn!!.setRequestProperty(key, value)
            }

            if (cookies != null) {
                for (cookie in cookies) {
                    if (com.manulaiko.psfucker.Main.DEBUG) {
                        println("Setting cookie: $cookie")
                    }
                    conn!!.addRequestProperty("Cookie", cookie.split(";".toRegex(), 1).toTypedArray()[0])
                }
            }

            this.lastResponseCode = conn!!.responseCode
            if (com.manulaiko.psfucker.Main.DEBUG) {
                println("Response Code: " + this.lastResponseCode)
            }

            this.cookies = conn!!.headerFields["Set-Cookie"]
            return true
        } catch (e: Exception) {
            println(e.message)
            e.printStackTrace()
        }

        return false
    }

    /**
     * Executes a request to the server
     *
     * @param method Request method
     * @param path
     */
    fun execute(path: String, method: String): Boolean {
        val headers = TreeMap<String, String>()
        headers["User-Agent"] = userAgent
        headers["Accept"] = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
        headers["Accept-Language"] = "en-US,en;q=0.5"

        return this.execute(path, method, headers, this.cookies)
    }

    /**
     * Returns the source of a given path
     */
    fun getPageContent(path: String): String {
        this.execute(path, "GET")
        try {
            val `in` = BufferedReader(
                    InputStreamReader(conn!!.inputStream)
            )
            var inputLine: String
            val response = StringBuffer()

            while ((inputLine = `in`.readLine()) != null) {
                response.append(inputLine)
            }
            `in`.close()

            // Get the response cookies
            this.cookies = conn!!.headerFields["Set-Cookie"]

            if (this.lastResponseCode != 200) {
                if (com.manulaiko.psfucker.Main.DEBUG) {
                    println("There was an error executing the HTTP request!")
                    println(response.toString())
                }
                return ""
            }

            return response.toString()
        } catch (e: Exception) {
            println(e.message)
            e.printStackTrace()
        }

        return ""
    }

    companion object {
        /* Start response codes */
        val RESPONSE_CODE_OK = 200
        val RESPONSE_CODE_NOT_FOUND = 404
    }
}