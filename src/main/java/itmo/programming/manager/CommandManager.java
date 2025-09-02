package itmo.programming.manager;

import itmo.programming.command.AddCommand;
import itmo.programming.command.BaseCommand;
import itmo.programming.command.ClearCommand;
import itmo.programming.command.ExecuteScriptCommand;
import itmo.programming.command.ExitCommand;
import itmo.programming.command.FirstCommand;
import itmo.programming.command.HelpCommand;
import itmo.programming.command.InfoCommand;
import itmo.programming.command.MaxByPersonCommand;
import itmo.programming.command.MinByCoordinateCommand;
import itmo.programming.command.PrintFieldDescendingTypeCommand;
import itmo.programming.command.RemoveByIdCommand;
import itmo.programming.command.RemoveGreaterCommand;
import itmo.programming.command.RemoveLowerCommand;
import itmo.programming.command.SaveCommand;
import itmo.programming.command.ShowCommand;
import itmo.programming.command.UpdateIdCommand;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс для работы с командами.
 */
public class CommandManager {

    // Статические поля должны быть в начале класса
    public static final String jsonPath = System.getenv("JSON_PATH");
    public static final String envPath = System.getenv("JAVA_PATH");

    // Нестатические поля далее
    private final HashMap<String, BaseCommand> commands = new HashMap<>();
    private final ConsoleManager consoleManager;
    private ArrayList<String> usedCommands = new ArrayList<>();

    // Конструкторы идут после полей
    /**
     * Конструктор.
     *
     * @param consoleManager consoleManager.
     */
    public CommandManager(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    // Методы класса
    /**
     * Геттер для jsonPath.
     */
    public static String getPath() {
        return jsonPath;
    }

    /**
     * Все доступные команды.
     *
     * @param fileManager fileManager.
     * @param console console.
     * @param commandManager commandManager.
     * @param collection collection.
     */
    public void commands(FileManager fileManager, ConsoleManager console,
                         CommandManager commandManager, CollectionManager collection) {
        commandManager.addCommand("help",
                new HelpCommand(console, commandManager));
        commandManager.addCommand("info",
                new InfoCommand(console, collection));
        commandManager.addCommand("show",
                new ShowCommand(console, collection));
        commandManager.addCommand("add",
                new AddCommand(console, collection));
        commandManager.addCommand("update",
                new UpdateIdCommand(console, collection));
        commandManager.addCommand("remove_by_id",
                new RemoveByIdCommand(console, collection));
        commandManager.addCommand("clear",
                new ClearCommand(console, collection));
        commandManager.addCommand("save",
                new SaveCommand(console, fileManager));
        commandManager.addCommand("execute_script",
                new ExecuteScriptCommand(console, commandManager, fileManager));
        commandManager.addCommand("exit",
                new ExitCommand(console));
        commandManager.addCommand("first_element",
                new FirstCommand(console, collection));
        commandManager.addCommand("max_person",
                new MaxByPersonCommand(console, collection));
        commandManager.addCommand("min_coordinates",
                new MinByCoordinateCommand(console, collection));
        commandManager.addCommand("order_type",
                new PrintFieldDescendingTypeCommand(console, collection));
        commandManager.addCommand("remove_lower",
                new RemoveLowerCommand(console, collection));
        commandManager.addCommand("remove_greater",
                new RemoveGreaterCommand(console, collection));

        new RuntimeManager(commandManager, fileManager, collection, console).interactiveMode();
    }

    /**
     * Получить список использованных команд.
     */
    public ArrayList<String> getUsedCommands() {
        return usedCommands;
    }

    /**
     * Изменить список использованных команд.
     *
     * @param usedCommands использованные команды
     */
    public void setUsedCommands(ArrayList<String> usedCommands) {
        this.usedCommands = usedCommands;
    }

    /**
     * Добавить команду в Hash-словарь с командами.
     *
     * @param commandName commandName.
     * @param command command.
     */
    public void addCommand(String commandName, BaseCommand command) {
        commands.put(commandName, command);
    }

    /**
     * Исполнение команды и ее результат.
     *
     * @param commandName commandName.
     * @param args args.
     */
    public int executeCommand(String commandName, String[] args) {
        final BaseCommand command = commands.get(commandName);
        if (command != null) {
            final int response = command.execute(args);
            final ArrayList<String> usedCommands = getUsedCommands();
            usedCommands.add(commandName);
            setUsedCommands(usedCommands);
            return response;
        }
        consoleManager.println("Такой команды не существует!");
        return 0;
    }

    /**
     * Получить словарь с командами.
     */
    public HashMap<String, BaseCommand> getCommands() {
        return this.commands;
    }
}
