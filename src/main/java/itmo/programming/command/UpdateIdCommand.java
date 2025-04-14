package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;
import itmo.programming.model.Ticket;
import itmo.programming.model.create.TicketCreate;

/**
 * Команда - update.
 * Действие - вывести первый элемент в коллекции.
 */
public class UpdateIdCommand implements BaseCommand {
    ConsoleManager console;
    CollectionManager collection;

    /**
     * Constrictor.
     *
     * @param console console.
     *
     * @param collection collection.
     */
    public UpdateIdCommand(ConsoleManager console, CollectionManager collection) {
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
            console.printErr("Коллекция не принимает аргументы");
            return 1;
        }
        try {
            final long id = Long.parseLong(args[0]);
            if (collection.findById(id) == null) {
                console.printErr("Элемента с таким индексом не существует!");
                return 1;
            } else {
                final Ticket ticket = new TicketCreate(console).update(id);
                collection.updateById(id, ticket);
                console.println("Значение элемента по id: " + args[0] + " обновлено!");
                return 0;
            }
        } catch (NumberFormatException e) {
            console.printErr("Неверный формат! Введите число (id)");
            return 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            console.printErr("В коллекции нет ни одного элемента");
            return 1;
        }
    }

    @Override
    public String toString() {
        return " -> обновить элемент из коллекции по его id";
    }
}
