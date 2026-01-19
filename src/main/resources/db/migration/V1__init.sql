-- График работы
create table schedule(
                      schedule_id serial not null primary key, --Уникальный идентификатор клиента графика
                      day_of_week int not null unique, --Номера дней недели (1-7)
                      start_hour int not null,  --Час начала работы
                      end_hour int not null --Час окончания работы
);

-- Специальные дни
create table special_days(
                      special_day_id serial not null primary key,--Уникальный идентификатор специального дня
                      date date not null UNIQUE,   -- Дата
                      is_working boolean not null default false, -- Работает ли бассейн (да/нет)
                      start_hour int,  -- Начало работы (если is_working = true)
                      end_hour int,    -- Конец работы (если is_working = true)
                      description varchar(255)  -- Описание
);

-- Клиенты
create table clients(
                      client_id serial not null primary key, --Уникальный идентификатор клиента
                      name varchar(150) not null, --ФИО
                      phone varchar(12), --телефон
                      email varchar(40) --email
);

-- Записи
create table appointments(
                 appointment_id serial not null primary key, --Уникальный идентификатор записи
                 client_id int not null, --Уникальный идентификатор клиента
                 date date not null, --Дата записи
                 created_at timestamp not null default now(), --Время создания записи
                 CONSTRAINT appointment_fk_client_id FOREIGN KEY(client_id) REFERENCES clients(client_id)
);

-- Часы записи
create table appointment_items(
             appointment_id int not null, --Уникальный идентификатор записи
             start_hour int not null,  --Начало записи
             primary key(appointment_id, start_hour),
             CONSTRAINT ai_fk_appointment_id FOREIGN KEY(appointment_id) REFERENCES appointments(appointment_id)
);

create index idx_clients_name on clients(name);

create index idx_appointments_date on appointments(date);
