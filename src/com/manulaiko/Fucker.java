package com.manulaiko;

import java.util.Map;
import java.util.TreeMap;

import com.manulaiko.utils.Tools;

/**
 * Base interface for all servers fuckers
 *
 * @author Manulaiko
 * @package com.manulaiko
 */
public abstract class Fucker
{
    /**
     * Server fingerprinter
     *
     * @var com.manulaiko.Fingerprinter
     */
    public Fingerprinter fingerprinter = null;

    /**
     * Options that the fucker provides
     *
     * The key is the name of the option that user should send as input
     * and the value is an Option object containing the fuck method
     */
    public Map<String, Option> options = new TreeMap<String, Option>();

    /**
     * Server address
     */
    public String server = "";

    /**
     * Server name
     */
    public String name = "";

    /**
     * Constructor
     * @param fp
     * @param server
     */
    public Fucker(Fingerprinter fp, String name, String server)
    {
        this.fingerprinter = fp;
        this.name = name;
        this.server = server;
    }

    /**
     * Fucks the server itself
     */
    public void fuck()
    {
        String option = "undefined";

        while(!option.equalsIgnoreCase("exit") || !option.equalsIgnoreCase("quit")) {
            System.out.println("Fucking "+this.name+" based server");
            System.out.println("Choose something from the list:");

            int i = 0;
            for(Map.Entry<String, Option> opt : this.options.entrySet()) {
                i++;
                System.out.println(" "+i+") "+opt.getKey()+": "+opt.getValue().description);
            }
            System.out.println(" "+(++i)+") exit: Stops fucking the server");

            System.out.print("What you do you want to do? ");
            option = Tools.in.nextLine();

            if(option.equalsIgnoreCase("exit")) {
                return;
            }

            for(Map.Entry<String, Option> opt : this.options.entrySet()) {
                if(opt.getKey().equalsIgnoreCase(option)) {
                    opt.getValue().fuck();
                }
            }
        }
    }
    
    /**
     * Sets server options
     */
    public abstract void setOptions();
}
