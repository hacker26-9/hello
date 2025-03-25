import java.net.*;

public class multicastreceiver {
    public static final int PORT = 1234;
    public static final String MULTICAST_ADDRESS = "239.1.2.3"; 

    public static void main(String[] args) throws Exception {
        MulticastSocket socket = new MulticastSocket(PORT);
        InetAddress address = InetAddress.getByName(MULTICAST_ADDRESS);
        NetworkInterface netIf = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());

        socket.joinGroup(new InetSocketAddress(address, PORT), netIf);
        System.out.println("Waiting for multicast messages...");

        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        while (true) {
            socket.receive(packet); 
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received from " + packet.getAddress() + ": " + message);
        }
    }
}
