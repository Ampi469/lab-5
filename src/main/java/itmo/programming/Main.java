package itmo.programming;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.CommandManager;
import itmo.programming.manager.ConsoleManager;
import itmo.programming.manager.FileManager;
import itmo.programming.manager.IdManager;

/**
 * Class main.
 */
public class Main {
    public static void main(String[] args) {
        final ConsoleManager console = new ConsoleManager();
        final CollectionManager collection = new CollectionManager();
        final CommandManager commandManager = new CommandManager(console);
        final String envPath = CommandManager.envPath;
        final FileManager fileManager = new FileManager(envPath, collection, console);
        IdManager.setCollectionManager(collection);
        commandManager.commands(fileManager, console, commandManager, collection);
    }
}
