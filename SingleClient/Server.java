//In order to start a server. We must import a package. I use "*" to declare all classes inside the package

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) {
        /* The println below is just an indication if whether the server is already running.
            We then declare a final int named "Port" so that the server can connect to that specific port 
        */
        System.out.println("Server is now running at Port 3000");
        final int Port = 3000;
        try(ServerSocket serverSocket = new ServerSocket(Port))
        {
            //Waits until a client starts and requests a connection on the host an port of this Server
            Socket socket = serverSocket.accept();
            System.out.println("Client has joined the server!"); //Indication if the client has successfully connected
            
            //Creating input and output streams to the socket. Using PrintWriter and BufferedReader
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true); //The boolean "true" is an autoFlush. If true, the println, printf, or format methods will flush the output buffer
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Same Indication as the previous println
            System.out.println("Client Connected at "+Port);
            String Line = "";

            /*Communicate with the client using these methods.
            ** bufferedReader.readLine() receives data from the client
            ** printWriter.println(Line) Sends data back to the client.
            */
            while ((Line = bufferedReader.readLine())!=null)
            {
                System.out.println("Client Message: "+Line);
                printWriter.println(Line);
            }

            //Closing the socket to prevent leak
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}