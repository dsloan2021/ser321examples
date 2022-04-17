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
	public ArrayList<ClientOwesNode> clientOwesNodes = new ArrayList<ClientOwesNode>();

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

		ClientOwesNode con = new ClientOwesNode(c.getID(), 0);
		clientOwesNodes.add(con);
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

	public int getOwedAmount(int clientID) {
		for (int i = 0; i < clientOwesNodes.size(); i++) {
			if (clientOwesNodes.get(i).getClientID() == clientID) {
				return clientOwesNodes.get(i).getMoneyOwed();
			}
		}
		return 0;
	}

	class ClientOwesNode {
		private int clientID;
		private int moneyOwed;

		public ClientOwesNode(int clientID, int moneyOwed) {
			this.clientID = clientID;
			this.moneyOwed = moneyOwed;
		}

		public int getClientID() {
			return clientID;
		}

		public int getMoneyOwed() {
			return moneyOwed;
		}

		public void setClientID(int id) {
			clientID = id;
		}

		public void setMoneyOwed(int owed) {
			moneyOwed = owed;
		}
	}

} // end Node