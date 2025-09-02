package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;

/**
 * Команда - show.
 * Действие - вывести первый элемент в коллекции.
 */
public class ShowCommand implements BaseCommand {
    ConsoleManager console;
    CollectionManager collection;

    /**
     * Constructor.
     *
     * @param console console.
     *
     * @param collection collection.
     */
    public ShowCommand(ConsoleManager console, CollectionManager collection) {
        this.console = console;
        this.collection = collection;
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
            return 1;
        }
        console.print(collection.getCollectionAsString());
        return 0;
    }

    @Override
    public String toString() {
        return " -> Вывести в стандартный поток вывода все элементы "
                + "коллекции в строковом представлении";
    }
}
