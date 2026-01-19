package ru.irina.pool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.irina.pool.entities.SpecialDay;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SpecialDayRepository extends JpaRepository<SpecialDay, Long> {
    Optional<SpecialDay> findByDate(LocalDate date);
}
