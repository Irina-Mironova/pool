package ru.irina.pool.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.irina.pool.dtos.AppointmentAddDto;
import ru.irina.pool.dtos.AppointmentCancelDto;
import ru.irina.pool.dtos.AppointmentCountDto;
import ru.irina.pool.services.AppointmentItemService;
import ru.irina.pool.services.AppointmentService;
import ru.irina.pool.utils.Validation;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v0/pool/timetable")
@RequiredArgsConstructor
@Tag(name = "Записи", description = "Методы работы с записями")
public class AppointmentController {

    private final AppointmentItemService appointmentItemService;

    private final AppointmentService appointmentService;

    @GetMapping("/all")
    @Operation(
            summary = "Запрос на получение занятых записей на определенную дату",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = AppointmentCountDto.class))
                    )
            }
    )
    public List<AppointmentCountDto> getCountBusyAppointments(
            @RequestParam("date")
            @Parameter(description = "дата посещения", example = "2026-01-15") LocalDate date
    ) {
        return appointmentItemService.getCountBusyAppointments(date);
    }

    @GetMapping("/available")
    @Operation(
            summary = "Запрос на получение доступных записей на определенную дату",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = AppointmentCountDto.class))
                    )
            }
    )
    public List<AppointmentCountDto> getCountAvailableAppointments(
            @RequestParam("date")
            @Parameter(description = "дата посещения", example = "2026-01-15") LocalDate date
    ) {
        return appointmentItemService.getCountAvailableAppointments(date);
    }


    @Operation(
            summary = "Запрос на запись клиента на определенное время",
            responses = {
                    @ApiResponse(
                            description = "Клиент успешно записан", responseCode = "201"
                    )
            }
    )
    @PostMapping("/reserve")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody @Validated AppointmentAddDto appointmentAddDto, BindingResult bindingResult) {
        appointmentService.checkValidation(bindingResult);

        return appointmentService.reserve(appointmentAddDto).toString();
    }

    @Operation(summary = "Отмена записи клиента на определенное время")
    @PostMapping("/cancel")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void cancel(@RequestBody @Validated AppointmentCancelDto appointmentCancelDto,
                         BindingResult bindingResult) {
        appointmentService.checkValidation(bindingResult);

        appointmentService.cancel(appointmentCancelDto);
    }

}
