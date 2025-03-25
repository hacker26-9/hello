import java.net.*;
import java.io.*;

public class udpserver {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(5000);
        byte[] receiveData = new byte[1024];
        byte[] sendData;

        System.out.println("UDP Server is running...");

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String numberStr = new String(receivePacket.getData(), 0, receivePacket.getLength());

            int number = Integer.parseInt(numberStr);
            long fact = factorial(number);  
            String result = "Factorial of " + number + " is " + fact;

            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            sendData = result.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        }
    }

    public static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
