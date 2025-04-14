package itmo.programming.manager;

/**
 * Класс для выдачи уникального ID.
 */
public class IdManager {
    private static Integer id = 1;
    private static CollectionManager collectionManager;

    /**
     * Определение коллекции в менеджере.
     *
     * @param collectionManager collectionManager.
     */
    public static void setCollectionManager(final CollectionManager collectionManager) {
        IdManager.collectionManager = collectionManager;
    }

    /**
     * Создание уникального ID.
     */
    public static Integer createId() {
        if (id != null) {
            if (collectionManager == null) {
                throw new NullPointerException("CollectionManager не инициализирован!");
            }
            while (collectionManager.findById(id) != null) {
                id++;
            }
            return id;
        }
        return 0;
    }
}
