import java.net.*;

public class multicastsender {
    public static final int PORT = 1234;
    public static final String MULTICAST_ADDRESS = "239.1.2.3"; 

    public static void main(String[] args) throws Exception {
        MulticastSocket socket = new MulticastSocket(); 
        InetAddress address = InetAddress.getByName(MULTICAST_ADDRESS);

        while (true) {
            Thread.sleep(5000); 
            String message = "Hello from Multicast Sender!";
            byte[] data = message.getBytes();

            DatagramPacket packet = new DatagramPacket(data, data.length, address, PORT);
            socket.send(packet); 
            System.out.println("Sent: " + message);
        }
    }
}
