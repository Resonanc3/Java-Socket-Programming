import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*This class is where the Runnable is implemented. It is passed through the server to handle multiple clients.
* So Whenever a new client enters. This class handles multiple clients.*/

public class ClientHandler implements Runnable {

    /*Static ArrayList of every ClientHandler object that is instantiated. It's main purpose is to keep track of all our clients
    so that whenever a client sends a message we can loop through our array list of clients and send the message to each client.*/
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    //properties for the ClientHandler
    private Socket socket; //This is passed to the server class to establish a connection between Client and Server
    private BufferedReader bufferedReader;//Read data sent from client
    private BufferedWriter bufferedWriter;//Send data to other client that is sent by a specific client
    private String clientUsername;

    //This constructor accepts the socket that is being passed by the server
    public ClientHandler(Socket socket) 
    {
        try
        {
            //Implementing this method so that this indicates that this socket is made specifically for this class.
            this.socket = socket;
            //Instantiate the BufferedReader and BufferedWriter to use for sending and receiving messages
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();//waits for the clients username to be sent over

            clientHandlers.add(this);
            broadcastMessage("[SERVER] "+clientUsername+" has entered the chat!");
        }
        //Declare a catch block in order to prevent IOException. IOException means Input Output Exception. So when an error occurs, the program will continue running.
        catch (IOException e)
        {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /*To implement the runnable, we need to override the methods. */
    @Override
    public void run()
    {
        String messageFromClient;

        while(socket.isConnected())
        {
            //Listen for a message from the client.
            try
            {
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            }
            catch(IOException e)
            {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    /*This class is responsible for sending a message to every client in the server */
    public void broadcastMessage(String messagToSend)
    {
        /*The clientHandler in the middle of the for loop  represents each client handler for each iteration through the arraylist.*/
        for (ClientHandler clientHandler : clientHandlers) 
        {
            try
            {
                /*broadcast the message to every client in the server except the client who sent the message.*/
                if (!clientHandler.clientUsername.equals(clientUsername))
                {
                    clientHandler.bufferedWriter.write(messagToSend);//Use bufferedWriter cause each clientHandler has BufferedWriter that is used to send data
                    clientHandler.bufferedWriter.newLine();//Automatically make a new line after broadcasting the message
                    clientHandler.bufferedWriter.flush();//Manually flush the message the client is sending
                }
            }
            catch (IOException e)
            {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    //This is implemented whenever a client exits the server. This will broadcast a message that to the other client, except to the client that has left.
    public void removeClientHandler()
    {
        clientHandlers.remove(this);
        broadcastMessage("SERVER: "+clientUsername+" has left the chat!");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter)
    {
        removeClientHandler();
        try
        {
            //if statement is implemented instead of if-else, because we will not know which of these three is still running or not. So
            //if one of these methods is null, then it will be ignored.
            if(bufferedReader != null)
            {
                bufferedReader.close();
            }
            if (bufferedWriter != null)
            {
                bufferedWriter.close();
            }
            if (socket != null)
            {
                socket.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
