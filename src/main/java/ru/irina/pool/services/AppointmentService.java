package ru.irina.pool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.irina.pool.dtos.AppointmentAddDto;
import ru.irina.pool.dtos.AppointmentCancelDto;
import ru.irina.pool.entities.*;
import ru.irina.pool.exceptions.IllegalStateException;
import ru.irina.pool.exceptions.ResourceNotFoundException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Locale;

@Service
public class AppointmentService extends BaseService <Appointment, Long>{
    private final AppointmentItemService appointmentItemService;

    private final  SpecialDayService specialDayService;
    private final  ScheduleService scheduleService;

    private final ClientService clientService;

    private final MessageSource messageSource;

    @Value("${max-count}")
    private int MAX_COUNT;

    @Autowired
    public AppointmentService(JpaRepository<Appointment, Long> repository,
                              AppointmentItemService appointmentItemService,
                              SpecialDayService specialDayService, ScheduleService scheduleService,
                              ClientService clientService, MessageSource messageSource) {
        super(repository, messageSource);
        this.appointmentItemService = appointmentItemService;
        this.specialDayService = specialDayService;
        this.scheduleService = scheduleService;
        this.clientService = clientService;
        this.messageSource = messageSource;
    }

    @Transactional
    public Long reserve(AppointmentAddDto appointmentAddDto) {
        LocalDate date = appointmentAddDto.getDatetime().toLocalDate();
        int hour = appointmentAddDto.getDatetime().getHour();

        checkWorkingTime(date, hour);

        Client client = clientService.findById(appointmentAddDto.getId());

        long busyCount = appointmentItemService.getCountByDateAndHour(date, hour);

        if (busyCount >= MAX_COUNT) {
            throw new IllegalStateException(messageSource.getMessage("error.no-more-available-appointments",
                    null, Locale.getDefault()));
        }

        Appointment appointment = new Appointment(client, date);
        repository.save(appointment);

        AppointmentItem item = new AppointmentItem(appointment, hour);
        appointmentItemService.save(item);

        return appointment.getId();

    }

    private void checkWorkingTime(LocalDate date, int hour) {
        SpecialDay specialDay = specialDayService.findByDate(date).orElse(null);

        int startHour, endHour;
        if (specialDay != null) {
            if (!specialDay.getIsWorking()) {
                throw new IllegalStateException(messageSource.getMessage("error.pool-closed",
                        null, Locale.getDefault()));
            }
            startHour = specialDay.getStartHour();
            endHour = specialDay.getEndHour();
        } else {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            Schedule schedule = scheduleService.findByDayOfWeek(dayOfWeek.getValue())
                    .orElseThrow(() -> new IllegalStateException(messageSource.getMessage("error.no-schedule",
                            null, Locale.getDefault())));
            startHour = schedule.getStartHour();
            endHour = schedule.getEndHour();
        }

        if (hour < startHour || hour >= endHour) {
            throw new IllegalStateException(String.format(messageSource.getMessage("error.appointment-no-possible",
                    null, Locale.getDefault()), startHour, endHour));
        }
    }

    @Transactional
    public void cancel(AppointmentCancelDto appointmentCancelDto) {
        Client client = clientService.findById(appointmentCancelDto.getClientId());

        Appointment appointment = repository.findById(appointmentCancelDto.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(
                        messageSource.getMessage("error.resource.id",null,
                                Locale.getDefault()), appointmentCancelDto.getOrderId())));

        if (!appointment.getClient().getClientId().equals(client.getClientId())) {
            throw new IllegalStateException(messageSource.getMessage("error.appointment-not-belong-client",
                    null, Locale.getDefault()));
        }

        appointmentItemService.deleteAll(appointment.getItems());
        repository.delete(appointment);
    }

}
