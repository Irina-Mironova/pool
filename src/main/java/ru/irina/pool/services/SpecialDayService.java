package ru.irina.pool.services;

import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.irina.pool.entities.SpecialDay;
import ru.irina.pool.repositories.SpecialDayRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SpecialDayService extends BaseService <SpecialDay, Long>{
    public SpecialDayService(JpaRepository<SpecialDay, Long> repository, MessageSource messageSource) {
        super(repository, messageSource);
    }

    public Optional<SpecialDay> findByDate(LocalDate date) {
        return ((SpecialDayRepository)repository).findByDate(date);
    }
}
