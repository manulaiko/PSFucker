package com.manulaiko.fingerprinters;

import com.manulaiko.Fingerprinter;

/**
 * Fingerprinter for all servers
 *
 * Does nothing and returns true :D
 *
 * @author Manulaiko
 * @package com.manulaiko.fingerprinters
 */
public class Global extends Fingerprinter
{
    /**
     * Identifies the server
     *
     * @param server
     * @return true
     */
    public boolean identify(String server)
    {
        return true;
    }
}
