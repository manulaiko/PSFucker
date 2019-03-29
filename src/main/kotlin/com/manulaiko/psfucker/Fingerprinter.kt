package com.manulaiko.psfucker

/**
 * Fingerprinter interface.
 * ========================
 *
 * Base interface for all servers fingerprinters.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
interface Fingerprinter<T : Fucker> {
    /**
     * The fucker for this kind of server.
     */
    val fucker: T

    /**
     * Starts fingerprinting the server
     *
     * @return True if server could be identified
     */
    fun identify(server: String): Boolean
}
