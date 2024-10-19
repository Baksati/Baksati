package org.example.view;

import org.example.controller.DeveloperController;
import org.example.model.Developer;

public class View {

    private final DeveloperController developerController = new DeveloperController();

    public void start() {
        System.out.println("Вывести всех девелоперов");
        System.out.println(developerController.getAllDevelopers());
    }
}
