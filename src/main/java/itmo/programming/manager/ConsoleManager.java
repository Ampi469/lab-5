package itmo.programming.manager;

/**
 * Класс для взаимодействия с каналом вывода.
 */
public class ConsoleManager {

    /**
     * Вывести параметр в консоль без переноса строки.
     *
     * @param obj объект.
     */
    public void print(Object obj) {
        System.out.print(obj);
    }

    /**
     * Вывести параметр в консоль с переносом строки.
     *
     * @param obj объект.
     */
    public void println(Object obj) {
        System.out.print(obj + "\n");
    }

    /**
     * Вывод ошибки.
     *
     * @param message сообщение.
     */
    public void printErr(String message) {
        System.out.println("Ошибка: " + message + " !");
    }

    /**
     * Вывод предупреждения.
     *
     * @param message сообщение.
     */
    public void printWarning(String message) {
        System.out.println("Предупреждение: " + message);
    }

}
