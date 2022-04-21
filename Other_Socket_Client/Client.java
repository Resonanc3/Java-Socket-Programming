import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;


/*This is the class that is responsible for runing the client. The client is the user that connects to the server and then communicates
with other clients if this file is executed again. If this file is executed again. A new thread will be created so that the server can handle
multiple clients. */
public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;


    public Client(Socket socket, String username) 
    {
        try
        {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
        }
        catch (IOException e)
        {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    //This constructor is responsible for sending message to the clients.
    public void sendMessage() 
    {
        try
        {
            bufferedWriter.write(username);//writes the username so the buffer clienthandler know who we are.
            bufferedWriter.newLine();//Automatically makes a newline when sending message
            bufferedWriter.flush();//Manually flush the messages the client is sending.

            Scanner scanner = new Scanner(System.in);//get input from the console
            while(socket.isConnected())
            {
                String messageTosend = scanner.nextLine();
                bufferedWriter.write(username + ": " + messageTosend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

            scanner.close();
        }
        catch (IOException e)
        {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    //This is where we use a new thread
    public void listenForMessage()
    {
        //Create a new thread and pass a runnable. Every runnable has Override with it.
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                String msgFromGroupChat;

                //The thread will be running, so while it is connected to the server it reads the broadcast message and then output it
                while(socket.isConnected())
                {
                    try
                    {
                        msgFromGroupChat = bufferedReader.readLine();
                        System.out.println(msgFromGroupChat);
                    }
                    catch (IOException e)
                    {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try
        {
            if (bufferedReader != null)
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

    public static void main(String[] args) throws UnknownHostException
    {
        final String localhost = "127.0.0.1";
        final int PORT = 1234;

        //Take input from the console
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username for the group chat: ");
        String username = scanner.nextLine();//reads the input, this will be the client's username
        
        //Connect to the server, the same port as where the server is listening
        try(Socket socket = new Socket(localhost, PORT))
        {
            Client client = new Client(socket, username); //Instantiate the class made above
            client.listenForMessage();//run these two blocking methods to be able to send and listen for incoming messages
            client.sendMessage();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        scanner.close(); //close scanner to avoid leaks.
    }
}