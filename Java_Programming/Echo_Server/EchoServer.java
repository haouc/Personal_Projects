/**
 * Created by Hao on 1/12/2015.
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        System.out.println("Please enter a port number for server (suggest using 4444 for testing):");
        Scanner console = new Scanner(System.in);
        int portNumber = console.nextInt();
        ServerSocket serverSocket = new ServerSocket(portNumber);
        Socket clientSocket = serverSocket.accept();
        PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        try {
            EchoProtocol protocol = new EchoProtocol();
            String serverMessage = protocol.processInput(null);
            out.println(serverMessage);
            String clientMessage;

            while ((clientMessage = in.readLine()) != null) {
                serverMessage = protocol.processInput(clientMessage);
                out.println(serverMessage);
                if (serverMessage.equals("Bye."))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}

