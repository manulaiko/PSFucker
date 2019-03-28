package com.manulaiko.psfucker.fingerprinters

import com.manulaiko.psfucker.net.HTTPClient

/**
 * OrbitReborn fingerprinter
 *
 * @author Manulaiko
 * @package com.manulaiko.fingerprinters
 */
class OrbitReborn : com.manulaiko.psfucker.Fingerprinter<*>() {
    override fun identify(server: String): Boolean {
        val connection = HTTPClient(server)
        val controllersExists = connection.pageExists("controllers")
        if (controllersExists) {
            return true
        }

        val modelsViews = connection.pageExists("models")
        if (modelsViews) {
            return true
        }

        val viewsExists = connection.pageExists("views")
        if (viewsExists) {
            return true
        }

        val libsExists = connection.pageExists("lib")
        if (libsExists) {
            return true
        }

        val debugExists = connection.pageExists("flashAPI/debug.php")
        return if (debugExists) {
            true
        } else connection.pageExists("flashAPI/infos.txt")

//No more checks, otherwise there's a huge probability that it's a based one server
    }
}
