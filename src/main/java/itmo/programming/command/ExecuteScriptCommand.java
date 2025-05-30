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

    /**
     * Конструктор.
     *
     * @param console console.
     * @param commandManager commandMтa manager.
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

        if (args.length == 0) {
            console.printErr("Укажите название файла");
            return 1;
        }

        final String fileName = args[0];
        final String scriptsDir = "src/main/java/itmo/programming/tests";
        File file = new File(scriptsDir, fileName);

        if (!file.exists()) {
            final String envPath = System.getenv("JAVA_PATH");
            if (envPath == null) {
                console.printErr("Файл не найден и переменная "
                        + "окружения JAVA_PATH не установлена.");
                return 1;
            } else {
                file = new File(envPath);
                console.print("Файл не найден по имени, используется"
                        + " файл из JAVA_PATH: " + file.getAbsolutePath());
            }
        }

        if (!fileManager.canReadFile(file, console)) {
            return 1;
        }

        try {
            ScriptManager.addToStack(file.getAbsolutePath());
            ScannerManager.setScanner(new Scanner(file));
            final Scanner scannerManager = ScriptManager.getLastScanner();

            if (scannerManager == null) {
                ScriptManager.removeFromStack();
                ScannerManager.setScanner(new Scanner(System.in));
                console.printErr("Ошибка: не удалось получить сканер для файла.");
                return 1;
            }

            ScannerManager.setScanner(scannerManager);
            while (scannerManager.hasNextLine()) {
                final String line = scannerManager.nextLine();
                final String[] command = line.trim().split(" ");
                if (command[0].equalsIgnoreCase("execute_script")
                        && command.length > 1) {
                    File nextScriptFile = new File(scriptsDir, command[1]);
                    if (!nextScriptFile.exists()) {
                        nextScriptFile = new File(command[1]);
                    }
                    final String absPath = nextScriptFile.getAbsolutePath(); // объявлено final
                    if (ScriptManager.isRecursive(absPath)) {
                        console.printErr("Найдена рекурсия! Повторно вызывается файл: " + new File(
                                command[1]).getAbsolutePath());
                        continue;
                    }
                }

                if (!(Objects.equals(command[0], ""))) {
                    console.println("Выполнение команды " + command[0] + " (" + fileName + "):");
                    if (commandManager.getCommands().get(command[0]) != null) {
                        final var statusCode = commandManager.executeCommand(command[0],
                                Arrays.copyOfRange(command, 1, command.length));
                        if (statusCode != 0) {
                            ScriptManager.removeFromStack();
                            return statusCode;
                        }
                    } else {
                        console.printErr("Такой команды нет");
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
            ScannerManager.setScanner(new Scanner(System.in));
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return " -> Считать и исполнить скрипт из указанного файла. "
                + "В скрипте содержатся команды в таком же виде, "
                + "в котором их вводит пользователь в интерактивном режиме.";
    }
}
