import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try
        {
            int count = 0;
            System.out.println("[***SERVER LOADED AND PREPARED. READY TO ACCEPT CLIENTS***]");
            while(!serverSocket.isClosed())
            {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();

                count+=1;
                
                System.out.println("[SERVER] There are "+count+" client(s) who joined the server.");
                
            }
        }
        catch (IOException e)
        {

        }

        
    }

    public void closeServerSocket() {
        try
        {
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
        final int PORT = 1234;
        ServerSocket serverSocket = new ServerSocket(PORT);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}