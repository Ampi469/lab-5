package itmo.programming.model.create;

import itmo.programming.manager.ConsoleManager;
import itmo.programming.model.Coordinates;
import java.util.Objects;

/**
 * Класс для запрашивания и заполнения полей Coordinate и для дальнейшей сборки экземпляров модели.
 */
public class CoordinatesCreate extends Create<Coordinates> {
    /**
     * Конструктор.
     *
     * @param consoleManager consoleManager.
     */
    public CoordinatesCreate(ConsoleManager consoleManager) {
        super(consoleManager);
    }

    /**
     * Запрашивание полей и возврат экземпляра собранной модели.
     */
    @Override
    public Coordinates build() {
        return new Coordinates(askInt("Coordinate.coordinateX",
                "(Integer, поле не может быть null)",
                Objects::nonNull),
                askLong("Coordinate.coordinateY",
                        "(Long)",
                        l -> (true)));
    }
}
