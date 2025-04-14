package itmo.programming.model.create;

import itmo.programming.manager.ConsoleManager;
import itmo.programming.manager.ValidationManager;
import itmo.programming.model.Person;

/**
 * Класс для запрашивания и заполнения полей Person и для дальнейшей сборки экземпляров модели.
 */
public class PersonCreate extends Create<Person> {

    /**
     * Конструктор.
     *
     * @param consoleManager consoleManager.
     */
    public PersonCreate(ConsoleManager consoleManager) {
        super(consoleManager);
    }

    /**
     * Запрашивание полей и возврат экземпляра собранной модели.
     */
    @Override
    public Person build() {
        return new Person(askLong("Person.height",
                "(Long, поле должно быть больше нуля)",
                l -> (l > 0)),
                askDouble("Person.weight",
                        "(Double, поле должно быть больше нуля)",
                        d -> (d > 0)),
                askString("Person.passportID",
                        "(String, поле должно быть уникальным,"
                                + "не может быть пустым",
                        s -> {
                            if (s.isEmpty()) {
                                return false;
                            }
                            ValidationManager.isValidPassportId(s);
                            return true;
                        })
        );
    }
}
