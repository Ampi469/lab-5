package itmo.programming;

import java.util.Arrays;
import java.util.List;

/**
 * Class main.
 */
public class Main {
    public static void main(String[] args) {
//        final ConsoleManager console = new ConsoleManager();
//        final CollectionManager collection = new CollectionManager();
//        final CommandManager commandManager = new CommandManager(console);
//        final String envPath = CommandManager.getPath();
//        final FileManager fileManager = new FileManager(envPath, collection, console);
//        IdManager.setCollectionManager(collection);
//        commandManager.commands(fileManager, console, commandManager, collection);
        List<String> name = Arrays.asList("programming", "love");
        List<Integer> age = Arrays.asList(20, 30);

        WildCard.getWildCardElement(name);
        WildCard.getWildCardElement(age);

    }

    public class WildCard {
        public static void getWildCardElement(List<?> list) {
            for (Object o : list) {
                System.out.println(o);
            }
        }

        public <T> T Type(T value){
            return value;
        }
    }

}

