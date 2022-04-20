import java.io.*;
import java.net.*;

public class Client {
    //Declare a throw that indicates that the IP address of a host could not be determined.
    public static void main (String[] args) throws UnknownHostException {
        //Declare a final String and int to be used by the Socket
        final String localhost = "127.0.0.1";
        final int Port = 3000;
        try(Socket socket = new Socket(localhost, Port))
        {
            //Create an input and output to the socket for communicating with the server
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(System.in));

            String Line;

            /*while loop uses readLine method to read one line at a time from the standard input stream
            * It reads a line from BufferedReader object bufferedReader1, which is connected to the socket. 
            */
            while ((Line = bufferedReader1.readLine())!=null)
            {
                printWriter.println(Line);
                System.out.println("Client Echo: "+bufferedReader.readLine());

                //This part of code will exit the program if the client types either of the arguments in if statement.
                if(Line.equals("Exit")||Line.equals("exit")||Line.equals("EXIT"))
                {
                    break;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}