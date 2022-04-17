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

public class Client extends Thread {
    private int id;
    private int money;
    public BufferedReader bufferedReader;

    public Client() {
        id = (int) (10000 * Math.random());
        money = 0;
    } // end constructor

    public Client(Socket socket) throws IOException {
        id = (int) (10000 * Math.random());
        money = 0;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public int getID() {
        return id;
    } // end getID

    public int getMoney() {
        return money;
    } // end getMoney

    public void run() {
        while (true) {
            try {
                JSONObject json = new JSONObject(bufferedReader.readLine());
                System.out.println("[" + json.getString("username") + "]: " + json.getString("message"));
            } catch (Exception e) {
                interrupt();
                break;
            } // end catch
        } // end while
    } // end run

} // end Client