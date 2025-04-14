package itmo.programming.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import itmo.programming.date.DateAdapter;
import itmo.programming.model.Ticket;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.Scanner;


/**
 * Class for working with file.
 */
public class FileManager {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
            .registerTypeAdapter(LocalDateTime.class, new DateAdapter()).create();
    private final CollectionManager collection;
    private final ConsoleManager console;
    private final String envPath = CommandManager.jsonPath;

    /**
     * Constrictor.
     *
     * @param envPath envPath.
     *
     * @param collection collection.
     *
     * @param console console.
     */
    public FileManager(String envPath, CollectionManager collection, ConsoleManager console) {
        this.collection = collection;
        this.console = console;
    }

    /**
     * Method for checking file.
     *
     * @param file file.
     *
     * @param console console.
     */
    public boolean canReadFile(File file, ConsoleManager console) {
        if (!file.exists()) {
            console.printErr("Файл не найден");
            System.exit(1);
            return false;
        }
        if (!file.canRead()) {
            console.printErr("Файл не может быть прочитан");
            return false;
        }
        if (file.isHidden()) {
            console.printErr("Файл скрыт");
            return false;
        }
        if (!file.isFile()) {
            console.printErr("Это не файл");
            return false;
        }
        return true;
    }

    /**
     * Method for writing a collection to a file.
     *
     * @throws FileNotFoundException fileNotFoundException.
     */
    public boolean writeCollection() throws FileNotFoundException {
        final PriorityQueue<Ticket> currentCollection = collection.getCollection();

        if (envPath == null) {
            console.printErr("Переменная окружения 'JSON_PATH' не установлена!");
            return false;
        }

        final File file = new File(envPath);

        if (file.exists()) {
            try (BufferedOutputStream bufferedOutputStream =
                         new BufferedOutputStream(new FileOutputStream(file))) {

                bufferedOutputStream.write(gson.toJson(currentCollection).getBytes());
                console.println("Коллекция успешно записана в файл " + envPath);
                return true;

            } catch (IOException e) {
                console.printErr("Файл не может быть открыт");
                System.exit(1);
                return false;
            }
        } else {
            console.printErr("Файла по пути '" + envPath + "' не существует");
            System.exit(1);
            return false;
        }
    }

    /**
     * Method to read a collection from a file.
     *
     * @param collection collection.
     */
    public void readCollection(CollectionManager collection) {

        if (envPath != null) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(envPath));
                 Scanner fileScanner = new Scanner(bufferedReader)) {

                StringBuilder jsonString = new StringBuilder();
                while (fileScanner.hasNextLine()) {
                    final String line = fileScanner.nextLine().trim();
                    if (!line.isEmpty()) {
                        jsonString.append(line);
                    }
                }
                if (jsonString.isEmpty()) {
                    jsonString = new StringBuilder("[]");
                }
                if ("[]".contentEquals(jsonString)) {
                    console.printWarning("Коллекция в файле пуста!");
                }

                final Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDateTime.class,
                                (JsonDeserializer<LocalDateTime>) (
                                        json, typeOfT, context) ->
                                        LocalDateTime.parse(json.getAsString()))
                        .create();
                final Type collectionType = new TypeToken<PriorityQueue<Ticket>>() {}.getType();
                final PriorityQueue<Ticket> currentCollection =
                        gson.fromJson(jsonString.toString(), collectionType);

                if (currentCollection != null) {
                    for (Ticket ticket : currentCollection) {
                        if (ValidationManager.isValidTicket(ticket, collection)
                                && ValidationManager.isValidCoordinates(ticket)
                                && ValidationManager.isValidPerson(ticket)
                                && ValidationManager.isValidEnum(ticket)) {
                            collection.add(ticket);
                        } else {
                            console.printErr(
                                    "В файле содержится коллекция в недопустимом формате!"
                            );
                            System.exit(1);
                        }
                    }
                    console.println("Коллекция по адресу: " + envPath + " загружена!");
                } else {
                    console.printErr("В файле содержится коллекция в недопустимом формате!");
                    System.exit(1);
                }

            } catch (FileNotFoundException e) {
                console.printErr("Файла не существует");
                System.exit(1);
            } catch (JsonParseException e) {
                console.printErr("В файле нет коллекции нужного вида");
                System.exit(1);
            } catch (IOException e) {
                console.printErr("Непредвиденная ошибка");
                System.exit(1);
            } catch (NullPointerException e) {
                console.printErr("Файл не найден");
                System.exit(1);
            }

        } else {
            console.printErr("Файл не найден");
            System.exit(1);
        }
    }
}
