package org.example.repository.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Specialty;
import org.example.repository.SpecialtyRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SpecialtyRepositoryImpl implements SpecialtyRepository {

    private static final String SPECIALTY_PATH = "specialties.json";
    private static final Gson gson = new Gson();

    @Override
    public List<Specialty> getAll() {
        return getSpecialtyFromJson();
    }

    @Override
    public Specialty getById(Long id) {
        for(Specialty specialty : getSpecialtyFromJson())
            if (specialty.getId().equals(id)) {
                return specialty;
            }
        return null;
    }

    @Override
    public void save(Specialty specialty) {
        List<Specialty> specialties = getSpecialtyFromJson();
        specialty.setId(getLastId() +1);
        specialties.add(specialty);
        saveAll(specialties);
    }

    @Override
    public void update(Specialty specialty) {
        List<Specialty> specialties = getSpecialtyFromJson();
        for(int i = 0; i < specialties.size(); i ++) {
            if(specialties.get(i).getId().equals(specialty.getId())) {
                specialties.set(i, specialty);
                break;
            }
        }
        saveAll(specialties);
    }

    @Override
    public void delete(Long id) {
        List<Specialty> specialties = getSpecialtyFromJson();
        specialties.removeIf(dev -> dev.getId().equals(id));
        saveAll(specialties);
    }

    private void saveAll(List<Specialty> specialties) {
        try {
            String jsonString = new Gson().toJson(specialties);
            Files.write(Paths.get(SPECIALTY_PATH), jsonString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Specialty> getSpecialtyFromJson() {
        try(Reader reader = new FileReader(SPECIALTY_PATH)) {
            Type SpecialtiesListType = new TypeToken<ArrayList<Specialty>>() { }.getType();

            return gson.fromJson(reader, SpecialtiesListType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private Long getLastId() {
        if(getSpecialtyFromJson().isEmpty())
            return 1L;
        return (long) getSpecialtyFromJson().size();
    }
}
