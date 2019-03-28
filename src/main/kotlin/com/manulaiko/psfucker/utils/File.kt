package com.manulaiko.psfucker.utils

import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.FileReader

/**
 * File helper
 *
 * Used to read/write from files
 *
 * @author Manulaiko
 * @package com.manulaiko.utils
 */
class File
/**
 * Constructor
 */
@Throws(FileNotFoundException::class)
constructor(path: String) {
    private val reader: BufferedReader

    /**
     * Returns file contents
     */
    //While the file isn't over continue
    val contents: String
        get() {
            var file = ""
            var line: String
            try {
                while ((line = reader.readLine()) != null) {
                    file += line
                }
            } catch (e: Exception) {
                println("Couldn't read the file!")
                println(e.message)
                if (com.manulaiko.psfucker.Main.DEBUG) {
                    e.printStackTrace()
                }
            }

            return file
        }

    init {
        this.reader = BufferedReader(FileReader(path))
    }
}
