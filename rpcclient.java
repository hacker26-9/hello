import java.io.*;
import java.net.*;
import java.util.Scanner;

public class rpcclient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5000);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter operation (ADD, SUB, MUL, DIV): ");
        String operation = scanner.next().toUpperCase();
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();
        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();

        output.println(operation + " " + num1 + " " + num2); // Send request
        System.out.println("Server: " + input.readLine()); // Receive response

        socket.close();
        scanner.close();
    }
}
