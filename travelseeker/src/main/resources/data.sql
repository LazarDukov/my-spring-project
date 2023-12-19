
    USE
`journeyproject`;
INSERT INTO `airplane_tickets` (id, company_name, date_time, from_airport, to_airport, fly_number, price, available)
VALUES (UUID(), 'Wizz Air', '2024-12-30T10:30:00', 'Sofia', 'Vienna', '47391', 225.00, 120);
INSERT INTO `airplane_tickets` (id, company_name, date_time, from_airport, to_airport, fly_number, price, available)
VALUES (UUID(), 'Ryan air', '2024-01-01T22:30:00', 'Istanbul', 'Sydney', '77748', 490.00, 99);
INSERT INTO `airplane_tickets` (id, company_name, date_time, from_airport, to_airport, fly_number, price, available)
VALUES (UUID(), 'Foxy air', '2024-01-01T17:34:00', 'Amsterdam', 'Warsaw', '11930', 82.00, 200);
INSERT INTO `airplane_tickets` (id, company_name, date_time, from_airport, to_airport, fly_number, price, available)
VALUES (UUID(), 'Sx02', '2024-01-01T15:34:00', 'Ankara', 'Paris', '88580', 420.00, 35);
INSERT INTO `airplane_tickets` (id, company_name, date_time, from_airport, to_airport, fly_number, price, available)
VALUES (UUID(), 'ManSo', '2024-01-01T23:56:00', 'London', 'Athina', '48723', 299.00, 233);
INSERT INTO `airplane_tickets` (id, company_name, date_time, from_airport, to_airport, fly_number, price, available)
VALUES (UUID(), 'BnB', '2024-01-01T23:59:00', 'Koln', 'Madrid', '38884', 144.00, 137);
INSERT INTO `airplane_tickets` (id, company_name, date_time, from_airport, to_airport, fly_number, price, available)
VALUES (UUID(), 'Wizz Air', '2024-01-02T05:44:00', 'Abu Dhabi', 'Belgrad', '433999', 500.00, 166);
INSERT INTO `airplane_tickets` (id, company_name, date_time, from_airport, to_airport, fly_number, price, available)
VALUES (UUID(), 'Wizz Air', '2024-01-02T7:25:00', 'Valetta', 'Tokyo', '103994', 899.00, 300);
INSERT INTO `airplane_tickets` (id, company_name, date_time, from_airport, to_airport, fly_number, price, available)
VALUES (UUID(), 'GonFon', '2024-01-2T8:03:00', 'Tirana', 'Rome', '47441', 287.00, 222);

INSERT INTO `car_rents` (id, make, model, body_type, fuel_type, price, available)
VALUES (UUID(), 'Citroen', 'Xsara', 2, 1, 299.00, 10);

INSERT INTO `hotels` (id, name, country, city, address, stars, description, price_per_night, room_type, available)
VALUES (UUID(), 'Hilton', 'England', 'London', 'Street John Lennon 15 building 10', 5,
        'The one and only hotel which can give it to you these emotions!', 500.00, 2, 40);
