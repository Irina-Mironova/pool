package ru.irina.pool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.irina.pool.dtos.projections.AppointmentCountProjection;
import ru.irina.pool.entities.Appointment;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query(value = """
            SELECT ai.start_hour, count(ai.start_hour) as count_appointment
            FROM appointment_items ai
            WHERE ai.appointment_id IN (
                SELECT a.appointment_id FROM appointments a 
                WHERE a.date = :date
            )
            GROUP BY ai.start_hour
          """, nativeQuery = true)
    List<AppointmentCountProjection> countBusyAppointments(@Param("date") LocalDate date);
}
