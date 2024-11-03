package org.example.model;

import lombok.Data;

@Data
public class Skill {

    private Long id;
    private String name;
    private Status status;

    @Override
    public String toString() {
        return "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"status\": \"" + status + "\"\n" +
                "}";
    }
}
