package itmo.programming.model.create;

import itmo.programming.manager.ConsoleManager;
import itmo.programming.manager.ScannerManager;
import itmo.programming.model.TicketType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Абстрактный класс для запрашивания данных разного типа.
 *
 * @param <T> take.
 */
public abstract class Create<T> {
    private ConsoleManager consoleManager = null;
    private final Scanner scanner = ScannerManager.getScanner();

    /**
     * Конструктор.
     *
     * @param consoleManager consoleManager.
     */
    public Create(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    /**
     * Метод для заполнения полей, который реализует наследник.
     */
    public abstract T build();

    /**
     * Метод для запрашивания и получения строки.
     *
     * @param fieldName имя поля.
     *
     * @param restrictions ограничения.
     *
     * @param validator валидность.
     */
    public String askString(String fieldName, String restrictions, Predicate<String> validator) {
        while (true) {
            consoleManager.print("Введите " + fieldName + " " + restrictions + ": ");
            final String input = scanner.nextLine().trim();
            if (validator.test(input)) {
                return input;
            } else {
                if (input.isEmpty() && validator.test("")) {
                    return null;
                } else {
                    consoleManager.printErr("Неправильный формат ввода");
                }
            }
        }
    }

    /**
     * Survey and obtaining method.
     *
     * @param field field.
     *
     * @param validator validator.
     */
    public TicketType askEnum(String field, Predicate<String> validator) {
        int c = 1;
        while (true) {
            consoleManager.print("Введите " + field + " (Заглавными,"
                    + " строчными буквами, либо же введите номер):"
                    + "\n" + "Доступные значения: \n");
            for (TicketType value : TicketType.values()) {
                consoleManager.println(c++ + ". " + value.toString());
            }
            c = 1;
            final String input = scanner.nextLine().trim();
            try {
                final int value = Integer.parseInt(input);
                final List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
                if (numbers.contains(value)) {
                    return TicketType.values()[value - 1];
                } else {
                    consoleManager.printErr("Нет такого номера!");
                }
            } catch (NumberFormatException e) {
                if (validator.test(input)) {
                    for (TicketType value : TicketType.values()) {
                        if (value.toString().equals(input.toUpperCase())) {
                            return value;
                        }
                    }
                    consoleManager.printErr("Значение не найдено");
                } else {
                    consoleManager.printErr("Неправильный формат ввода");
                }
            } catch (NoSuchElementException e) {
                consoleManager.printErr("Введено некорректное значение");
            }

        }
    }

    /**
     * Метод для запрашивания и получения объекта типа Integer.
     *
     * @param field имя поля.
     *
     * @param restrictions ограничения.
     *
     * @param validator валидность.
     */
    public Integer askInt(String field, String restrictions, Predicate<Integer> validator) {
        while (true) {
            consoleManager.print("Введите" + field + " " + restrictions + ": ");
            final String input = scanner.nextLine().trim();
            try {
                final Integer number = Integer.parseInt(input);
                if (validator.test(number)) {
                    return number;
                } else {
                    consoleManager.printErr("Ошибка проверки");
                }
            } catch (NumberFormatException e) {

                if (input.isEmpty() && validator.test(null)) {

                    return 0;
                } else {
                    consoleManager.printErr("Неверный формат ввода");
                }
            }
        }
    }

    /**
     * Метод для запрашивания и получения объекта типа Long.
     *
     * @param field имя файла.
     *
     * @param restrictions ограничения.
     *
     * @param validator валидность.
     */
    public Long askLong(String field, String restrictions, Predicate<Long> validator) {
        while (true) {
            consoleManager.print("Введите " + field + " " + restrictions + ": ");
            final String input = scanner.nextLine().trim();
            try {
                final Long number = Long.parseLong(input);
                if (validator.test(number)) {
                    return number;
                } else {
                    consoleManager.printErr("Ошибка проверки");
                }
            } catch (NumberFormatException e) {
                if (input.isEmpty() && validator.test(null)) {
                    return null;
                } else {
                    consoleManager.printErr("Неверный формат ввода");
                }
            }
        }
    }

    /**
     * Метод для запрашивания и получения объекта типа Double.
     *
     * @param fieldName имя поля.
     *
     * @param restrictions ограничения.
     *
     * @param validator валидность.
     */
    public Double askDouble(String fieldName, String restrictions, Predicate<Double> validator) {
        while (true) {
            consoleManager.print("Введите " + fieldName + " " + restrictions + ": ");
            final String input = scanner.nextLine().trim().replace(",", ".");
            try {
                final Double number = Double.parseDouble(input);
                if (validator.test(number)) {
                    return number;
                } else {
                    consoleManager.printErr("Ошибка валидации");
                }
            } catch (NumberFormatException e) {
                if (input.isEmpty() && validator.test(null)) {
                    return null;
                }
                consoleManager.printErr("Неверный формат ввода");
            }
        }
    }

}
