package com.manulaiko.psfucker.fuckers

import com.manulaiko.psfucker.Fingerprinter
import com.manulaiko.psfucker.Fucker
import com.manulaiko.psfucker.fuckers.global.DosEmu

/**
 * Global fucker
 *
 * This fucker contains options that should work on ALL servers
 *
 * @author Manulaiko
 * @package com.manulaiko.fuckers
 */
class Global
/**
 * Constructor
 */
(fp: Fingerprinter<*>, server: String) : Fucker(fp, "Global", server) {
    init {

        this.setOptions()
    }

    /**
     * Sets server options
     */
    override fun setOptions() {
        //this.options.put("kill_apache", new KillApache(this));
        this.options
                .put("dos_emu", DosEmu(this))
    }
}
