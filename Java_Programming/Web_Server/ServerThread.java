/**
 * Created by Hao on 3/8/2015.
 */

import java.io.*;
import java.net.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.logging.*;
import javax.net.ssl.*;
public class ServerThread extends Thread{

    int portNumber;
    String ServerType;

    public ServerThread(int port, String serverType){
        this.portNumber = port;
        this.ServerType = serverType;
    }

    @Override
    public void run() {
        if (ServerType.compareTo("http") == 0) {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(portNumber);
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (true) {
                try {
                    Socket connection_socket = serverSocket.accept();
                    System.out.println("HTTP server get a new connection");
                    HandleThread my_server_service = new HandleThread(connection_socket);
                    my_server_service.start();
                }catch (IOException ex) {
                    Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else {
            char[] password = "yanzhewu".toCharArray();
            String storedKey = "server.jks";
            try {
                SSLContext sslContext;
                sslContext = SSLContext.getInstance("TLS");
                KeyStore keyStore = KeyStore.getInstance("JKS");

                FileInputStream inputKey = new FileInputStream(storedKey);
                keyStore.load(inputKey, password);
                KeyManagerFactory keyManager = KeyManagerFactory.getInstance("SunX509");
                keyManager.init(keyStore, password);
                TrustManagerFactory trusted = TrustManagerFactory.getInstance("SunX509");
                trusted.init(keyStore);

                sslContext.init(keyManager.getKeyManagers(), null, null);
                SSLServerSocketFactory socketFactory = sslContext.getServerSocketFactory();
                SSLServerSocket serverSocket = (SSLServerSocket) socketFactory.createServerSocket(portNumber);

                while (true) {
                    Socket socket = serverSocket.accept();
                    System.out.println("HTTPS server get a new connection");
                    HandleThread server = new HandleThread(socket);
                    server.start();
                }

            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (KeyStoreException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CertificateException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnrecoverableKeyException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (KeyManagementException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}