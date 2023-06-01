import java.io.*;
import java.net.*;

class MultiThreadedUDPClient {
    public static void main(String[] args) {
        try {
            // Create a socket
            DatagramSocket socket = new DatagramSocket();

            // Specify the server address and port
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 1234;

            // Create multiple client threads
            for (int i = 0; i < 5; i++) {
                ClientThread clientThread = new ClientThread(socket, serverAddress, serverPort, "Message " + (i + 1));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientThread extends Thread {
    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort;
    private String message;

    public ClientThread(DatagramSocket socket, InetAddress serverAddress, int serverPort, String message) {
        this.socket = socket;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.message = message;
    }

    public void run() {
        try {
            // Convert the message to bytes
            byte[] sendData = message.getBytes();

            // Create a DatagramPacket to send to the server
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress,
