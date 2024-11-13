package org.example.repository.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Developer;
import org.example.repository.DeveloperRepository;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        return null;
    }

    @Override
    public void save(Developer developer) {
        List<Developer> developers = getDevelopersFromJson();
        developer.setId(getLastId() + 1);
        developers.add(developer);
        saveAll(developers);
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
        try {
            String jsonString = new Gson().toJson(developers);
            Files.write(Paths.get(DEVELOPER_PATH), jsonString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Developer> getDevelopersFromJson() {
        try(Reader reader = new FileReader(DEVELOPER_PATH)) {
            Type developersListType = new TypeToken<ArrayList<Developer>>() { }.getType();

            return gson.fromJson(reader, developersListType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private Long getLastId() {
        if (getDevelopersFromJson().isEmpty())
            return 1L;
        return (long) getDevelopersFromJson().size();
    }
}

