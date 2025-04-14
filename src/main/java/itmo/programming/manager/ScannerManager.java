package itmo.programming.manager;

import java.util.Scanner;

/**
 * Class for implementation Scanner.
 */
public class ScannerManager {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Get Scanner.
     */
    public static Scanner getScanner() {
        return scanner;
    }

    /**
     * Set scanner.
     *
     * @param scanner scanner.
     */
    public static void setScanner(Scanner scanner) {
        ScannerManager.scanner = scanner;
    }
}
