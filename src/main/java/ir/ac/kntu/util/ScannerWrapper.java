package ir.ac.kntu.util;

import java.io.Console;
import java.util.Scanner;

public class ScannerWrapper {
    private static ScannerWrapper instance = new ScannerWrapper();
    private Console console;
    private Scanner scanner;

    private ScannerWrapper() {
        console = System.console();
        scanner = new Scanner(System.in);
    }

    public ScannerWrapper getInstance() {
        return instance;
    }

    public int nextInt() {
        int nextInt = scanner.nextInt();
        scanner.nextLine();
        return nextInt;
    }

    public int readInt(String message) {
        System.out.print(message);
        int nextInt = scanner.nextInt();
        scanner.nextLine();
        return nextInt;
    }

    public double readDouble(String message) {
        System.out.print(message);
        double nextDouble = scanner.nextDouble();
        scanner.nextLine();
        return nextDouble;
    }

    public String readString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public String readPassword(String message) {
        if (console == null) {
            System.out.println("No console available");
            return null;
        }
        System.out.print(message);
        return String.valueOf(console.readPassword());
    }

    public void close() {
        scanner.close();
    }

}
