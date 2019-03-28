package com.manulaiko.psfucker.fingerprinters

import com.manulaiko.psfucker.Fingerprinter

/**
 * Fingerprinter for all servers
 *
 * Does nothing and returns true :D
 *
 * @author Manulaiko
 * @package com.manulaiko.fingerprinters
 */
class Global : Fingerprinter<*>() {
    /**
     * Identifies the server
     *
     * @param server
     * @return true
     */
    override fun identify(server: String): Boolean {
        return true
    }
}
