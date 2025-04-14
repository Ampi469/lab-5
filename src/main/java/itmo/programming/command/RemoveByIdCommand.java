package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;

/**
 * Команда - remove_by_id.
 * Действие - вывести первый элемент в коллекции.
 */
public class RemoveByIdCommand implements BaseCommand {
    ConsoleManager console;
    CollectionManager collection;

    /**
     * Constructor.
     *
     * @param console console.
     *
     * @param collection collection.
     */
    public RemoveByIdCommand(ConsoleManager console, CollectionManager collection) {
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
        try {
            final long id = Integer.parseInt(args[0]);
            if (collection.removeById(id)) {
                console.println("Элемент по индексу " + id + " успешно удален!");
                return 0;
            } else {
                console.printErr("Элемент с индексом " + id + " не найден");
                return 1;
            }
        } catch (NumberFormatException e) {
            console.printErr("Неверный формат");
            return 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            console.printErr("В коллекции нет ни одного элемента");
            return 1;
        }
    }

    @Override
    public String toString() {
        return " -> Удалить элемент из коллекции по его id";
    }
}
