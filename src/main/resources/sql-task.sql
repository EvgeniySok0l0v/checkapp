1)--Вывести к каждому самолету класс обслуживания и количество мест этого класса
select bookings.aircrafts.model, bookings.seats.fare_conditions,
       count(bookings.seats.fare_conditions) as "count_of_seats"
from aircrafts, seats
where bookings.aircrafts.aircraft_code = bookings.seats.aircraft_code
group by bookings.seats.fare_conditions, bookings.aircrafts.model
order by bookings.aircrafts.model, bookings.seats.fare_conditions

2)--Найти 3 самых вместительных самолета (модель + кол-во мест)
select bookings.aircrafts.model,
       count(bookings.seats.aircraft_code) as "count_of_seats"
from aircrafts, seats
where bookings.aircrafts.aircraft_code = bookings.seats.aircraft_code
group by bookings.aircrafts.model
order by "count_of_seats" desc
    limit 3

3)--Вывести код,модель самолета и места не эконом класса для самолета 'Аэробус A321-200' с сортировкой по местам
select bookings.aircrafts.aircraft_code, bookings.aircrafts.model, bookings.seats.seat_no
from aircrafts, seats
where bookings.aircrafts.aircraft_code = bookings.seats.aircraft_code and
        bookings.aircrafts.model = 'Аэробус A321-200' and
    bookings.seats.fare_conditions != 'Economy'
group by bookings.aircrafts.model, bookings.aircrafts.aircraft_code, bookings.seats.seat_no
order by bookings.seats.seat_no

4)--Вывести города в которых больше 1 аэропорта ( код аэропорта, аэропорт, город)
with duplicateCity as (select bookings.airports.city,
    count(city) as CNT
    from airports
    group by city
    having count(city) > 1)
select bookings.airports.airport_code, bookings.airports.airport_name, bookings.airports.city
from airports
where city in (select city from duplicateCity)

5)-- Найти ближайший вылетающий рейс из Екатеринбурга в Москву, на который еще не завершилась регистрация
select *
from flights
where
        departure_airport in (select airport_code
                              from airports
                              where city = 'Екатеринбург') and
        arrival_airport in (select airport_code
                            from airports
                            where city = 'Москва') and
        status = 'On Time'

6)--Вывести самый дешевый и дорогой билет и стоимость ( в одном результирующем ответе)
select A.ticket_no, book_ref, passenger_id, passenger_name, contact_data, A.amount
from (
         select ticket_no, amount
         from ticket_flights
         order by amount desc
             limit 1
     ) T1,
     (
         select ticket_no, amount
         from ticket_flights
         order by amount
             limit 1
     ) T2,
     (
         select tickets.ticket_no, flight_id, fare_conditions, amount, tickets, book_ref, passenger_id, passenger_name, contact_data
         from ticket_flights,tickets
         where ticket_flights.ticket_no = tickets.ticket_no
     ) A
where (T1.ticket_no = A.ticket_no and T1.amount = A.amount)
   or (T2.ticket_no = A.ticket_no and T2.amount = A.amount)
group by A.ticket_no, book_ref, passenger_id, passenger_name, contact_data, A.amount
having count(A.amount) > 0

7)-- Написать DDL таблицы Customers , должны быть поля id , firstName, LastName, email , phone. Добавить ограничения на поля ( constraints) .
create table Customers
(
    id serial primary key not null,
    first_name character varying(30) not null,
    last_name character varying(30) not null,
    email character varying(30) not null,
    phone character varying(30) not null
);

8)-- Написать DDL таблицы Orders , должен быть id, customerId,	quantity. Должен быть внешний ключ на таблицу customers + ограничения
create table Orders
(
    id serial primary key not null,
    customerId int4 not null,
    quantity int not null,
    foreign key (customerId) references Customers (id) on delete cascade
);

9)-- Написать 5 insert в эти таблицы
insert into customers(id, first_name, last_name, email, phone)
values
    (1,'Ibragim', 'Neibragim', 'ibragim@mail.ru', '33-23-12'),
    (2,'Anton', 'Putin', 'rus77@mail.ru', '77-77-77'),
    (3,'Sashka', 'Zelenskiy', 'ukr228@gmail.com', '22-80-00'),
    (4,'Evgeniy', 'Sokolov', 'koten0k335a@gmail.com', '99-23-12'),
    (5,'Ibragim', 'Beli', 'beli778ibr@mail.ru', '31-19-12')

insert into orders (id, customerid, quantity)
values
    (1, 1, 2),
    (2, 1, 3),
    (3, 3, 2),
    (4, 5, 99),
    (5, 2, 2)

10)-- удалить таблицы
drop table customers, orders

11)-- не могу ничего сложного придумать, усложнять это плохо(в пятницу утром попобую накатить что-то)
select * from bookings