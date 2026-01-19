package ru.irina.pool.converters;

import org.springframework.stereotype.Component;
import ru.irina.pool.dtos.ClientDto;
import ru.irina.pool.dtos.ClientNameDto;
import ru.irina.pool.entities.Client;

@Component
public class ClientConverter implements BaseConverter<Client, ClientDto>{
    @Override
    public ClientDto entityToDto(Client entity) {
        return new ClientDto(entity.getClientId(),
                entity.getName(),
                entity.getPhone(),
                entity.getEmail());
    }

    @Override
    public Client dtoToEntity(Client entity, ClientDto dto) {
        if (entity == null) {
            entity = new Client();
        }

        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());

        return entity;
    }

    public ClientNameDto entityToClientNameDto(Client entity) {
        return new ClientNameDto(entity.getClientId(), entity.getName());
    }
}
