INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'admin', '$2a$10$9MIX8kYPkuB7uE/H5nHF8.KG6.YdjBA/voOnjSZnZDxLXL/2BIerS', 'ADMIN', false);--paswoord:admin
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'marie', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER', false);--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'testHuis', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'PRODUCTIEHUIS', false);--paswoord:password

INSERT INTO PROFILE (ID, USER_ID_ID, LENGTH, EMAIL, BIRTHDATE, PROFILEPICTURE, FULLPICTURE)
    VALUES (nextval('PROFILE_SEQ'), 2, 0, 'r0803085@student.thomasmore.be', '2001-06-23', '/img/41.png', '/img/20993.jpg');

insert into event(ID,AANTALDEELNEMERS,POSTCODE,CITY,STREET,HOUSENUMBER,CONTROLE,DATUM,DESCRIPTION,END_TIME,NAME,START_TIME)
    VALUES(nextval('EVENT_SEQ'),50,'2800','Mechelen', 'Zandpoortvest', '60',true,'2020-05-17','Test description','19:00','testevent','15:00');
insert into event(ID,AANTALDEELNEMERS,POSTCODE,CITY,STREET,HOUSENUMBER,CONTROLE,DATUM,DESCRIPTION,END_TIME,NAME,START_TIME)
    VALUES(nextval('EVENT_SEQ'),50,'2800','Mechelen', 'Zandpoortvest', '60',true,'2020-04-17','Test description','19:00','testevent','15:00');
insert into event(ID,AANTALDEELNEMERS,POSTCODE,CITY,STREET,HOUSENUMBER,CONTROLE,DATUM,DESCRIPTION,END_TIME,NAME,START_TIME)
    VALUES(nextval('EVENT_SEQ'),50,'2800','Mechelen', 'Zandpoortvest', '60',true,'2020-06-17','Test description','19:00','testevent','15:00');

insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(1, 1);
insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(1, 2);
