package ru.irina.pool.services;

import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.BindingResult;
import ru.irina.pool.exceptions.ResourceNotFoundException;
import ru.irina.pool.exceptions.ValidationException;

import java.util.List;
import java.util.Locale;

public class BaseService<T, ID> {
    protected final JpaRepository<T, ID> repository;
     protected final MessageSource messageSource;

    public BaseService(JpaRepository<T, ID> repository, MessageSource messageSource) {
        this.repository = repository;
        this.messageSource = messageSource;
    }

    public T findById(ID id) {
        return repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(messageSource.getMessage("error.resource.id",
                        null, Locale.getDefault()), id))
        );
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public void checkValidation(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult.getFieldErrors()
                            .stream()
                            .map(error -> error.getField() + ": " + error.getDefaultMessage())
                            .toList()
            );
        }
    }

}
