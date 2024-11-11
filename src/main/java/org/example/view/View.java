package org.example.view;

import org.example.controller.DeveloperController;
import org.example.controller.SkillController;
import org.example.controller.SpecialtyController;
import org.example.model.Developer;
import org.example.model.Skill;
import org.example.model.Specialty;
import org.example.model.Status;

import javax.lang.model.element.Name;
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

                developerController.saveDeveloper(developer);
                skillController.saveSkill(new Skill());

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

                developerController.updateDeveloper(dev);
                System.out.println("Разработчик изменен");

            } else if (input == 6) {
                System.out.println("Введите ID разработчика для удаления");
                Long id = scanner.nextLong();
                developerController.deleteDeveloper(id);
                System.out.println("Разработчик удален");

            } else if (input == 7) {
                System.out.println("Введите ID разработчика для установки статуса: DELETED");
                Developer dev = developerController.getDeveloperById(scanner.nextLong());
                dev.setStatus(Status.DELETED);
                developerController.updateDeveloper(dev);
                System.out.println("Установлен статус: DELETED");
                // 1 - ACTIVE,  2 - DELETED

            } else if (input == 8) {
                System.out.println("Текущая коллекция навыков: ");
                System.out.println(skillController.getAllSkills());
                System.out.println();

            } else if (input == 9) {
                try {
                    System.out.println("Введите ID навыка: ");
                    Long id = scanner.nextLong();
                    System.out.println(skillController.getSkillById(id));
                } catch (NullPointerException e) {
                    System.out.println("ID не найден");
                }

            } else if (input == 10) {
                scanner.nextLine();

                System.out.println("Введите название навыка: ");
                String name = scanner.nextLine();

                Status status = Status.ACTIVE;

                Skill skill = new Skill();
                skill.setName(name);
                skill.setStatus(status);

                skillController.saveSkill(skill);
                System.out.println("Навык : " + skill.getName() + "добавлен");

            } else if (input == 11) {
                System.out.println("Введите ID навыка для изменения данных");
                Skill skill = skillController.getSkillById(scanner.nextLong());

                scanner.nextLine();

                System.out.println("Введите новое название навыка");
                String newName = scanner.nextLine();

                if(!newName.isEmpty()) {
                    skill.setName(newName);
                }

                skillController.updateSkill(skill);
                System.out.println("Навык изменен");

            } else if (input == 12) {
                System.out.println("Введите ID навыка для удаления: ");
                Long id = scanner.nextLong();
                skillController.deleteSkill(id);
                System.out.println("Навык удален");

            } else if (input == 13) {
                System.out.println("Введите ID разработчика для установки статуса: DELETED");
                Skill skill = skillController.getSkillById(scanner.nextLong());
                skill.setStatus(Status.DELETED);
                skillController.updateSkill(skill);
                System.out.println("Установлен статус: DELETED");
            }

        } while (input != 1) ;

    }

    private static void extracted() {
        for (String s : Arrays.asList(
                "Меню", "1. Выход", "2. Вывести всех разработчиков",
                "3. Вывести разработчика по ID", "4. Добавить разработчика",
                "5. Изменить разработчика", "6. Удалить разработчика по ID",
                "7. Установить статутс разработчику DELETED", "8. Вывести все навыки разработчиков",
                "9. Вывести навыки по ID", "10. Добавить навык", "11. Изменить навык",
                "12. Удалить навык разработчика по ID", "13. Установить статус навыку DELETED")) {
            System.out.println(s);
        }
    }
}
