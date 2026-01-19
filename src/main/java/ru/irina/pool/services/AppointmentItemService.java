package ru.irina.pool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.irina.pool.dtos.AppointmentCountDto;
import ru.irina.pool.entities.AppointmentItem;
import ru.irina.pool.repositories.AppointmentItemRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentItemService {
    private final AppointmentItemRepository appointmentItemRepository;

    @Value("${max-count}")
    private int MAX_COUNT;

    @Autowired
    public AppointmentItemService(AppointmentItemRepository appointmentItemRepository) {
        this.appointmentItemRepository = appointmentItemRepository;
    }

    public List<AppointmentCountDto> getCountBusyAppointments(LocalDate date) {
        return appointmentItemRepository.countBusyAppointments(date).stream()
                .map(p -> new AppointmentCountDto(p.getStartHour(), p.getCountAppointment()))
                .toList();
    }

    public List<AppointmentCountDto> getCountAvailableAppointments(LocalDate date) {

        return appointmentItemRepository.countAvailableAppointments(date, MAX_COUNT).stream()
                .map(p -> new AppointmentCountDto(p.getStartHour(), p.getCountAppointment()))
                .toList();
    }

    public long getCountByDateAndHour(LocalDate date, Integer hour) {
        return appointmentItemRepository.countByDateAndHour(date, hour);
    }

    public void save(AppointmentItem appointmentItem) {
        appointmentItemRepository.save(appointmentItem);
    }

    public void deleteAll(List<AppointmentItem> items) {
        appointmentItemRepository.deleteAll(items);
    }

}
