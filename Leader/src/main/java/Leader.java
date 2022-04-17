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

public class Leader extends Thread {
    private BufferedReader bufferedReader;
    public int owes;
    public ArrayList<Client> clientList = new ArrayList<Client>();
    public ArrayList<Node> nodeList = new ArrayList<Node>();

	private static Runner nodeLeader;
	private static ServerSocket serverSocket;

    public Leader(String portNum) throws IOException {
		serverSocket = new ServerSocket(Integer.valueOf(portNum));
	} // end constructor

    public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String port = args[0];
		int money = Integer.parseInt(args[1]);
		Node node = new Node(money);
		nodeList.add(node);

		serverSocket = new ServerSocket(8000);
		Socket sock = serverSocket.accept();

		nodeLeader = new Leader(port);
		nodeLeader.start();
	} // end main

    public void nodeAdded(Node n) {
        nodeList.add(n);
    }

    public void clientAdded(Client c) {
        clientList.add(c);
    }

    public boolean checkForClientInLeader(int clientID) {
        boolean inList = false;
        for (int i = 0; i < clientList.size(); i++) {
            if (clientList.get(i).getID() == clientID) {
                inList = true;
            }
        }
        return inList;
    } // end checkForClient

    public int getClientID() {
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
                return -1;
            } else {
                if (checkForClientInLeader(enteredID)) {
                    return enteredID;
                } else {
                    System.out.println("That ID number is not recognized. Please enter a valid number.");
                }
            } // end else
        } // end while
    } // end getClientID

    public void getClientInput(int clientID) {
        try {
            System.out.println("You may now choose to credit or payback a loan. Type exit to exit.");
            while (true) {
                String message = bufferedReader.readLine();
                if (message.toLowerCase().equals("exit")) {
                    System.out.println("Goodbye!");
                    break;
                } else if (message.toLowerCase().equals("credit")) {
                    choseCredit(clientID);
                } else if (message.toLowerCase().equals("payback")) {
                    chosePayback(clientID);
                } else {
                    System.out.println("That is not a valid input. Please try again.");
                } // end if
            } // end while
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        } // end catch
    } // end getClientInput

    private void choseCredit(int clientID) {
        int amountWanted = 0;
        int majority = 0;

        System.out.println(
                "How much money would you like to credit to the account? Entering a negative value will bring you to the previous menu.");
        try {
            amountWanted = Integer.parseInt(bufferedReader.readLine());
            if (amountWanted < 0) {
                System.out.println("Returning to the previous menu.");
                return;
            } // end if
        } catch (Exception e) {
            System.out.println("You did not enter a number.");
            e.printStackTrace();
        } // end catch

        int len = nodeList.size();
        for (int i = 0; i < len; i++) {
            Node n = nodeList.get(i);
            if (n.getMoney() < (amountWanted * 1.5)) {
                majority--;
            } else {
                if (n.checkForClientInNode(clientID)) {
                    majority--;
                } else {
                    majority++;
                } // end inner else

            } // end outer else
        } // end for

        if (majority <= 0) {
            System.out.println("You have credit from too many banks or are asking for too much money.");
        } else {
            amountWanted /= len;
            for (int i = 0; i < len; i++) {
                Node n = nodeList.get(i);
                int newAmount = n.getMoney() - amountWanted;
                n.setMoney(newAmount);
            } // end for
        } // end if

    } // end choseCredit

    private void chosePayback(int clientID) {
        int amountToGive = 0;
        int majority = 0;

        System.out.println(
                "How much money would you like to payback to the account? Entering a negative value will bring you to the previous menu.");
        try {
            amountToGive = Integer.parseInt(bufferedReader.readLine());
            if (amountToGive < 0) {
                System.out.println("Returning to the previous menu.");
                return;
            } // end if
        } catch (Exception e) {
            System.out.println("You did not enter a number.");
            e.printStackTrace();
        } // end catch

        int len = nodeList.size();
        for (int i = 0; i < len; i++) {
            Node n = nodeList.get(i);
            if (n.checkForClientInNode(clientID)) {
                majority--;
            } else {
                majority++;
            } // end inner else
        } // end for
    } // end chosePayback

} // end Leader