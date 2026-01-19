package ru.irina.pool.services;

import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.irina.pool.entities.Schedule;
import ru.irina.pool.repositories.ScheduleRepository;

import java.util.Optional;

@Service
public class ScheduleService extends BaseService <Schedule, Long>{
    public ScheduleService(JpaRepository<Schedule, Long> repository, MessageSource messageSource) {
        super(repository, messageSource);
    }

    public Optional<Schedule> findByDayOfWeek(int dayOfWeek) {
        return ((ScheduleRepository)repository).findByDayOfWeek(dayOfWeek);
    }

}
