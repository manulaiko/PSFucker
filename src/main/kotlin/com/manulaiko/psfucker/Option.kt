package com.manulaiko.psfucker

/**
 * Option class
 *
 * This class will be the base of the fucker options.
 *
 * Each option of the fucker must extend this class
 *
 * @author Manulaiko
 * @package com.manulaiko
 */
abstract class Option
/**
 * Constructor
 */
(
        /**
         * Fucker
         */
        var fucker: Fucker) {

    /**
     * Description of the option
     */
    var description: String? = null

    /**
     * Fuck method
     *
     * It will fuck the server with this option
     */
    abstract fun fuck()
}
