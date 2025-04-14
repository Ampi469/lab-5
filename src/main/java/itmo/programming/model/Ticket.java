package itmo.programming.model;

import java.util.Objects;

/**
 * Класс Ticket модели.
 */
public class Ticket implements Comparable<Ticket> {
    private final long id;
    private final String name;
    private final Coordinates coordinates;
    private final java.util.Date creationDate;
    private final Integer price;
    private final TicketType ticketType;
    private final Person person;

    /**
     * Конструктор.
     *
     * @param id id.
     *
     * @param name имя.
     *
     * @param coordinates координаты.
     *
     * @param creationDate creationDate.
     *
     * @param price цена.
     *
     * @param ticketType тип билета.
     *
     * @param person человек.
     */
    public Ticket(long id, String name, Coordinates coordinates,
                  java.util.Date creationDate, Integer price,
                  TicketType ticketType, Person person) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.ticketType = ticketType;
        this.person = person;
    }

    /**
     * Получить значение поля id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Получить значение поля name.
     */
    public String getName() {
        return name;
    }

    /**
     * Получить значение X.
     */
    public Integer getCoordinateX() {
        return coordinates.getCoordinatesX();
    }

    /**
     * Получить сумму координат.
     */
    public long getSumCoordinates() {
        return coordinates.sumOfCoordinates();
    }

    /**
     * Получить значение поля price.
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Получить значение поля ticketType.
     */
    public TicketType getTicketType() {
        return ticketType;
    }

    /**
     * Получить значения поля getHeight.
     */
    public long getHeight() {
        return person.getHeight();
    }

    /**
     * Получить значения поля getWeight.
     */
    public double getWidth() {
        return person.getHeight();
    }

    /**
     * Получить значения поля getPassportID.
     */
    public String getPassportId() {
        return person.getPassportId();
    }

    /**
     * Получить сумму элементов класса Person.
     */
    public double getSumPersonElement() {
        return person.sumPersonElement();
    }

    @Override
    public String toString() {
        return "Ticket: (id: " + id
                + ", name: " + name
                + ", coordinates: " + coordinates
                + ", creationDate: " + creationDate
                + ", price: " + price
                + ", ticketType: " + ticketType
                + ", person: " + person  + ")";

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final Ticket ticket = (Ticket) obj;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate,
                price, ticketType, person);
    }

    @Override
    public int compareTo(Ticket obj) {
        return (int) ((int) (this.getId() - obj.getId()));
    }

}
