package ru.irina.pool.services;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.irina.pool.converters.ClientConverter;
import ru.irina.pool.dtos.ClientDto;
import ru.irina.pool.entities.Client;
import ru.irina.pool.repositories.ClientRepository;

import java.util.List;

@Service
public class ClientService extends BaseService<Client, Long>{

    private final ClientConverter clientConverter;

    public ClientService(JpaRepository<Client, Long> repository, MessageSource messageSource,
                         ClientConverter clientConverter) {

        super(repository, messageSource);
        this.clientConverter = clientConverter;
    }

    public Client create(@Nonnull ClientDto clientDto) {
        Client client = clientConverter.dtoToEntity(null, clientDto);

        return save(client);
    }

    @Transactional
    public Client update(@Nonnull ClientDto clientDto) {
        Client client = clientConverter.dtoToEntity(findById(clientDto.getId()), clientDto);

        return save(client);
    }

    public List<Client> findByName(String name) {
        return ((ClientRepository) repository).findByName(name);
    }

}
