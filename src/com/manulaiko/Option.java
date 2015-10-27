package com.manulaiko;

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
public abstract class Option
{
    /**
     * Fucker
     */
    public Fucker fucker;

    /**
     * Description of the option
     */
    public String description;

    /**
     * Constructor
     */
    public Option(Fucker fucker)
    {
        this.fucker = fucker;
    }

    /**
     * Fuck method
     *
     * It will fuck the server with this option
     */
    public abstract void fuck();
}
