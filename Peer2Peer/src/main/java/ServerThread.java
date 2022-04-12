import java.io.IOException;
//import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * SERVER
 * This is the ServerThread class that has a socket where we accept clients
 * contacting us.
 * We save the clients ports connecting to the server into a List in this class.
 * When we wand to send a message we send it to all the listening ports
 */

public class ServerThread extends Thread {
	private ServerSocket serverSocket;
	private Set<Socket> listeningSockets = new HashSet<Socket>();

	public ServerThread(String portNum) throws IOException {
		serverSocket = new ServerSocket(Integer.valueOf(portNum));
	} // end constructor

	/**
	 * Starting the thread, we are waiting for clients wanting to talk to us, then
	 * save the socket in a list
	 */
	public void run() {
		try {
			while (true) {
				Socket sock = serverSocket.accept();
				listeningSockets.add(sock);
				System.out.println(listeningSockets.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // end catch
	} // end run

	/**
	 * Sending the message to the OutputStream for each socket that we saved
	 */
	void sendMessage(String message) {
		try {
			for (Socket s : listeningSockets) {
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				out.println(message);
			} // end for
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end sendMessage
} // end ServerThread class

/*
 * 1. Come up with a way, that a new node can be added at any time and will
 * automatically register with the other nodes (more explanation later)
 * 
 * 2. A node can recognize if another node is not responding anymore (offline)
 * and let the other nodes know that that peer is gone
 */