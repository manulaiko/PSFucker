package com.manulaiko.psfucker;

import com.manulaiko.psfucker.utils.*;
import java.util.*;
import java.util.Map.*;

/**
 * PSFucker main class
 * 
 * App's entry point, contains `static void main(String[] args)`
 * 
 * @author Manulaiko
 * @package com.manulaiko
 */
public class Main
{
    /**
     * Version
     * 
     * @var String
     */
    public static String version = "v0.1.0";
    
    /**
     * Server ip
     * 
     * @var String
     */
    public static String server = "";

    /**
     * Fingerprinters
     *
     * @var Map
     */
    public static Map<String, Fingerprinter> fingerprinters = new TreeMap<String, Fingerprinter>();

    /**
     * Flag to indicate if we're running or not on debug version
     *
     * @var boolean
     */
    public static final boolean DEBUG = true;
    
    /**
     * Main method
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args)
    {
        System.out.println("PSFucker "+ version +" by Manulaiko");
        System.out.println("----------------------------");

        System.out.println("Loading fingerprinters...");
        loadFingerprinters();
        System.out.println(fingerprinters.size() + " servers loaded!");

        start();
    }

    /**
     * Loads fingerprinters
     */
    public static void loadFingerprinters()
    {
        fingerprinters.put("OrbitReborn", new com.manulaiko.psfucker.fingerprinters.OrbitReborn());
        fingerprinters.put("Global", new com.manulaiko.psfucker.fingerprinters.Global());
    }

    /**
     * Fingerprints the server
     *
     * Will try to fingerprint the server, if it couldn't be fingerprinter will come back
     * to ask to server address.
     *
     * @param server Server to fingerprint
     * @return Fingerprinter object
     */
    public static Fingerprinter fingerprint(String server)
    {
        Map<String, Fingerprinter> matches = new TreeMap<String, Fingerprinter>();

        for(Entry<String, Fingerprinter> fingerprint : fingerprinters.entrySet()) {
            if(fingerprint.getValue().identify(server)) {
                matches.put(fingerprint.getKey(), fingerprint.getValue());
            }
        }

        if(matches.size() <= 0) {
            System.out.println("Couldn't identify server :(");
            try {
                Thread.sleep(300);
            } catch(Exception e) { }
            Tools.clearConsole();
            start();
        }

        System.out.println(matches.size()+" matches found!");
        System.out.println("Choose which fucker you want to use from the list:");
        for(Entry<String, Fingerprinter> e : matches.entrySet()) {
            System.out.println(" -"+e.getKey()+" based server fucker");
        }
        System.out.print("Enter the name of the server: ");
        String match = Tools.in.nextLine();

        if(matches.containsKey(match)) {
            return matches.get(match);
        } else {
            System.out.println("You entered a wrong value!");
            System.out.println("Please, choose one of the following servers... let's see if this time you can do it");
            try {
                Thread.sleep(300);
            } catch(Exception e) { }
            Tools.clearConsole();
            fingerprint(server);
        }

        return null;
    }
    
    /**
     * Starts PSFucker
     *
     * Ask for the server, fingerprints the server and fucks the server
     *
     * Once server has been fucked it will ask for the server again
     */
    public static void start()
    {
        System.out.print("Enter server IP: ");
        server = Tools.in.nextLine();

        Tools.clearConsole();
        System.out.println("Fingerprinting server...");
        Fingerprinter fServer = fingerprint(server);
        if(fServer == null) {
            System.out.println("Couldn't fingerprint server!");
            System.out.print("Do you wanna try with any of the servers available? (y/n");
            if(Tools.in.nextLine().charAt(0) == 'y') {
                System.out.println("Choose which fucker you want to use from the list:");
                for(Entry<String, Fingerprinter> e : fingerprinters.entrySet()) {
                    if(e.getKey().equalsIgnoreCase("global")) {
                        System.out.println(" -"+ e.getKey() +" fucker (Should work for all servers)");
                    } else {
                        System.out.println(" -"+ e.getKey() +" based server fucker");
                    }
                }
                System.out.print("Enter the name of the server: ");
                String match = Tools.in.nextLine();

                if(fingerprinters.containsKey(match)) {
                    fServer = fingerprinters.get(match);
                } else {
                    System.out.println("You entered a wrong value!");
                    System.out.println("Please, choose one of the following servers... let's see if this time you can do it");
                    try {
                        Thread.sleep(300);
                    } catch(Exception e) { }
                    Tools.clearConsole();
                    start();
                }
            } else {
                Tools.clearConsole();
                start();
            }
        }

        Tools.clearConsole();
        System.out.println("Fucking server "+server+"!");
        fServer.fucker.fuck();

        start();
    }
}