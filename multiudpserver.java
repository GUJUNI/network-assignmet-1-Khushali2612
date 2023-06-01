import java.io.*;
import java.net.*;

class MultiThreadedUDPServer {
    public static void main(String[] args) {
        try {
            // Create a server socket and bind it to a specific port
            DatagramSocket serverSocket = new DatagramSocket(1234);
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                // Create a buffer to store incoming data
                byte[] buffer = new byte[1024];

                // Create a DatagramPacket to receive the incoming data
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);

                // Receive the data from the client
                serverSocket.receive(receivePacket);
                System.out.println("Client connected: " + receivePacket.getAddress() + ":" + receivePacket.getPort());

                // Create a new thread to handle the client
                ClientHandler clientHandler = new ClientHandler(serverSocket, receivePacket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private DatagramSocket serverSocket;
    private DatagramPacket receivePacket;

    public ClientHandler(DatagramSocket serverSocket, DatagramPacket receivePacket) {
        this.serverSocket = serverSocket;
        this.receivePacket = receivePacket;
    }

    public void run() {
        try {
            // Extract client information from the receive packet
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            // Process the received data
            String message = new String(receivePacket.getData()).trim();
            System.out.println("Received from client: " + message);

            // Prepare the response
            String response = "Server response: " + message.toUpperCase();
            byte[] responseData = response.getBytes();

            // Create a DatagramPacket to send the response back to the client
            DatagramPacket sendPacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);

            // Send the response to the client
            serverSocket.send(sendPacket);

            System.out.println("Response sent to client: " + clientAddress + ":" + clientPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
