package com.manulaiko.fuckers;

import com.manulaiko.Fingerprinter;
import com.manulaiko.Option;
import com.manulaiko.net.HTTPClient;
import com.manulaiko.utils.Tools;

import java.util.Map;

/**
 * BlackEye fucker
 *
 * Used for fuck BlackEye servers
 * The options for this fucket are in package com.manulaiko.fuckers.blackeye
 *
 * @author Manulaiko
 * @package com.manulaiko.fuckers
 */
public class OrbitReborn extends com.manulaiko.Fucker
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
    	this.options.put("sql_injection", new com.manulaiko.fuckers.orbitreborn.SqlInjection(this));
    }
}
