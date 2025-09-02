package itmo.programming.command;

/**
 * Интерфейс обычной команды.
 */
public interface BaseCommand {

    /**
     * Исполнение.
     *
     * @param args args.
     */
    int execute(String[] args);
}
