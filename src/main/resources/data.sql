INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'admin', '$2a$10$9MIX8kYPkuB7uE/H5nHF8.KG6.YdjBA/voOnjSZnZDxLXL/2BIerS', 'ADMIN', false);--paswoord:admin
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'marie', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER', false);--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'testHuis', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'PRODUCTIEHUIS', false);--paswoord:password

INSERT INTO PROFILE (ID, USER_ID_ID, LENGTH, EMAIL, BIRTH_DATE, PROFILE_PICTURE, FULL_PICTURE, REWARD_POINTS)
    VALUES (nextval('PROFILE_SEQ'), 2, 0, 'r0803085@student.thomasmore.be', '2001-06-23', '/img/41.png', '/img/20993.jpg', 1);

insert into event(ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER)
    VALUES(nextval('EVENT_SEQ'),50,true,'2020-05-17','Test description','15:00','testevent','19:00', '2800', 'Mechelen', 'Zandpoortvest', '60');--,'Zandpoortvest 60, 2800 Mechelen'
insert into event(ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER)
    VALUES(nextval('EVENT_SEQ'),50, true,'2020-04-17','Test description','15:00','testevent','19:00', '2800', 'Mechelen', 'Zandpoortvest', '60');
insert into event(ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER)
    VALUES(nextval('EVENT_SEQ'),50,true,'2020-06-17','Test description','15:00','testevent','19:00', '2800', 'Mechelen', 'Zandpoortvest', '60');

insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(1, 1);
insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(1, 2);
