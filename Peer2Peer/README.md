## Purpose:
Very basic peer-2-peer for a chat. All peers can communicate with each other. 

Each peer is client and server at the same time. 
When started the peer has a serverthread in which the peer listens for potential other peers to connect.

The peer can choose to listen to other peers by setting the host:port for the peers they want to be able to send messages to them. For every one of these peers that this peer wants to listen to a thread is created and a connection established to the server (which is another peer).

Then chatting can start if everyone did this. 

Client Thread constantly listens.

ServerThread writes every registered listener (the other peers). 
</br></br></br>



## How to run it

Arguments are name and port. Start 2 to many peers each having a unique port number. 

gradle runPeer --args "Name 7000" --console=plain -q

When asked who "> Who do you want to listen to? Enter host:port"
enter in one line all the host:port combination you want to listen to, e.g.
localhost:8000 localhost:8001

You will then be listening to these two peers only. You cannot change who you listen to, 
you would need to start again. If you enter wrong info the program quits. 
I know userfriendly, feel free to change that if you like :-)
</br></br></br>

## From Submission page

1. Come up with a way, that a new node can be added at any time and will automatically 
	register with the other nodes (more explanation later)

2. A node can recognize if another node is not responding anymore (offline) and let the
	other nodes know that that peer is gone


When a new node is started, it should contact one of the already existing nodes. You can
assume that you give the host and port of one already running node to the gradle task so
that the new node can contact this node to "say hi".

It is for you to figure out or come up with a way you like so that this new node is then
integrated into the network.

Describe in your Readme.md for this task what you decided to do.
</br></br></br>



## Tips (for this and activity 2):

add a bunch of print statements

draw out the communications that should be made
