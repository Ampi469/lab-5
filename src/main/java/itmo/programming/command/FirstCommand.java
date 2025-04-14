package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;

/**
 * Команда - first_element.
 * Действие - вывести первый элемент в коллекции.
 */
public class FirstCommand implements BaseCommand {

    ConsoleManager console;
    CollectionManager collection;

    /**
     * Constructor.
     *
     * @param console console.
     *
     * @param collection collection.
     */
    public FirstCommand(ConsoleManager console, CollectionManager collection) {
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
        if (collection.getCollection() == null) {
            console.println("Первый элемент коллекции: "
                    + collection.firstElement());
            return 0;
        } else {
            console.printErr("Коллекция пуста");
            return 1;
        }
    }

    @Override
    public String toString() {
        return " -> Вывести первый элемент коллекции";
    }
}
