package ru.irina.pool.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Schema(description = "Модель данных создания записи")
public class AppointmentAddDto {
    @Schema(description = "Id клиента", required = true, example = "1")
    @NotNull(message = "{valid.message.empty}")
    private Long id;

    @Schema(description = "Дата и время начала записи(час)", example = "2026-01-15T10:00", required = true)
    @NotNull(message = "valid.message.empty}")
    private LocalDateTime datetime;

    public AppointmentAddDto(Long id, LocalDateTime datetime) {
        this.id = id;
        this.datetime = datetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
}
