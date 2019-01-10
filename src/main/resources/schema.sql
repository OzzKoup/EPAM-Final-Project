CREATE DATABASE cruise ENCODING='UTF8';

CREATE TABLE USER_ROLE (
ID SERIAL PRIMARY KEY,
LOGIN VARCHAR (30) NOT NULL,
PASSWORD VARCHAR (30) NOT NULL,
ROLE VARCHAR (30) NOT NULL,
NAME VARCHAR (30) NOT NULL,
SURNAME VARCHAR (30) NOT NULL,
BALANCE FLOAT
);

CREATE TABLE SHIP (
ID SERIAL PRIMARY KEY,
NAME VARCHAR (30) NOT NULL,
PASSENGER_AMOUNT INTEGER NOT NULL,
ROUTE_TO VARCHAR(30) NOT NULL,
ROUTE_FROM VARCHAR(30) NOT NULL,
AMOUNT_PORTS INTEGER NOT NULL,
TRAVEL_START DATE,
TRAVEL_END DATE,
STANDART_PRICE FLOAT
);

CREATE TABLE CRUISE_INFO (
ID SERIAL PRIMARY KEY,
SHIP_ID INTEGER REFERENCES SHIP(ID),
ROOM_TYPE VARCHAR (30),
USER_ID INTEGER REFERENCES USER_ROLE(ID),
TOTAL_PRICE FLOAT
);

CREATE TABLE EXCURSION (
ID SERIAL PRIMARY KEY,
NAME VARCHAR(30),
DURATION VARCHAR (30),
PRICE FLOAT,
ADDITIONAL_INFO VARCHAR (50),
SHIP_ID INTEGER REFERENCES SHIP(ID)
);

CREATE TABLE EXCURSION_INFO (
ID SERIAL PRIMARY KEY,
EXCURSION_ID INTEGER REFERENCES EXCURSION(ID),
USER_ID INTEGER REFERENCES USER_ROLE(ID),
CRUISE_INFO_ID INTEGER REFERENCES cruise_info(id)
);

CREATE TABLE BONUS (
ID SERIAL PRIMARY KEY,
NAME VARCHAR(40) NOT NULL,
 DESCRIPTION VARCHAR(50) NOT NULL,
ROOM_TYPE VARCHAR(30) DEFAULT NULL
);

SELECT * FROM bonus WHERE id = 12;