package com.manulaiko.psfucker.utils;

import java.util.Random;
import java.util.Scanner;

/**
 * Utils class
 * 
 * Contains random shit
 * 
 * @author Manulaiko
 * @package com.manulaiko.utils
 */
public class Tools
{
    /**
     * Scanner object
     * 
     * Used o read user's input
     * 
     * @var java.util.Scanner
     */
    public static Scanner in = new Scanner(System.in);
    public static Random r;

    /**
     * Clears console
     */
    public static void clearConsole()
    {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            System.out.println("Couldn't clear console!");
            System.out.println(e.getMessage());
            if(com.manulaiko.psfucker.Main.DEBUG) {
                e.printStackTrace();
            }
        }

        System.out.println("PSFucker "+ com.manulaiko.psfucker.Main.version +" by Manulaiko");
        System.out.println("----------------------------");
    }

    /**
     * Parses a string so it can be executed through URL
     *
     * @param code String to parse
     * @return Parsed string
     */
    public static String toURL(String code) {
        return code.replace(" ", "%20")
                .replace("!", "%21")
                .replace("\"", "%22")
                .replace("#", "%23")
                .replace("$", "%24")
                .replace("%", "%25")
                .replace("&", "%26")
                .replace("\'", "%27")
                .replace("(", "%28")
                .replace(")", "%29");
    }
}