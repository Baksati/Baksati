package org.example.repository.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Skill;
import org.example.repository.SkillRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
        return new Skill();
    }

    @Override
    public void save(Skill skill) {
        getSkillsFromJson().add(skill);
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
        try(FileWriter writer = new FileWriter(SKILL_PATH)) {
            gson.toJson(skills, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Skill> getSkillsFromJson() {
        try(FileReader reader = new FileReader(SKILL_PATH)) {
            Type skillsListType = new TypeToken<List<Skill>>(){}.getType();

            return gson.fromJson(reader, skillsListType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
