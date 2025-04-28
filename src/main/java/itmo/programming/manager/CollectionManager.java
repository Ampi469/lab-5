package itmo.programming.manager;

import itmo.programming.model.Ticket;
import itmo.programming.model.TicketType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Класс для взаимодействия с коллекцией.
 */
public class CollectionManager {

    /**
     * Время инициализации коллекции.
     */
    public Date initTime = new Date();

    /**
     * Hash-словарь для быстрого доступа по ID к объектам модели.
     */
    private final HashMap<Long, Ticket> models = new HashMap<>();


    /**
     * Коллекция.
     */
    private final PriorityQueue<Ticket> collection = new PriorityQueue<>(
            Comparator.comparingLong(Ticket::getId)
    );

    /**
     * Добавить элемент в коллекцию.
     *
     * @param ticket билет.
     */
    public void add(Ticket ticket) {
        if (isExist(ticket)) {
            return;
        }
        models.put(ticket.getId(), ticket);
        collection.add(ticket);
    }

    /**
     * Обновить по значению ID.
     *
     * @param id id.
     *
     * @param ticket билет.
     */
    public boolean updateById(Long id, Ticket ticket) {
        if (!isExist(ticket)) {
            return false;
        }
        collection.remove(findById(id));
        models.remove(id);
        models.put(id, ticket);
        collection.add(ticket);
        return true;
    }

    /**
     * Удалить по значению ID.
     *
     * @param id id.
     */
    public boolean removeById(Long id) {
        final var ticket = findById(id);
        if (ticket == null) {
            return false;
        }
        models.remove(ticket.getId());
        collection.remove(ticket);
        return true;
    }

    /**
     * Очистить коллекцию.
     */
    public boolean clear() {
        initTime = new Date();
        collection.clear();
        return true;
    }

    /**
     * Метод для вывода элементов коллекции.
     * В порядке возрастания их типов.
     */
    public void orderedEnum() {
        final CollectionManager collectionManager
                = new CollectionManager();
        final TicketType[] orderedTypes = { TicketType
                .VIP, TicketType
                .USUAL, TicketType
                .BUDGETARY, TicketType
                .CHEAP };
        final List<Ticket> tickets = new ArrayList<>(collectionManager.getCollection());
        tickets.addAll(collectionManager.getCollection());
        for (TicketType type : orderedTypes) {
            System.out.println("Элементы типа: " + type);
            tickets.stream()
                    .filter(ticket -> ticket.getTicketType() == type)
                    .sorted(Comparator.comparing(Ticket::getId))
                    .forEach(System.out::println);
            System.out.println();

        }
    }

    /**
     * Вывести первый элемент коллекции.
     */
    public Ticket firstElement() {
        return collection.peek();
    }

    /**
     * Метод для получения элементов коллекции в формате строки.
     */
    public String getCollectionAsString() {
        StringBuilder collectionString = new StringBuilder();
        if (!collection.isEmpty()) {
            for (Ticket object : collection) {
                collectionString.append(object);
                collectionString.append("\n");
            }
        } else {
            collectionString = new StringBuilder("Нечего выводить...\n");
        }
        return collectionString.toString();
    }

    /**
     * Удалить все элементы, превышающие заданный.
     *
     * @param ticket билет.
     */
    public String removeLower(Ticket ticket) {

        final List<Long> keys = new ArrayList<>(models.keySet());
        Collections.sort(keys);

        long expected = keys.get(0);
        Long freeSpot = null;

        for (Long key : keys) {
            if (!key.equals(expected)) {
                freeSpot = key;
                break;
            }
            expected++;
        }
        if (freeSpot != null) {
            final Long finalFreeSpot = freeSpot;
            models.entrySet().removeIf(entry -> entry.getKey() < finalFreeSpot);
            return "Все элементы ниже заданного были удалены!";
        }
        return "Удалите один элемент из коллекции"
                + " чтобы исполнить команду! ";
    }

    /**
     * Получить размер коллекции.
     */
    public long getCollectionSize() {
        return collection.size();
    }

    /**
     * Get max element by person.
     */
    public Ticket maxByPerson() {
        final PriorityQueue<Ticket> queue = new PriorityQueue<>(Comparator
                .comparingDouble(Ticket::getSumPersonElement).reversed());
        queue.addAll(getCollection());
        return queue.peek();
    }

    /**
     * Удалить все элементы, меньше, чем заданный.
     *
     * @param ticket билет.
     */
    public String removeGreater(Ticket ticket) {

        final List<Long> keys = new ArrayList<>(models.keySet());
        Collections.sort(keys);

        long expected = keys.get(0);
        Long freeSpot = null;

        for (Long key : keys) {
            if (!key.equals(expected)) {
                freeSpot = key;
                break;
            }
            expected++;
        }
        if (freeSpot != null) {
            final Long finalFreeSpot = freeSpot;
            models.entrySet().removeIf(entry -> entry.getKey() > finalFreeSpot);
            return "Все элементы выше заданного были удалены!";
        }
        return "Удалите один элемент из коллекции"
                + " чтобы исполнить команду! ";
    }

    /**
     * Вывести любой элемент из коллекции.
     * Значение поля coordinates которого является минимальным.
     */
    public Ticket minOfCoordinates() {
        final PriorityQueue<Ticket> queue = new PriorityQueue<>(Comparator
                .comparingLong(Ticket::getSumCoordinates));
        queue.addAll(getCollection());
        return queue.peek();
    }

    /**
     * Получить коллекцию.
     */
    public PriorityQueue<Ticket> getCollection() {
        return collection;
    }

    /**
     * Получение коллекции по ID.
     *
     * @param id id.
     */
    public Ticket findById(long id) {
        return models.get(id);
    }

    /**
     * Проверка существования элемента в коллекции.
     *
     * @param ticket билет.
     */
    public boolean isExist(Ticket ticket) {
        return ((ticket != null) && (findById(ticket.getId())) != null);
    }

    /**
     * Получить тип коллекции.
     */
    public String getTypeOfCollections() {
        return collection.getClass().getTypeName();
    }

}
