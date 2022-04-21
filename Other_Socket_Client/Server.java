import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    //Responsible for incoming connection or clients to communicate with
    private ServerSocket serverSocket;

    //Constructor to setup ServerSocket
    public Server(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;
    }

    //method in keeping the server running
    public void startServer() {
        //try-catch block to handle errors
        try
        {
            
            int count = 0;//this is nothing special. Everytime a client enters, count will increment so that it would print an int to be output in the println below.
            System.out.println("[***SERVER LOADED AND PREPARED. READY TO ACCEPT CLIENTS***]");
            //while this isn't closed this loop waits for a client to connect.
            while(!serverSocket.isClosed())
            {
                /*The accept methods waits until a client starts and requests a connection on the host and port of this server
                * The serverSocket.accept is a blocking method. It halts until a client connects. If the client does connect
                * It returns an object which can be used to communicate with the client.
                */
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected");
                //This class implements the runnable interface, accepts the socket object that accepts from the accept method.
                ClientHandler clientHandler = new ClientHandler(socket);

                //Able to give us a sequence of instructions, passing the class clienthandler
                Thread thread = new Thread(clientHandler);
                thread.start();//execute to start the thread.

                count+=1;
                
                System.out.println("[SERVER] There are "+count+" client(s) who joined the server.");
                
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        
    }

    //To avoid nested try catching, a method is created. That if an error occurs we'll just shut it down.
    public void closeServerSocket() {
        try
        {
            //Make sure the serverSocket is not null to avoid null pointer exception.
            if (serverSocket != null)
            {
                serverSocket.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main (String[] args)throws IOException {
        //Declaring which port should the server connect. Passing the port number to serverSocket.
        final int PORT = 1234;
        ServerSocket serverSocket = new ServerSocket(PORT);
        //Start and run the server by passing the serverSocket to this constructor.
        Server server = new Server(serverSocket);
        server.startServer();
    }
}