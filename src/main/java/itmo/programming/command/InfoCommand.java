package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;

/**
 * Команда - info.
 * Действие - вывести первый элемент в коллекции.
 */
public class InfoCommand implements BaseCommand {
    ConsoleManager console;
    CollectionManager collection;

    /**
     * Constructor.
     *
     * @param console condole.
     *
     * @param collection collection.
     */
    public InfoCommand(ConsoleManager console, CollectionManager collection) {
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
        final String collectionElements = collection.getCollectionAsString();
        console.println("Дата инициализации коллекции - " + collection.initTime);
        console.println("Тип коллекции - " + collection.getTypeOfCollections());
        console.println("Размер коллекции - " + collection.getCollectionSize());
        console.print("Элементы коллекции: \n" + collectionElements);
        return 0;
    }

    @Override
    public String toString() {
        return " -> вывести в стандартный поток вывода информацию о коллекции "
                + "(тип, дата инициализации, количество элементов и т.д.)";
    }
}
