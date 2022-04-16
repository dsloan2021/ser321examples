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

import org.json.*;
import org.w3c.dom.NodeList;

class Node {
	private double money;
	private boolean leader;
	LinkedList<Client> clientList = new LinkedList<Client>();

	public Node(double money, boolean leader) {
		this.money = money;
		this.leader = leader;
	} // end constructor
}

public class Runner {
	private BufferedReader bufferedReader;
	// private LinkedList<Client> clientList = new LinkedList<Client>();
	private static LinkedList<Node> nodeList = new LinkedList<>();

	/*
	 * public Node(int money, boolean leader) {
	 * this.money = money;
	 * this.leader = leader;
	 * } // end constructor
	 */

	public static void main(String[] args) {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String port = args[0];
		double money = Double.parseDouble(args[1]);
		boolean leader = false;
		if (args[2].equals("true")) {
			leader = true;
		}
		Node node = new Node(money, leader);
		if (leader = false) {
			nodeList.add(node);
		}

		System.out.println("Port number: " + port + "\nMoney: " + money);
	} // end main

	public void getClientID() {
		try {
			System.out.println("Please enter your ID number. Enter -1 to exit.");
			while (true) {
				int enteredID = -1;
				try {
					enteredID = bufferedReader.read();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (enteredID == -1) {
					System.out.println("Goodbye!");
					break;
				} else if (clientList.size() == 0) {
					//
				} else {
					//
				} // end if
			} // end while
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		} // end catch
	} // end getClientID

	public void getClientInput() {
		try {
			System.out.println("You may now choose to credit or payback a loan. Type exit to exit.");
			while (true) {
				String message = bufferedReader.readLine();
				if (message.toLowerCase().equals("exit")) {
					System.out.println("Goodbye!");
					break;
				} else if (message.toLowerCase().equals("credit")) {
					choseCredit();
				} else if (message.toLowerCase().equals("payback")) {
					chosePayback();
				} else {
					//
				} // end if
			} // end while
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		} // end catch
	} // end getClientInput

	private void choseCredit() {
		for (int i = 0; i < nodeList.size(); i++) {
			Node n = nodeList.get(i);
			for (int j = 0; j < n.clientList.size(); j++) {
				//
			} // end for
		} // end for

		System.out.println(
				"How much money would you like to credit to the account? Entering a negative bring you to the previous menu.");
		int amount = 0;
		try {
			amount = bufferedReader.read();
			if (amount < 0) {
				System.out.println("That is not a valid amount.");
				return;
			} // end if
		} catch (Exception e) {
			e.printStackTrace();
		} // end catch
	} // end choseCredit

	private void chosePayback() {
		//
	} // end chosePayback
} // end Node
