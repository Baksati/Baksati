package org.example.repository.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Skill;
import org.example.repository.SkillRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SkillRepositoryImpl implements SkillRepository {

    private static final String SKILL_PATH = "skills.json";
    private static final Gson gson = new Gson();

    @Override
    public List<Skill> getAll() {
        return getSkillsFromJson();
    }

    @Override
    public Skill getById(Long id) {
        for(Skill skill : getSkillsFromJson())
            if (skill.getId().equals(id)) {
                return skill;
            }
        return null;
    }

    @Override
    public void save(Skill skill) {
        List<Skill> skills = getSkillsFromJson();
        skill.setId(getLastId() + 1);
        skills.add(skill);
        saveAll(skills);
    }

    @Override
    public void update(Skill skill) {
        List<Skill> skills = getSkillsFromJson();

        for(int i = 0; i < skills.size(); i ++) {
            if(skills.get(i).getId().equals(skill.getId())) {
                skills.set(i, skill);
                break;
            }
        }
        saveAll(skills);
    }

    @Override
    public void delete(Long id) {
        List<Skill> skills = getSkillsFromJson();
        skills.removeIf(dev -> dev.getId().equals(id));
        saveAll(skills);
    }

    private void saveAll(List<Skill> skills) {
        try {
            String jsonString = new Gson().toJson(skills);
            Files.write(Paths.get(SKILL_PATH), jsonString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Skill> getSkillsFromJson() {
        try(Reader reader = new FileReader(SKILL_PATH)) {
            Type skillsListType = new TypeToken<ArrayList<Skill>>() { } .getType();

            return gson.fromJson(reader, skillsListType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private Long getLastId() {
        if(getSkillsFromJson().isEmpty())
            return 1L;
        return (long) getSkillsFromJson().size();
    }
}
