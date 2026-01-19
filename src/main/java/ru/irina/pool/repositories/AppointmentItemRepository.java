package ru.irina.pool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.irina.pool.dtos.projections.AppointmentCountProjection;
import ru.irina.pool.entities.AppointmentItem;
import ru.irina.pool.entities.AppointmentItemId;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentItemRepository extends JpaRepository<AppointmentItem, AppointmentItemId> {
    @Query("""
        SELECT ai.id.hour AS startHour, COUNT(ai) AS countAppointment
        FROM AppointmentItem ai
        JOIN ai.appointment a
        WHERE a.date = :date
        GROUP BY ai.id.hour
        ORDER BY ai.id.hour
    """)
    List<AppointmentCountProjection> countBusyAppointments(@Param("date") LocalDate date);

    @Query("""
        SELECT ai.id.hour AS startHour, (:maxCount - COUNT(ai)) AS countAppointment
        FROM AppointmentItem ai
        JOIN ai.appointment a
        WHERE a.date = :date
        GROUP BY ai.id.hour
        ORDER BY ai.id.hour
    """)
    List<AppointmentCountProjection> countAvailableAppointments(@Param("date") LocalDate date,
                                                                @Param("maxCount") Integer maxCount);

    @Query("""
        SELECT COUNT(ai)
        FROM AppointmentItem ai JOIN ai.appointment a
        WHERE a.date = :date AND ai.id.hour = :hour
    """)
    long countByDateAndHour(@Param("date") LocalDate date, @Param("hour") Integer hour);
}
