//import java.io.*;
//import java.net.*;
//
//public class HTTPRequest extends Thread{
//
//    private final Socket clientSocket;
//
//    public HTTPRequest(Socket clientSocket) throws IOException{
//        this.clientSocket = clientSocket;
//    }
//
//
//    public void run() {
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {
//
//            // Read request line
//            String url = reader.readLine();
//            if (url == null) {
//                return; // Client disconnected abruptly
//            }
//
//            String[] request = url.split(" ");
//            String method = request[0];
//            String path = request[1];
//            String protocol = request[2];
//
//
//            // Handle request in a separate method
//            handleRequest(method, path, writer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                clientSocket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.err.println("Client connection closed");
//        }
//    }
//
//    private void handleRequest(String method, String path, PrintWriter writer) throws IOException {
//        String filePath = "/home/laksh.nagar/IdeaProjects/HttpProject/files";
//        String actualPath = filePath+path;
//        String clrf ="\r\n";
//
//        writer.print("HTTP/1.1 200 OK"+clrf);
//        writer.print("Content-Type: text/html"+clrf);
//        writer.println("Content-Type: image/jpg"+clrf);
//        writer.println("Content-length"+actualPath.length()+clrf);
//
//        if (method.equalsIgnoreCase("GET")) {
//            File resource = new File(actualPath);
//            if (!resource.exists()) {
//                return;
//            }
//
//            try (InputStream resourceInputStream = new FileInputStream(resource)) {
//                byte[] inputBytes = new byte[1024];
//                while (resourceInputStream.read(inputBytes) > 0) {
//                    clientSocket.getOutputStream().write(inputBytes);
//                }
//            }
//        } else {
//            // Handle other methods (optional)
//            writer.println("HTTP/1.1 501 Not Implemented");
//            writer.println("Content-Type: text/plain");
//            writer.println();
//            writer.println("Error: Method not supported");
//        }
//    }
//}