/**
 * Created by Hao on 3/8/2015.
 */
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;


class WebServer {
    public static void main(String[] args) throws IOException {

        int i, httpPort = -1, sslPort = -1;
        String portPattern = "--serverPort=[0-9]+";
        String sslPortPattern = "--sslServerPort=[0-9]+";
        Pattern hPortPattern = Pattern.compile(portPattern);
        Pattern sPortPattern = Pattern.compile(sslPortPattern);

        for (i = 0; i < args.length; i++)
        {
            Matcher matchPort = hPortPattern.matcher(args[i]);
            if (matchPort.find())
            {
                try
                {
                    httpPort = Integer.parseInt(args[i].substring(13));
                } catch (NumberFormatException e)
                {
                    break;
                }
            }
        }

        for (i = 0; i < args.length; i++)
        {
            Matcher matchPort = sPortPattern.matcher(args[i]);
            if (matchPort.find())
            {
                try
                {
                    sslPort = Integer.parseInt(args[i].substring(16));
                } catch (NumberFormatException e)
                {
                    break;
                }
            }
        }

        System.out.println("http portNumber:" + httpPort + " SSL portNumber:" + sslPort);

        if (httpPort < 0 && sslPort < 0) {
            System.out.println("please use --serverPort option or --sslServerPort option (or both)");
            return;
        }

        if (httpPort >= 0) {
            ServerThread httpServer = new ServerThread(httpPort, "http");
            System.out.println("run a HTTP server");
            httpServer.start();
        }

        if (sslPort >= 0) {
            ServerThread sslServer = new ServerThread(sslPort, "ssl");
            System.out.println("run a HTTPS server");
            sslServer.start();
        }

        while (true) {
            String input;
            Scanner scanner = new Scanner(System.in);
            input = scanner.next();
            if (input.compareTo("exit") == 0)
                break;
        }

        System.out.println("server ends connection!");

    }

}