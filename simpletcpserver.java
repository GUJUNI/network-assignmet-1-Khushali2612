import java.io.*;
import java.net.*;

class TCPServer {
    public static void main(String[] args) {
        try {
            // Create a server socket and bind it to a specific port
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server started. Waiting for client...");

            // Accept client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // Create input and output streams for client communication
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read client message
            String message = in.readLine();
            System.out.println("Received from client: " + message);

            // Send response back to the client
            out.println("Server response: Message received.");

            // Close the streams and sockets
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
