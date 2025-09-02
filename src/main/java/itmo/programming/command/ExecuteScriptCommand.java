package itmo.programming.command;

import itmo.programming.manager.CommandManager;
import itmo.programming.manager.ConsoleManager;
import itmo.programming.manager.FileManager;
import itmo.programming.manager.ScannerManager;
import itmo.programming.manager.ScriptManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Команда - execute_script.
 * Действие - Считать и исполнить скрипт из указанного файла.
 */
public class ExecuteScriptCommand implements BaseCommand {
    final ConsoleManager console;
    final CommandManager commandManager;
    final FileManager fileManager;
    final String envPath = CommandManager.envPath;


    /**
     * Конструктор.
     *
     * @param console console.
     *
     * @param commandManager commandMтa manager.
     *
     * @param fileManager fileManager.
     */
    public ExecuteScriptCommand(ConsoleManager console,
                                CommandManager commandManager,
                                FileManager fileManager) {
        this.console = console;
        this.commandManager = commandManager;
        this.fileManager = fileManager;
    }

    /**
     * Исполнение.
     *
     * @param args args.
     */
    @Override
    public int execute(String[] args) {

        if (envPath == null || envPath.isEmpty()) {
            console.printErr("Переменная окружения JAVA_PATH не задана или пуста!");
            return 0;
        }

        final File file = new File(envPath);
        if (!fileManager.canReadFile(file, console)) {
            return 0;
        }

        try {
            ScriptManager.addToStack(envPath);
            Scanner scannerManager;
            while ((scannerManager = ScriptManager.getLastScanner()) != null) {
                ScannerManager.setScanner(scannerManager);
                if (!scannerManager.hasNextLine()) {
                    ScriptManager.addToStack(envPath);
                    ScannerManager.setScanner(new Scanner(System.in));
                    return 0;
                }
                final String line;
                try {
                    line = scannerManager.nextLine().trim();
                } catch (NoSuchElementException eof) {
                    ScriptManager.removeFromStack();
                    ScannerManager.setScanner(new Scanner(System.in));
                    continue;
                }
                if (line.isEmpty()) {
                    continue;
                }
                final String[] command = line.trim().split(" ");
                if (command[0].equalsIgnoreCase(
                        "execute_script") && ScriptManager.isRecursive(command[1])
                ) {
                    console.printErr(
                            "Найдена рекурсия! Повторно вызывается файл: "
                                    + new File(command[1]).getAbsolutePath()
                    );
                    continue;
                }
                if (!(Objects.equals(command[0], ""))) {
                    console.println("Выполнение команды " + command[0] + " (" + envPath + "):");
                    if (commandManager.getCommands().get(command[0]) != null) {
                        final var statusCode = commandManager.executeCommand(command[0],
                                Arrays.copyOfRange(command, 1, command.length));
                        if (statusCode != 0) {
                            return statusCode;
                        }
                    } else {
                        console.printErr("Такой команды нет");
                        ScriptManager.removeFromStack();
                        ScannerManager.setScanner(new Scanner(System.in));
                        return 1;
                    }
                }
            }
            ScriptManager.removeFromStack();
            ScannerManager.setScanner(new Scanner(System.in));

        } catch (FileNotFoundException e) {
            console.printErr(e.getMessage());
            ScannerManager.setScanner(new Scanner(System.in));
            return 1;
        } catch (NoSuchElementException e) {
            console.printErr("Ошибка при работе с файлом: " + e.getMessage());
            ScriptManager.removeFromStack();
            ScannerManager.setScanner(new Scanner(System.in));
            return 1;
        }
        return 1;
    }

    @Override
    public String toString() {
        return " -> Считать и исполнить скрипт из указанного файла. "
                + "В скрипте содержатся команды в таком же виде, "
                + "в котором их вводит пользователь в интерактивном режиме.";
    }
}
