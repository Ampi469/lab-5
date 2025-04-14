package itmo.programming.command;

import itmo.programming.manager.ConsoleManager;
import itmo.programming.manager.FileManager;
import java.io.FileNotFoundException;

/**
 * Команда - save.
 * Действие - вывести первый элемент в коллекции.
 */
public class SaveCommand implements BaseCommand {
    ConsoleManager console;
    FileManager fileManager;

    /**
     * Constructor.
     *
     * @param console console.
     */
    public SaveCommand(ConsoleManager console) {
        this.console = console;
    }

    /**
     * Execution.
     *
     * @param args args.
     */
    @Override
    public int execute(String[] args) {
        if (args.length != 0) {
            console.printErr("Команда не принимает аргументы");
            return 2;
        }
        try {
            fileManager.writeCollection();
            return 0;
        } catch (FileNotFoundException e) {
            console.printErr("Файл не найден!");
            return 1;
        }
    }

    @Override
    public String toString() {
        return " -> Сохранить коллекцию в файл";
    }
}
