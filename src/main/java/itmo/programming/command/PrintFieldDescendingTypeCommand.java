package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;

/**
 * Команда - order_type.
 * Действие - вывести первый элемент в коллекции.
 */
public class PrintFieldDescendingTypeCommand implements BaseCommand {
    ConsoleManager console;
    CollectionManager collection;

    /**
     * Constructor.
     *
     * @param console console.
     *
     * @param collection collection.
     */
    public PrintFieldDescendingTypeCommand(ConsoleManager console,
                                           CollectionManager collection) {
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
            console.printErr("Команда не принимает аргументов");
            return 1;
        }
        if (!(collection.getCollectionSize() == 0)) {
            collection.orderedEnum();
            return 0;
        } else {
            console.printErr("Коллекция пуста");
            return 1;
        }
    }

    @Override
    public String toString() {
        return " -> Вывести значения поля type всех элементов в порядке убывания";
    }
}
