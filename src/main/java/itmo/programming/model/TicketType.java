package itmo.programming.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Enum TicketType модели.
 */
public enum TicketType {
    VIP,
    USUAL,
    BUDGETARY,
    CHEAP;

    /**
     * Получить все именные константы.
     */
    public static ArrayList<TicketType> types() {
        final ArrayList<TicketType> types = new ArrayList<>();
        Collections.addAll(types, TicketType.values());
        return types;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
