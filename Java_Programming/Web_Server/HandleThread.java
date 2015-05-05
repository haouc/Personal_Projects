/**
 * Created by Hao on 3/8/2015.
 */
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandleThread extends Thread {
    private static final String WWW_REDIRECT_DEF = "www/redirect.defs";
    private BufferedReader reader;
    private OutputStream outputStream;

    private long timestamp;
    private boolean timeToStart;

    private Socket originalSocket;

    public HandleThread(){}

    public HandleThread(Socket socket){
        timeToStart = false;
        this.originalSocket = socket;
        try {
            reader = new BufferedReader(new InputStreamReader(this.originalSocket.getInputStream()));
            outputStream = this.originalSocket.getOutputStream();
        }catch (IOException e){
               e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.originalSocket.setKeepAlive(true);
                Request request_message = receiving();
                if (request_message == null) {
                    if(timeToStart){
                        long currentTime = System.currentTimeMillis()/1000;
                        if(Math.abs(currentTime- timestamp) > 5){
                            System.out.println("Time out! Persistent socket is close");
                            this.reader.close();
                            this.outputStream.close();
                            this.originalSocket.close();
                            break;
                        }
                    }
                    else{
                        timeToStart = true;
                        timestamp = System.currentTimeMillis()/1000;
                    }
                    continue;
                }
                else {
                    timeToStart =false;
                }
                if (toClient(request_message) != 0) {
                    originalSocket.close();
                    System.out.println("toClient function error");
                    break;
                }
            }
            catch (SocketException e){
                if(e.getMessage().compareTo("Socket Closed") == 0){
                    break;
                }
                break;
            }catch (IOException e) {
                break;
            }
        }

    }

    private Request receiving() throws IOException{

        Request rm = new Request();
        String line = reader.readLine();
        if(line == null) {
            return null;
        }
        else {
            System.out.println(line);
        }
        String[] str = line.split(" ");
        rm.method=str[0];
        rm.URL=str[1];
        rm.version=str[2];

        String inputLine;
        Header hl;
        while ((inputLine = reader.readLine()).compareTo("")!=0) {
            hl = new Header(inputLine);
            rm.hl.add(hl);
        }

        return rm;
    }

    private int toClient(Request request_message) throws IOException {
        ResponseMessage response = generateReMessage(request_message);
        String cr = "\r";
        String lf = "\n";
        String output;

        output = response.version + " " + response.status + " " + response.phrase + cr + lf;
        outputStream.write(output.getBytes(), 0, output.length());
        for (Header header: response.hl) {
            output = header.fieldName + " " + header.value + cr + lf;
            outputStream.write(output.getBytes(), 0, output.length());
        }

        output = cr + lf;
        outputStream.write(output.getBytes(), 0, output.length());
        outputStream.flush();

        if (response.status.equals("200") && request_message.method.equals("GET")) {
            System.out.println("the local path for requested object is:" + response.entityPath);

            BufferedInputStream input_stream = new BufferedInputStream(new FileInputStream(new File(response.entityPath)));
            byte[] file_buff = new byte[1024];
            int read_size;
            while ((read_size = input_stream.read(file_buff, 0, file_buff.length)) > 0) {
                outputStream.write(file_buff, 0, read_size);
                outputStream.flush();
            }
            input_stream.close();
        }

        return 0;

    }

    private static ResponseMessage generateReMessage(Request request) {

        HandleThread handleThread = new HandleThread();
        ResponseMessage response = handleThread.new ResponseMessage();
        String versionPatternStr = "HTTP/[0-9]+.[0-9]+";
        Pattern versionPattern = Pattern.compile(versionPatternStr);
        Matcher versionPatternMatcher = versionPattern.matcher(request.version);


        response.version = "HTTP/1.1";
        if (!versionPatternMatcher.find()) {
            Header header_content_length = handleThread.new Header("Content-Length: 0");

            response.status = "400";
            response.phrase = "Bad Request";
            response.hl.add(header_content_length);
            response.entityPath = "";
            return response;
        }
        else if (request.method.equals("GET") || request.method.equals("HEAD")) {

            String requested_URL = local_path(request.URL);
            String redirected_path = redirection(request.URL, WWW_REDIRECT_DEF);


            if (!requested_URL.equals(WWW_REDIRECT_DEF) && fileExistence(requested_URL)) {

                Header header_content_type = handleThread.new Header("Content-Type: " + contentType(requested_URL));
                long file_size = get_file_size(requested_URL);

                Header header_content_length = handleThread.new Header("Content-Length: " + Long.toString(file_size));
                Header header_connection_keep_alive = handleThread.new Header("Connection: " + "Keep-Alive");

                response.status = "200";
                response.phrase = "OK";
                response.hl.add(header_content_type);
                response.hl.add(header_content_length);
                response.hl.add(header_connection_keep_alive);
                if (request.method.equals("GET"))
                    response.entityPath = requested_URL;
                else
                    response.entityPath = "";
                return response;
            }
            else if (!requested_URL.equals(WWW_REDIRECT_DEF) && redirected_path.length() > 0) {
                Header header_location = handleThread.new Header("Location: " + redirected_path);

                response.status = "301";
                response.phrase = "Moved Permanently";
                response.hl.add(header_location);

                if (request.method.equals("GET")) {
                    Header header_content_length = handleThread.new Header("Content-Length: 0");
                    response.hl.add(header_content_length);
                }
                else {
                    Header header_content_length = handleThread.new Header("Content-Length: 0");
                    response.hl.add(header_content_length);
                }
                return response;
            }
            else {
                Header header_content_length = handleThread.new Header("Content-Length: 0");

                response.status = "404";
                response.phrase = "Not Found";
                response.hl.add(header_content_length);
                response.entityPath = "";
                return response;
            }
        }
        else {
            Header header_content_length = handleThread.new Header("Content-Length: 0");

            response.status = "403";
            response.phrase = "Forbidden";
            response.hl.add(header_content_length);
            response.entityPath = "";
        }

        return response;
    }

    private static String redirection(String requestedURL, String file) {
        String redirection = "";
        File redirectFile = new File(file);
        if (redirectFile.exists()) {

            try {

                BufferedReader br = new BufferedReader(new FileReader(file));
                String redirectStr;

                while ((redirectStr = br.readLine()) != null)
                {
                    String[] rePath = redirectStr.split(" ");
                    if (requestedURL.equals(rePath[0])) {
                        redirection = rePath[1];
                    }
                }

                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(WebServer.class.getName()).log(Level.SEVERE, null, ex);
                    return redirection;
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(WebServer.class.getName()).log(Level.SEVERE, null, ex);
                return redirection;
            } catch (IOException ex) {
                Logger.getLogger(WebServer.class.getName()).log(Level.SEVERE, null, ex);
                return redirection;
            }
            return redirection;
        }
        else {
            return redirection;
        }
    }

    private static boolean fileExistence(String filePath) {
        File checkedFile = new File(filePath);
        return checkedFile.exists() && checkedFile.isFile();
    }

    private static String contentType(String filePath) {
        int i = filePath.length() - 1;
        String suffix;

        for (; i > 0; i--) {
            if (filePath.charAt(i) == '.')
                break;
        }

        suffix = filePath.substring(i + 1);

        if (suffix.equals("html") || suffix.equals("hml")) {
            return "text/html";
        }
        else if (suffix.equals("pdf")) {
            return "application/pdf";
        }
        else if (suffix.equals("png")) {
            return "image/png";
        }
        else if (suffix.equals("jpeg") || suffix.endsWith("jpg")) {
            return "image/jpeg";
        }
        else
            return "text/plain";
    }

    private static long get_file_size(String filePath) {
        File file = new File(filePath);
        return file.length();
    }

    static private String local_path(String URL) {
        if (URL.equals("/") || URL.equals(""))
            return "www/index.html";
//        else if (URL == null)
//            return "";
        else if (URL.charAt(0) != '/')
            return "www/" + URL;
        else
            return "www" + URL;
    }

    public class Request {
        public String
                method,
                URL,
                version;
               // entity_body;
        public ArrayList<Header> hl = new ArrayList<Header>();
    }

    public class Header {
        public String fieldName;
        public String value;
        Header(String line){
            String[] str = line.split(" ");
            fieldName = str[0];
            value = str[1];
        }
    }

    public class ResponseMessage {
        public String version, status, phrase;
        public ArrayList<Header> hl = new ArrayList<Header>();
        public String entityPath;
    }
}
