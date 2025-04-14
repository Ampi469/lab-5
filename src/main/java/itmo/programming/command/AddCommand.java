package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;
import itmo.programming.model.Ticket;
import itmo.programming.model.create.TicketCreate;

/**
 * Команда - Add.
 * Действие - создать е=новый элемент.
 */
public class AddCommand implements BaseCommand {
    ConsoleManager console;
    CollectionManager collection;

    /**
     * Конструктор.
     *
     * @param console console.
     *
     * @param collection collection.
     */
    public AddCommand(ConsoleManager console, CollectionManager collection) {
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
        final Ticket ticket = new TicketCreate(console).build();
        collection.add(ticket);
        console.println("Элемент добавлен в коллекцию");
        return 0;
    }

    @Override
    public String toString() {
        return " -> Добавить новый элемент в коллекцию";
    }
}
