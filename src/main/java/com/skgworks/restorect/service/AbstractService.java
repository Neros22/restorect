package com.skgworks.restorect.service;

import com.skgworks.restorect.model.BaseModel;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract sealed class AbstractService<Model extends BaseModel, Repository extends MongoRepository<Model, String>> permits ItemService, UserService {

    protected final Repository repository;

    public Model create(Model object) {
        object.setCreated(new Date());
        return repository.save(object);
    }

    public List<Model> getAll() {
        return repository.findAll();
    }

    public Model getById(String id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Model update(Model updatedModel) {
        Optional<Model> existingModel = repository.findById(updatedModel.getId());
        if (existingModel.isPresent()) {
            updatedModel.setUpdated(new Date());
            return repository.save(updatedModel);
        } else {
            throw new RuntimeException(updatedModel.getClass() + " not found with id: " + updatedModel.getId());
        }
    }

    public boolean delete(String id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }
}
