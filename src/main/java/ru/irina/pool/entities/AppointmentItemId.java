package ru.irina.pool.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentItemId implements Serializable {
    @Column(name = "appointment_id")
    private Long appointmentId;

    @Column(name = "start_hour")
    private Integer hour;
}
