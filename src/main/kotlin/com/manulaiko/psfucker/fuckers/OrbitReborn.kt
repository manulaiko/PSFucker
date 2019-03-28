package com.manulaiko.psfucker.fuckers

import com.manulaiko.psfucker.Fingerprinter
import com.manulaiko.psfucker.Option
import com.manulaiko.psfucker.net.HTTPClient
import com.manulaiko.psfucker.utils.Tools

/**
 * BlackEye fucker
 *
 * Used for fuck BlackEye servers
 * The options for this fucket are scanner package com.manulaiko.fuckers.blackeye
 *
 * @author Manulaiko
 * @package com.manulaiko.fuckers
 */
class OrbitReborn
/**
 * Constructor
 */
(fp: Fingerprinter<*>, server: String) : com.manulaiko.psfucker.Fucker(fp, "OrbitReborn", server) {
    init {
        this.setOptions()
    }

    /**
     * Sets server options
     */
    override fun setOptions() {
        this.options
                .put("sql_injection", com.manulaiko.psfucker.fuckers.orbitreborn.SqlInjection(this))
    }
}
