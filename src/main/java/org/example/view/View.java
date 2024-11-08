package org.example.view;

import org.example.controller.DeveloperController;
import org.example.controller.SkillController;
import org.example.controller.SpecialtyController;
import org.example.model.Developer;
import org.example.model.Status;
import java.util.*;

public class View {

    private final DeveloperController developerController = new DeveloperController();
    private final SpecialtyController specialtyController = new SpecialtyController();
    private final SkillController skillController = new SkillController();
    private final Scanner scanner = new Scanner(System.in);

    // New commit
    // New commit 2

    public void start() {
        int input;

        do {
            extracted();
            input = scanner.nextInt();

            if (input == 1) {
                System.out.println("Выйти с приложения");

            } else if (input == 2) {
                System.out.println("Текущая коллекция разработчиков: ");
                System.out.println(developerController.getAllDevelopers());
                System.out.println();

            } else if (input == 3) {
                try {
                    System.out.println("Введите ID разработчика: ");
                    Long id = scanner.nextLong();
                    System.out.println(developerController.getDeveloperById(id));
                } catch (NullPointerException e) {  // TODO сделать после задания, обработку ввода текста
                    System.out.println("ID не найден");
                }
            } else if (input == 4) {
                System.out.println("Введите имя разработчика: ");
                String firstName = "";
                do {
                    firstName = scanner.nextLine();
                } while (Objects.equals(firstName, ""));

                System.out.println("Введите фамилию разработчика: ");
                String lastName = scanner.nextLine();

                Status status = Status.ACTIVE;

                Developer developer = new Developer();
                developer.setFirstName(firstName);
                developer.setLastName(lastName);
                developer.setStatus(status);

                developerController.saveDeveloper(developer);

                System.out.println(developer);
                System.out.println("Разработчик : " + developer.getFirstName() + " , " + developer.getLastName() + " добавлен");

            }

        } while (input != 1) ;

    }

    private static void extracted() {
        for (String s : Arrays.asList(
                "Меню", "1. Выход", "2. Вывести всех разработчиков",
                "3. Вывести разработчика по ID", "4. Добавить разработчика",
                "5. Изменить разработчика", "6. Удалить разработчика по ID")) {
            System.out.println(s);
        }
    }
}
