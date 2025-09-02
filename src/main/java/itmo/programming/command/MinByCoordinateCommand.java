package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;

/**
 * Команда - min_coordinates.
 * Действие - вывести первый элемент в коллекции.
 */
public class MinByCoordinateCommand implements BaseCommand {

    ConsoleManager console;
    CollectionManager collection;

    /**
     * Constructor.
     *
     * @param console console.
     *
     * @param collection collection.
     */
    public MinByCoordinateCommand(ConsoleManager console, CollectionManager collection) {
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
        if (!(collection.getCollectionSize() == 0)) {
            console.println("Элемент из коллекции, значение "
                    + "поля coordinates является минимальным: "
                    + collection.minOfCoordinates());
            return 0;
        } else {
            console.printErr("Коллекция пуста");
            return 1;
        }
    }

    @Override
    public String toString() {
        return " -> Вывести любой объект из коллекции, "
                + "значение поля coordinates которого является минимальным";
    }
}
