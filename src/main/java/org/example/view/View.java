package org.example.view;

import org.example.controller.DeveloperController;
import org.example.model.Developer;
import org.example.model.Skill;
import org.example.model.Specialty;
import org.example.model.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {

    private final DeveloperController developerController = new DeveloperController();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Вывести всех разработчиков");
        System.out.println(developerController.getAllDevelopers());

        System.out.println("Введите ID разработчика:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Получить разработчика по ID = " + id + ":");
        System.out.println(developerController.getDeveloperById(id));
    }
    public void start1() {
        System.out.println("Введите ID разработчика:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Введите имя разработчика:");
        String firstname = scanner.nextLine();

        System.out.println("Введите фамилию разработчика:");
        String lastname = scanner.nextLine();

        System.out.println("Введите специальность разработчика:");
        String specialty = scanner.nextLine();

        System.out.println("Введите навык разработчика:");
        String skill = scanner.nextLine();

        System.out.println("Введите статус разработчика (ACTIVE или DELETE) :");
        String statusInput = scanner.nextLine().toUpperCase();

        Status status;
        try {
            status = Status.valueOf(statusInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Неккоректный статус. По умолчанию установлен статус ACTIVE.");
            status = Status.ACTIVE;
        }

        Developer newDeveloper = new Developer();
        newDeveloper.setId(id);
        newDeveloper.setFirstName(firstname);
        newDeveloper.setLastName(lastname);

        Specialty newSpecialty = new Specialty();
        newDeveloper.setSpecialty(newSpecialty);

        List<Skill> skills = new ArrayList<>();
        newDeveloper.setSkills(skills);

        newDeveloper.setStatus(status);

        developerController.saveDeveloper(newDeveloper);
        System.out.println("Разработчик сохранен");
    }
}
