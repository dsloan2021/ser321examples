import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.lang.Math;

import org.json.*;

public class Client {
    private int clientID;
	private BufferedReader bufferedReader;
    
    public Client() {
        clientID = (int) (10000 * Math.random());
    } // end constructor
} // end Client
