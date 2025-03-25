import java.io.*;
import java.net.*;

public class rpcserver {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5000);
        System.out.println("RPC Server is running...");
        Socket socket = server.accept();
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        String request = input.readLine(); // Read request
        String[] parts = request.split(" "); // Format: operation number1 number2
        String operation = parts[0];
        double num1 = Double.parseDouble(parts[1]);
        double num2 = Double.parseDouble(parts[2]);
        double result = 0;

        switch (operation) {
            case "ADD": result = num1 + num2; break;
            case "SUB": result = num1 - num2; break;
            case "MUL": result = num1 * num2; break;
            case "DIV": result = (num2 != 0) ? num1 / num2 : Double.NaN; break;
            default: output.println("Invalid Operation"); return;
        }

        output.println("Result: " + result);
        socket.close();
        server.close();
    }
}
