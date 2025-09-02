package itmo.programming.manager;

import itmo.programming.model.Ticket;
import itmo.programming.model.TicketType;

/**
 * Класс для проверки валидности.
 */
public class ValidationManager {

    /**
     * Проверка на валидность полей Ticket.
     *
     * @param ticket ticket.
     *
     * @param collectionManager collectionManager.
     */
    public static boolean isValidTicket(Ticket ticket, CollectionManager collectionManager) {
        return ticket.getId() > 0
                && ticket.getId() != null
                && ticket.getName() != null
                && ticket.getPrice() != null
                && ticket.getPrice() > 0;
    }

    /**
     * Проверка на валидность полей Coordinates.
     *
     * @param ticket ticket.
     */
    public static boolean isValidCoordinates(Ticket ticket) {
        return ticket.getCoordinateX() != null;
    }

    /**
     * Проверка на валидность полей Person.
     *
     * @param ticket ticket.
     */
    public static boolean isValidPerson(Ticket ticket) {
        return ticket.getHeight() > 0 && ticket.getWidth() > 0
                && !ticket.getPassportId().isEmpty();
    }

    /**
     * Проверка на уникальность поля passportID.
     *
     * @param passportId passportID.
     */
    public static boolean isValidPassportId(String passportId) {
        final CollectionManager collectionManager = new CollectionManager();
        for (Ticket ticket : collectionManager.getCollection()) {
            return !ticket.getPassportId().equals(passportId);
        }
        return false;
    }

    /**
     * Проверка на валидность Enum.
     *
     * @param ticket ticket.
     */
    public static boolean isValidEnum(Ticket ticket) {
        return TicketType.types().contains(ticket.getTicketType());
    }
}
