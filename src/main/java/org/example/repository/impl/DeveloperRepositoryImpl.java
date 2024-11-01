package org.example.repository.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Developer;
import org.example.repository.DeveloperRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DeveloperRepositoryImpl implements DeveloperRepository {

    private static final String DEVELOPER_PATH = "developers.json";
    private static final Gson gson = new Gson();

    @Override
    public List<Developer> getAll() {
        return getDevelopersFromJson();
    }

    @Override
    public Developer getById(Long id) {
        for(Developer developer : getDevelopersFromJson())
            if (developer.getId().equals(id)) {
                return developer;
            }
        return new Developer();
    }

    @Override
    public void save(Developer developer) {
        getDevelopersFromJson().add(developer);
    }

    @Override
    public void update(Developer developer) {
        List<Developer> developers = getDevelopersFromJson();

        for(int i = 0; i < developers.size(); i ++) {
            if(developers.get(i).getId().equals(developer.getId())) {
                developers.set(i, developer);
                break;
            }
        }
        saveAll(developers);
    }

    @Override
    public void delete(Long id) {
        List<Developer> developers = getDevelopersFromJson();
        developers.removeIf(dev -> dev.getId().equals(id));
        saveAll(developers);
    }

    private void saveAll(List<Developer> developers) {
        try(FileWriter writer = new FileWriter(DEVELOPER_PATH)) {
            gson.toJson(developers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Developer> getDevelopersFromJson() {
        try(FileReader reader = new FileReader(DEVELOPER_PATH)) {
            Type developersListType = new TypeToken<List<Developer>>(){}.getType();

            return gson.fromJson(reader, developersListType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}

