package com.manulaiko.psfucker

/**
 * Base interface for all servers fingerprinters
 *
 * @author Manulaiko
 */
interface Fingerprinter<T : Fucker> {
    /**
     * Starts fingerprinting the server
     *
     * @return True if server could be identified
     */
    fun identify(server: String): Boolean
}
