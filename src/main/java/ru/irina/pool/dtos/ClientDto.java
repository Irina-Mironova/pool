package ru.irina.pool.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Модель данных клиента")
@JsonPropertyOrder({ "id", "name", "phone", "email"})
public class ClientDto {
    @Schema(description = "Id клиента", required = true, example = "1")
    @NotNull(message = "{valid.message.empty}")
    private Long id;

    @Schema(description = "ФИО клиента", maxLength = 255, minLength = 3, required = true,
            example = "Иванов Иван Иванович")
    @Size(min = 3, max = 255, message = "{valid.message.size}")
    @NotBlank(message = "{valid.message.empty}")
    private String name;

    @Schema(description = "Телефон", required = true, example = "+79174363697")
    @Pattern(regexp = "^(\\+7)([0-9]{10})$", message = "{valid.message.phone}")
    @NotBlank(message = "{valid.message.empty}")
    private String phone;

    @Schema(description = "Email", required = true, example = "example@example.com")
    @Email(message = "{valid.message.email}")
    private String email;

    public ClientDto() {
    }

    public ClientDto(Long id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public ClientDto(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
