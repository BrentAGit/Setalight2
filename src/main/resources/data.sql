INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED/*, "LENGTH"*/)
    VALUES (nextval('USER_SEQ'), 'admin', '$2a$10$9MIX8kYPkuB7uE/H5nHF8.KG6.YdjBA/voOnjSZnZDxLXL/2BIerS', 'ADMIN', false/*,0 */);--paswoord:admin
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED/*, "LENGTH",EMAIL, BIRTHDATE, PROFILEPICTURE,FULLPICTURE*/)
    VALUES (nextval('USER_SEQ'), 'marie', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER', false/*,0,'r0790860@student.thomasmore.be','1999-05-17','/img/41.png','/img/20993.jpg'*/);--paswoord:password
INSERT INTO userS (ID, USERNAME, PASSWORD, ROLE, VERIFIED/*, "LENGTH"*/)
    VALUES (nextval('USER_SEQ'), 'testHuis', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'PRODUCTIEHUIS', false/*,0*/);--paswoord:password

INSERT INTO PROFILE (ID, USER_ID_ID, "LENGTH", EMAIL, BIRTHDATE, PROFILEPICTURE, FULLPICTURE)
    VALUES (nextval('PROFILE_SEQ'), 1, 0, 'r0803085@student.thomasmore.be', '2001-06-23', '/img/41.png', '/img/20993.jpg');

insert into event(ID,AANTALDEELNEMERS,ADRES,CONTROLE,DATUM,DESCRIPTION,END_TIME,NAME,START_TIME)
    VALUES(nextval('EVENT_SEQ'),50,'Zandpoortvest 60, 2800 Mechelen',true,'2020-05-17','Test description','15:00','testevent','19:00');
insert into event(ID,AANTALDEELNEMERS,ADRES,CONTROLE,DATUM,DESCRIPTION,END_TIME,NAME,START_TIME)
    VALUES(nextval('EVENT_SEQ'),50,'Zandpoortvest 60, 2800 Mechelen',true,'2020-04-17','Test description','15:00','testevent','19:00');
insert into event(ID,AANTALDEELNEMERS,ADRES,CONTROLE,DATUM,DESCRIPTION,END_TIME,NAME,START_TIME)
    VALUES(nextval('EVENT_SEQ'),50,'Zandpoortvest 60, 2800 Mechelen',true,'2020-06-17','Test description','15:00','testevent','19:00');

insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(1, 1);
insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(1, 2);
