package ru.irina.pool.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Модель данных для отмены записи")
public class AppointmentCancelDto {
    @Schema(description = "Id клиента", example = "1", required = true)
    @NotNull(message = "valid.message.empty}")
    private Long clientId;

    @Schema(description = "Идентификатор записи", example = "4", required = true)
    @NotNull(message = "valid.message.empty}")
    private Long orderId;

    public AppointmentCancelDto(Long clientId, Long orderId) {
        this.clientId = clientId;
        this.orderId = orderId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
