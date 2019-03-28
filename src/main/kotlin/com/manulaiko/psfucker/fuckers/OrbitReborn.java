package com.manulaiko.psfucker.fuckers;

import com.manulaiko.psfucker.Fingerprinter;
import com.manulaiko.psfucker.Option;
import com.manulaiko.psfucker.net.HTTPClient;
import com.manulaiko.psfucker.utils.Tools;

import java.util.Map;

/**
 * BlackEye fucker
 *
 * Used for fuck BlackEye servers
 * The options for this fucket are scanner package com.manulaiko.fuckers.blackeye
 *
 * @author Manulaiko
 * @package com.manulaiko.fuckers
 */
public class OrbitReborn extends com.manulaiko.psfucker.Fucker
{
    /**
     * Constructor
     */
    public OrbitReborn(Fingerprinter fp, String server)
    {
        super(fp, "OrbitReborn", server);
        this.setOptions();
    }
    
    /**
     * Sets server options
     */
    public void setOptions()
    {
    	this.options.put("sql_injection", new com.manulaiko.psfucker.fuckers.orbitreborn.SqlInjection(this));
    }
}
