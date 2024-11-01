package org.example.model;

import java.util.List;
import java.util.Objects;

public class Developer {

    private Long id;
    private String firstName;
    private String lastName;
    private Status status;
    private Specialty specialty;
    private List<Skill> skills;

    public String getFirstName() {
        return firstName;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public Status getStatus() {
        return status;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return Objects.equals(id, developer.id) && Objects.equals(firstName, developer.firstName) && Objects.equals(lastName, developer.lastName) && status == developer.status && Objects.equals(specialty, developer.specialty) && Objects.equals(skills, developer.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, status, specialty, skills);
    }

    @Override
    public String toString() {
        return "\n" + "Developer" +
                " id = " + id +
                " firstName = " + firstName +
                " lastName = " + lastName +
                " status = " + status +
                " specialty = " + specialty +
                " skills = " + skills;
    }
}

