DROP TABLE IF EXISTS flight_passenger;
DROP TABLE IF EXISTS passenger;
DROP TABLE IF EXISTS miscellaneous;
DROP TABLE IF EXISTS flight;
DROP TABLE IF EXISTS airline;

CREATE TABLE airline (
	airline_id int4 NOT NULL,
	airline_name varchar(255) NULL,
	CONSTRAINT airline_pkey PRIMARY KEY (airline_id)
);

CREATE TABLE flight (
	flight_id int4 NOT NULL,
	departure_date timestamp NULL,
	destination varchar(255) NULL,
	flight_code varchar(255) NULL,
	origin varchar(255) NULL,
	airline_id int4 NULL,
	CONSTRAINT flight_pkey PRIMARY KEY (flight_id),
	CONSTRAINT flight_airline FOREIGN KEY (airline_id) REFERENCES airline(airline_id)
);

CREATE TABLE miscellaneous (
	miscellaneous_id int4 NOT NULL,
	flight_id int4 NULL,
	miscellaneous varchar(255) NULL,
	CONSTRAINT miscellaneous_pkey PRIMARY KEY (miscellaneous_id),
	CONSTRAINT miscellaneous_flight FOREIGN KEY (flight_id) REFERENCES flight(flight_id)
);

CREATE TABLE passenger (
	passenger_id int4 NOT NULL,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	CONSTRAINT passenger_pkey PRIMARY KEY (passenger_id)
);

CREATE TABLE flight_passenger (
	flight_id int4 NOT NULL,
	passenger_id int4 NOT NULL,
	CONSTRAINT flight_passenger_pkey PRIMARY KEY (flight_id, passenger_id),
	CONSTRAINT flight_passenger_flight FOREIGN KEY (flight_id) REFERENCES flight(flight_id),
	CONSTRAINT flight_passenger_passenger FOREIGN KEY (passenger_id) REFERENCES passenger(passenger_id)
);

