package ru.irina.pool.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.irina.pool.converters.ClientConverter;
import ru.irina.pool.dtos.ClientDto;
import ru.irina.pool.dtos.ClientNameDto;
import ru.irina.pool.entities.Client;
import ru.irina.pool.services.ClientService;
import ru.irina.pool.utils.Validation;

import java.util.List;

@RestController
@RequestMapping("/api/v0/pool/client")
@RequiredArgsConstructor
@Tag(name = "Клиенты", description = "Методы работы с клиентами")
public class ClientController {

    private final ClientService clientService;

    private final ClientConverter clientConverter;

    @Operation(
            summary = "Запрос на вывод всех клиентов бассейна",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientNameDto.class)))
                    )
            }
    )
    @GetMapping("/all")
    public List<ClientNameDto> findAll() {
        return clientService.findAll().stream()
                .map(clientConverter::entityToClientNameDto)
                .toList();
    }


    @Operation(
            summary = "Запрос на получение клиента по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ClientDto.class))
                    )
            }
    )
    @GetMapping("get/{id}")
    public ClientDto findById(@PathVariable
                              @Parameter(description = "Идентификатор клиента", required = true) Long id) {
        Client client = clientService.findById(id);

        return clientConverter.entityToDto(client);
    }

    @Operation(
            summary = "Запрос на добавление нового клиента",
            responses = {
                    @ApiResponse(
                            description = "Клиент успешно добавлен", responseCode = "201"
                    )
            }
    )
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Validated ClientDto clientDto, BindingResult bindingResult) {
        clientService.checkValidation(bindingResult);
        clientService.create(clientDto);
    }


    @Operation(
            summary = "Запрос на обновление данных о клиенте",
            responses = {
                    @ApiResponse(
                            description = "Информация о клиенте успешно обновлена", responseCode = "200"
                    )
            }
    )
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Validated ClientDto clientDto, BindingResult bindingResult) {
        clientService.checkValidation(bindingResult);
        clientService.update(clientDto);
    }


    @Operation(
            summary = "Запрос на поиск клиента по ФИО",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )
            }
    )
    @GetMapping("/name")
    public List<ClientDto> findByName(
            @RequestParam(value = "name", required = true)
            @Parameter(description = "ФИО", required = true) String name
    ) {
        return clientService.findByName(name).stream()
                .map(clientConverter::entityToDto)
                .toList();
    }

}
