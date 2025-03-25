import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class tcpserver {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5000);
        System.out.println("Server is waiting for a client...");
        Socket socket = server.accept(); 
        System.out.println("Client connected!");

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        String message = input.readLine(); 
        System.out.println("Client: " + message);
        output.println("Server received: " + message);

        socket.close();
        server.close();
    }
}
