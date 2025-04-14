package itmo.programming.model;

/**
 * Класс Coordinates модели.
 */
public class Coordinates {
    private final Integer coordinatesX;
    private final Long coordinatesY;

    /**
     * Конструктор.
     *
     * @param coordinatesX coordinatesX.
     *
     * @param coordinatesY coordinatesY.
     */
    public Coordinates(Integer coordinatesX, Long coordinatesY) {
        this.coordinatesX = coordinatesX;
        this.coordinatesY = coordinatesY;
    }

    /**
     * Получить значение поля X.
     */
    public Integer getCoordinatesX() {
        return coordinatesX;
    }

    /**
     * Получить значение поля Y.
     */
    public Long getCoordinatesY() {
        return coordinatesY;
    }

    /**
     * Получить сумму координат X и Y.
     */
    public long sumOfCoordinates() {
        return coordinatesX + coordinatesY;
    }

    @Override
    public String toString() {
        return "[" + coordinatesX + ", " + coordinatesY + "]";
    }

}
