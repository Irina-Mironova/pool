package ru.irina.pool.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель данных клиента(краткая)")
@JsonPropertyOrder({ "id", "name" })
public class ClientNameDto {
    @Schema(description = "Id клиента", required = true, example = "1")
    private Long id;

    @Schema(description = "ФИО клиента", maxLength = 255, minLength = 3, required = true,
            example = "Иванов Иван Иванович")
    private String name;

    public ClientNameDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
