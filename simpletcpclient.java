import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String[] args) {
        try {
            // Create a socket and connect to the server
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to server.");

            // Create input and output streams for server communication
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send a message to the server
            out.println("Hello from client!");

            // Read server response
            String response = in.readLine();
            System.out.println("Server response: " + response);

            // Close the streams and socket
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
