import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.*;

/**
 * Client
 * This is the Client thread class, there is a client thread for each peer we
 * are listening to.
 * We are constantly listening and if we get a message we print it.
 */

public class ClientThread extends Thread {
	private BufferedReader bufferedReader;

	public ClientThread(Socket socket) throws IOException {
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	} // end constructor

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
} // end ClientThread class

/*
 * 1. Come up with a way, that a new node can be added at any time and will
 * automatically register with the other nodes (more explanation later)
 * 
 * 2. A node can recognize if another node is not responding anymore (offline)
 * and let the other nodes know that that peer is gone
 */