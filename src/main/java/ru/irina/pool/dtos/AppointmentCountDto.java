package ru.irina.pool.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель данных количества записей")
@JsonPropertyOrder({ "time", "count"})
public class AppointmentCountDto {
    @Schema(description = "Время (час)", example = "10")
    private String time;

    @Schema(description = "Количество записей", example = "6")
    private Long count;

    public AppointmentCountDto(Integer time, Long count) {
        this.time = time != null ? String.valueOf(time) : "";
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
