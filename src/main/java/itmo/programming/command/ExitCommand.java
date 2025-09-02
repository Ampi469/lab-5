package itmo.programming.command;

import itmo.programming.manager.ConsoleManager;

/**
 * Команда - exit.
 * Действие - Завершить программу (без сохранения в файл).
 */
public class ExitCommand implements BaseCommand {
    ConsoleManager console;

    /**\
     *Constructor.
     *
     * @param console console.
     */
    public ExitCommand(ConsoleManager console) {
        this.console = console;
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
        System.exit(0);
        return 0;
    }

    @Override
    public String toString() {
        return " -> Завершить программу (без сохранения в файл)";
    }
}
