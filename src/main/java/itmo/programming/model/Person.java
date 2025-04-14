package itmo.programming.model;

/**
 * Класс Person модели.
 */
public class Person {
    private final long height;
    private final double weight;
    private final String passportId;

    /**
     * Конструктор.
     *
     * @param height height.
     *
     * @param weight weight.
     *
     * @param passportId passportId.
     */
    public Person(long height, double weight, String passportId) {
        this.height = height;
        this.weight = weight;
        this.passportId = passportId;
    }


    /**
     * Получить значение поля height.
     */
    public long getHeight() {
        return height;
    }

    /**
     * Получить значение поля passportID.
     */
    public String getPassportId() {
        return passportId;
    }

    /**
     * Получить сумму элементов класса Person.
     */
    public double sumPersonElement() {
        return height + weight;
    }

    @Override
    public String toString() {
        return "(" + height + ", " + weight + ", " + passportId + ")";
    }
}
