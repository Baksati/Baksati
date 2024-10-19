package org.example.repository.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Specialty;
import org.example.repository.SpecialtyRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SpecialtyRepositoryImpl implements SpecialtyRepository {

    private static final String SPECIALTY_PATH = "specialties.json";
    private static final Gson gson = new Gson();

    @Override
    public List<Specialty> getAll() {
        try(FileReader reader = new FileReader(SPECIALTY_PATH)) {
            Type specialtiesListType = new TypeToken<List<Specialty>>(){}.getType();

            return gson.fromJson(reader, specialtiesListType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Specialty getById(Long id) {
        for(Specialty specialty : getAll())
            if (specialty.getId().equals(id)) {
                return specialty;
            }
        return null;
    }

    @Override
    public void save(Specialty specialty) {
        List<Specialty> specialties = getAll();
        specialties.add(specialty);
        saveAll(specialties);
    }

    @Override
    public void update(Specialty specialty) {
        List<Specialty> specialties = getAll();
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
        List<Specialty> specialties = getAll();
        specialties.removeIf(dev -> dev.getId().equals(id));
        saveAll(specialties);
    }

    private void saveAll(List<Specialty> specialties) {
        try(FileWriter writer = new FileWriter(SPECIALTY_PATH)) {
            gson.toJson(specialties, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}