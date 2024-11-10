package org.example.view;

import org.example.controller.DeveloperController;
import org.example.controller.SkillController;
import org.example.controller.SpecialtyController;
import org.example.model.Developer;
import org.example.model.Skill;
import org.example.model.Specialty;
import org.example.model.Status;
import java.util.*;

public class View {

    private final DeveloperController developerController = new DeveloperController();
    private final SpecialtyController specialtyController = new SpecialtyController();
    private final SkillController skillController = new SkillController();
    private final Scanner scanner = new Scanner(System.in);

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
                Specialty specialty = specialtyController.getSpecialtyById(1L);
                List<Skill> skills = new ArrayList<>();
                skills.add(skillController.getSkillById(1L));
                developer.setSkills(skills);
                developer.setSpecialty(specialty);

                developerController.saveDeveloper(developer);

                System.out.println("Разработчик : " + developer.getFirstName() + " , " + developer.getLastName() + " добавлен");

            } else if (input == 5) {
                System.out.println("Введите ID разработчика для измений данных");
                Developer dev = developerController.getDeveloperById(scanner.nextLong());

                scanner.nextLine();

                System.out.println("Введите новое имя разработчика");
                String newFirstName = scanner.nextLine();

                System.out.println("Введите новую фамилию разработчика");
                String newLastName = scanner.nextLine();

                if (!newFirstName.isEmpty()) {
                    dev.setFirstName(newFirstName);
                }

                if (!newLastName.isEmpty()) {
                    dev.setLastName(newLastName);
                }

                dev.setStatus(Status.ACTIVE);
                developerController.updateDeveloper(dev);
                System.out.println("Разработчик изменен: \n" + "Статус установлен по умолчанию: " + Status.ACTIVE);

        } else if (input == 6) {
                System.out.println("Введите ID разработчика для удаления");
                Long id = scanner.nextLong();
                developerController.deleteDeveloper(id);
                System.out.println("Разработчик удален");

            } else if (input == 7) {
                System.out.println("Введите ID разработчика для установки статуса: ");
                Developer dev = developerController.getDeveloperById(scanner.nextLong());
                dev.setStatus(Status.DELETE);
                developerController.updateDeveloper(dev);
                System.out.println("Установлен статус: " + Status.DELETE);

            } else if (input == 8) {
                System.out.println("Введите ID разработчика для установки статуса: ");
                Developer dev = developerController.getDeveloperById(scanner.nextLong());
                dev.setStatus(Status.ACTIVE);
                developerController.updateDeveloper(dev);
                System.out.println("Установлен статус: " + Status.ACTIVE);
            }

        } while (input != 1) ;

    }

    private static void extracted() {
        for (String s : Arrays.asList(
                "Меню", "1. Выход", "2. Вывести всех разработчиков",
                "3. Вывести разработчика по ID", "4. Добавить разработчика",
                "5. Изменить разработчика", "6. Удалить разработчика по ID", "7. Установить статутс DELETED",
                "8. Установить статус ACTIVE")) {
            System.out.println(s);
        }
    }
}
