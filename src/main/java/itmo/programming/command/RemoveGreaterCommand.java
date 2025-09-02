package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;
import itmo.programming.model.Ticket;
import itmo.programming.model.create.TicketCreate;

/**
 * Команда - remove_greater.
 * Действие - вывести первый элемент в коллекции.
 */
public class RemoveGreaterCommand implements BaseCommand {
    ConsoleManager console;
    CollectionManager collection;

    /**
     * Constructor.
     *
     * @param console console.
     *
     * @param collection collection.
     */
    public RemoveGreaterCommand(ConsoleManager console, CollectionManager collection) {
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
            return 0;
        }
        if (collection.getCollectionSize() != 0) {
            final Ticket ticket = new TicketCreate(console).build();
            collection.removeGreater(ticket);
        } else {
            console.printErr("коллекция пуста");
        }
        return 1;
    }

    @Override
    public String toString() {
        return " -> Удалить из коллекции все элементы, превышающие заданный";
    }
}
