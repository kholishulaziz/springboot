INSERT INTO Airline (airline_id,airline_name)
	VALUES (1,'American');
INSERT INTO Airline (airline_id,airline_name)
	VALUES (2,'Delta');
INSERT INTO Airline (airline_id,airline_name)
	VALUES (3,'United');

INSERT INTO Passenger (passenger_id, first_name, last_name)
    VALUES(1, 'Albus', 'Dumbledore');
INSERT INTO Passenger (passenger_id, first_name, last_name)
    VALUES(2, 'Hermione', 'Granger');
INSERT INTO Passenger (passenger_id, first_name, last_name)
    VALUES(3, 'Sirius', 'Black');

INSERT INTO Flight (flight_id, departure_date, destination, flight_code, origin, airline_id)
    VALUES(0, '2021-01-01 08:00:00.000', 'JOG', 'GA303', 'SUB', 1);

INSERT INTO Flight_passenger (flight_id, passenger_id)
    values(0, 1);
INSERT INTO Flight_passenger (flight_id, passenger_id)
    values(0, 2);

INSERT INTO Miscellaneous (miscellaneous_id, miscellaneous, flight_id)
    VALUES(1, 'Snack & Drink', 0);
