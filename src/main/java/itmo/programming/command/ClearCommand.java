package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;

/**
 * Команда - clear.
 * Действие - очистка коллекции.
 */
public class ClearCommand implements BaseCommand {

    ConsoleManager console;
    CollectionManager collection;

    /**
     * Конструктор.
     *
     * @param console console.
     *
     * @param collection collection.
     */
    public ClearCommand(ConsoleManager console, CollectionManager collection) {
        this.console = console;
        this.collection = collection;
    }

    /**
     * Исполнение.
     *
     * @param args args.
     */
    @Override
    public int execute(String[] args) {
        if (args.length != 0) {
            console.printErr("Команда не принимает аргументы");
            return 1;
        }
        collection.clear();
        console.println("Коллекция очищена!");
        return 0;
    }

    @Override
    public String toString() {
        return "-> чистить коллекцию";
    }
}
