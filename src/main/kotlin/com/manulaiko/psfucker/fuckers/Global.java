package com.manulaiko.psfucker.fuckers;

import com.manulaiko.psfucker.Fingerprinter;
import com.manulaiko.psfucker.Fucker;
import com.manulaiko.psfucker.fuckers.global.DosEmu;

/**
 * Global fucker
 *
 * This fucker contains options that should work on ALL servers
 *
 * @author Manulaiko
 * @package com.manulaiko.fuckers
 */
public class Global extends Fucker
{
    /**
     * Constructor
     */
    public Global(Fingerprinter fp, String server)
    {
        super(fp, "Global", server);
        
        this.setOptions();
    }
    
    /**
     * Sets server options
     */
    public void setOptions()
    {
        //this.options.put("kill_apache", new KillApache(this));
        this.options.put("dos_emu", new DosEmu(this));
    }
}
