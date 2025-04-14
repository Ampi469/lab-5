package itmo.programming.command;

import itmo.programming.manager.CommandManager;
import itmo.programming.manager.ConsoleManager;

/**
 * Команда - help.
 * Действие - вывести первый элемент в коллекции.
 */
public class HelpCommand implements BaseCommand {
    ConsoleManager console;
    CommandManager command;

    /**
     * Constructor.
     *
     * @param console console.
     *
     * @param command command.
     */
    public HelpCommand(ConsoleManager console, CommandManager command) {
        this.console = console;
        this.command = command;
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
        console.println("Все доступные команды:");
        command.getCommands().forEach((name, command) -> {
            console.println(name + command.toString());
        });
        return 0;
    }

    @Override
    public String toString() {
        return " -> Вывести все доступные команды";
    }
}
