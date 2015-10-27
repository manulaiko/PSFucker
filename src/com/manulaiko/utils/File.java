package com.manulaiko.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * File helper
 *
 * Used to read/write from files
 *
 * @author Manulaiko
 * @package com.manulaiko.utils
 */
public class File
{
    private final BufferedReader reader;

    /**
     * Constructor
     */
    public File(String path) throws FileNotFoundException
    {
        this.reader = new BufferedReader(new FileReader(path));
    }

    /**
     * Returns file contents
     */
    public String getContents()
    {
        String file = "";
        String line;
        try {
            //While the file isn't over continue
            while ((line = reader.readLine()) != null) {
                file += line;
            }
        } catch(Exception e) {
            System.out.println("Couldn't read the file!");
            System.out.println(e.getMessage());
            if(com.manulaiko.Main.DEBUG) {
                e.printStackTrace();
            }
        }

        return file;
    }
}
