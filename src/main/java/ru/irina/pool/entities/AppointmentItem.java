package ru.irina.pool.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointment_items")
public class AppointmentItem {
    @EmbeddedId
    private AppointmentItemId id;

    @MapsId("appointmentId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    public AppointmentItem(Appointment appointment, Integer hour) {
        this.appointment = appointment;
        this.id = new AppointmentItemId(appointment.getId(), hour);
    }
}
