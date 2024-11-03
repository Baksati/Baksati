package org.example.model;

import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Developer {

    private Long id;
    private String firstName;
    private String lastName;
    private Status status;
    private Specialty specialty;
    private List<Skill> skills;

    @Override
    public String toString() {
        return "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"firstName\": \"" + firstName + "\",\n" +
                "  \"lastName\": \"" + lastName + "\",\n" +
                "  \"status\": \"" + status + "\",\n" +
                "  \"specialty\": " + (specialty != null ? specialty.toString().replaceAll("(?m)^", "    ") : "null") + ",\n" +
                "  \"skills\": " + (skills != null ? "[\n" + skills.stream()
                .map(skill -> "    " + skill.toString().replaceAll("(?m)^", "    "))
                .collect(Collectors.joining(",\n")) + "\n  ]" : "[]") + "\n" +
                "}";
    }
}
