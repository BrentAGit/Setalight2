INSERT INTO USER (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'admin', '$2a$10$9MIX8kYPkuB7uE/H5nHF8.KG6.YdjBA/voOnjSZnZDxLXL/2BIerS', 'ADMIN', false );--paswoord:admin
INSERT INTO USER (ID, USERNAME, PASSWORD, ROLE,VERIFIED)
    VALUES (nextval('USER_SEQ'), 'marie', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER', false);--paswoord:password
INSERT INTO user (ID, USERNAME, PASSWORD, ROLE,VERIFIED)
    VALUES (nextval('USER_SEQ'), 'testHuis', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'PRODUCTIEHUIS', false);--paswoord:password

insert into event(ID,AANTALDEELNEMERS,ADRES,CONTROLE,DATUM,DESCRIPTION,END_TIME,"NAME",START_TIME)
    VALUES(0,50,'Zandpoortvest 60, 2800 Mechelen',true,'2020-05-17','Test description','15:00','testevent','19:00');
    insert into event(ID,AANTALDEELNEMERS,ADRES,CONTROLE,DATUM,DESCRIPTION,END_TIME,"NAME",START_TIME)
    VALUES(1,50,'Zandpoortvest 60, 2800 Mechelen',true,'2020-04-17','Test description','15:00','testevent','19:00');
    insert into event(ID,AANTALDEELNEMERS,ADRES,CONTROLE,DATUM,DESCRIPTION,END_TIME,"NAME",START_TIME)
    VALUES(2,50,'Zandpoortvest 60, 2800 Mechelen',true,'2020-06-17','Test description','15:00','testevent','19:00');
insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(0, 0);
