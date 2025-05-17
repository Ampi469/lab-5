package itmo.programming.model.create;

import itmo.programming.manager.ConsoleManager;
import itmo.programming.manager.IdManager;
import itmo.programming.model.Coordinates;
import itmo.programming.model.Person;
import itmo.programming.model.Ticket;
import itmo.programming.model.TicketType;
import java.util.Date;


/**
 * Класс для запрашивания и заполнения полей Ticket и для дальнейшей сборки экземпляров модели.
 */
public class TicketCreate extends Create<Ticket> {
    private final ConsoleManager console;

    /**
     * Конструктор.
     *
     * @param console console.
     */
    public TicketCreate(ConsoleManager console) {
        super(console);
        this.console = console;
    }

    /**
     * Запрашивание полей и возврат экземпляра собранной модели.
     */
    @Override
    public Ticket build() {
        return new Ticket(IdManager.createId(),
                askString("Ticket.name",
                        "(String, поле не может быть null"
                                + "строка не может быть пустой)",
                        s -> !s.isEmpty()),
                askCoordinates(),
                new Date(),
                askInt("Ticket.price",
                        "(Integer, поле не может быть null,"
                                + "значение поля должно быть больше нуля)",
                        integer -> (integer != null && integer > 0)),
                askType(),
                askPerson());
    }

    /**
     * Method to update ticket.
     *
     * @param id id.
     */
    public Ticket update(long id) {
        return new Ticket(id,
                askString("Ticket.name",
                        "(String, поле не может быть null"
                                + "строка не может быть пустой)",
                        s -> !s.isEmpty()),
                askCoordinates(),
                new Date(),
                askInt("Ticket.price",
                        "(Integer, поле не может быть null,"
                                + "значение поля должно быть больше нуля)",
                        integer -> (integer != null && integer > 0)),
                askType(),
                askPerson());
    }

    /**
     * Method for getting coordinates.
     */
    private Coordinates askCoordinates() {
        return new CoordinatesCreate(console).build();
    }

    /**
     * Method for getting type.
     */
    private TicketType askType() {
        return askEnum("TicketType", s -> true);
    }

    /**
     * Method for getting person.
     */
    private Person askPerson() {
        return new PersonCreate(console).build();
    }
}
