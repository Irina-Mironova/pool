insert into schedule(day_of_week, start_hour, end_hour)
values (1, 8, 22),
       (2, 8, 22),
       (3, 8, 22),
       (4, 8, 22),
       (5, 8, 22),
       (6, 9, 20),
       (7, 9, 20);

insert into special_days (date, is_working, start_hour, end_hour, description)
values ('2026-01-01', false, null, null, 'Новый год'),
       ('2026-02-23', false, null, null, '23 февраля'),
       ('2026-03-08', false, null, null, '8 марта'),
       ('2026-05-01', false, null, null, '1 Мая'),
       ('2026-05-09', false, null, null, 'День Победы'),
       ('2026-06-12', false, null, null, 'День России'),
       ('2026-02-14', true, 10, 18, 'Плановая уборка бассейна'),
       ('2026-03-14', true, 10, 18, 'Плановая уборка бассейна');

insert into clients(name, phone, email)
values ('Иванов Иван Иванович', '+79171234567', 'ivanov@mail.ru'),
       ('Петров Петр Петрович', '+79172541244', 'petrov@mail.ru'),
       ('Сидоров Алексей Петрович', '+79177452369', 'sidorov@mail.ru'),
       ('Козлова Елена Дмитриевна', '+79637514112', 'kozlova@mail.ru'),
       ('Смирнов Дмитрий Александрович', '+79638884444', 'smirnov@mail.ru');

insert into appointments(client_id, date)
values (1, date '2026-01-15'),
       (2, date '2026-01-15'),
       (3, date '2026-01-15'),
       (4, date '2026-01-15'),
       (5, date '2026-01-15'),
       (1, date '2026-01-17'),
       (2, date '2026-01-17'),
       (3, date '2026-01-18');

insert into appointment_items(appointment_id, start_hour)
values (1, 10),
       (1, 11),
       (2, 11),
       (3, 12),
       (3, 13),
       (3, 14),
       (4, 10),
       (5, 11),
       (5, 12),
       (6, 11),
       (7, 12),
       (8, 11);
