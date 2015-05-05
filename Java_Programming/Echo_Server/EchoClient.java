/**
 * Created by Hao on 1/12/2015.
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);

        System.out.println("Please enter an IP address for reaching server (suggest using 127.0.0.1 for testing):");
        String hostName = console.next();
        System.out.println("Please enter a port number for client (suggest using 4444 for testing):");
        int portNumber = console.nextInt();


        Socket echoSocket = new Socket(hostName, portNumber);
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(echoSocket.getInputStream()));
        try {
            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Base(Server): " + fromServer);
                if (fromServer.equals("Bye."))
                    break;

                if ((fromUser = stdIn.readLine()) != null) {
                    System.out.println("Delta One(Client): " + fromUser);
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("The host " + hostName + "can not be reached.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}
