package com.manulaiko.psfucker;

/**
 * Base interface for all servers fingerprinters
 *
 * @author Manulaiko
 * @package com.manulaiko
 */
public abstract class Fingerprinter
{
    /**
     * Server fucker
     *
     * @var com.manulaiko.Fucker
     */
    public Fucker fucker = null;

    /**
     * Starts fingerprinting the server
     *
     * @return boolean True if server could be identified
     */
    public abstract boolean identify(String server);
}
