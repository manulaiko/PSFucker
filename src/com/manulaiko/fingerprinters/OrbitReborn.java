package com.manulaiko.fingerprinters;

import com.manulaiko.net.HTTPClient;

/**
 * OrbitReborn fingerprinter
 *
 * @author Manulaiko
 * @package com.manulaiko.fingerprinters
 */
public class OrbitReborn extends com.manulaiko.Fingerprinter
{
    public boolean identify(String server)
    {
        HTTPClient connection = new HTTPClient(server);
        boolean controllersExists = connection.pageExists("controllers");
        if(controllersExists) {
        	return true;
        }
        
        boolean modelsViews = connection.pageExists("models");
        if(modelsViews) {
        	return true;
        }
        
        boolean viewsExists = connection.pageExists("views");
        if(viewsExists) {
        	return true;
        }
        
        boolean libsExists = connection.pageExists("lib");
        if(libsExists) {
        	return true;
        }
        
        boolean debugExists = connection.pageExists("flashAPI/debug.php");
        if(debugExists) {
        	return true;
        }
        
        boolean infoExists = connection.pageExists("flashAPI/infos.txt");
        return infoExists; //No more checks, otherwise there's a huge probability that it's a based one server
    }
}
