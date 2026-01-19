package ru.irina.pool.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "special_days")
public class SpecialDay {
    @Id
    @Column(name = "special_day_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specialDayId;

    private LocalDate date;

    @Column(name = "is_working")
    private Boolean isWorking = false;

    private Byte startHour;

    private Byte endHour;

    private String description;
}
