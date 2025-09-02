package itmo.programming.manager;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class for implementing interactive mode.
 */
public class RuntimeManager {
    private boolean save = true;
    private final CommandManager commandManager;
    private final FileManager fileManager;
    private final CollectionManager collectionManager;
    private final ConsoleManager consoleManager;

    /**
     * Constructor.
     *
     * @param commandManager commandManager.
     *
     * @param fileManager fileManager.
     *
     * @param collectionManager collectionManager.
     *
     * @param consoleManager consoleManager.
     */
    public RuntimeManager(CommandManager commandManager,
                          FileManager fileManager,
                          CollectionManager collectionManager,
                          ConsoleManager consoleManager) {
        this.commandManager = commandManager;
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
        this.consoleManager = consoleManager;
    }

    /**
     * Method implementing interactive mode.
     */
    public void interactiveMode() {

        final Scanner scanner = ScannerManager.getScanner();
        fileManager.readCollection(collectionManager);
        consoleManager.println("Напишите help, если не знаете написание команд"
                + " (либо если не знаете что вообще написать)");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (save) {
                try {
                    if (fileManager.writeCollection()) {
                        consoleManager.println("Данные успешно сохранены перед выходом.");
                    } else {
                        consoleManager.printWarning("Данные не сохранены (Файл не загружен)");
                    }
                } catch (FileNotFoundException e) {
                    consoleManager.printErr("Ошибка при сохранении данных");
                }
            }

        }));
        while (true) {
            try {
                final String[] userCommand = scanner.nextLine().trim().split(" ");
                if ("exit".equalsIgnoreCase(userCommand[0])) {
                    save = false;
                    commandManager.executeCommand(userCommand[0].toLowerCase(),
                            Arrays.copyOfRange(userCommand, 1, userCommand.length));
                    System.exit(0);
                } else {
                    commandManager.executeCommand(userCommand[0].toLowerCase(),
                            Arrays.copyOfRange(userCommand, 1, userCommand.length));
                }
            } catch (NoSuchElementException e) {
                System.exit(0);
            }
        }

    }
}
