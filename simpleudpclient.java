import java.io.*;
import java.net.*;
class UDPClient {
    public static void main(String[] args) {
        try {
            // Create a socket
            DatagramSocket socket = new DatagramSocket();

            // Specify the server address and port
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 1234;

            // Create a message to send to the server
            String message = "Hello from client!";
            byte[] sendData = message.getBytes();

            // Create a DatagramPacket to send to the server
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

            // Send the packet to the server
            socket.send(sendPacket);
            System.out.println("Message sent to server.");

            // Create a buffer to store the server response
            byte[] receiveData = new byte[1024];

            // Create a DatagramPacket to receive the server response
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Receive the response from the server
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData());
            System.out.println("Server response: " + response);

            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
