import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import org.json.*;

public class Node extends Thread {
	private int money;
	// public int owes;
	public ArrayList<Client> clientList = new ArrayList<Client>();

	public Node(int money) {
		this.money = money;
		// owes = 0;
	} // end constructor

	public int getMoney() {
		return money;
	} // end getMoney

	public void setMoney(int newAmount) {
		money = newAmount;
	} // end setMoney

	public void addClient(Client c) {
		clientList.add(c);
	} // end addClient

	public boolean checkForClientInNode(int clientID) {
		boolean inList = false;
		for (int i = 0; i < clientList.size(); i++) {
			if (clientList.get(i).getID() == clientID) {
				inList = true;
			}
		}
		return inList;
	} // end checkForClient

} // end Node