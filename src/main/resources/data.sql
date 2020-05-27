INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'admin', '$2a$10$9MIX8kYPkuB7uE/H5nHF8.KG6.YdjBA/voOnjSZnZDxLXL/2BIerS', 'ADMIN', false);--paswoord:admin
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'marie', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER', false);--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'VRT', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'PRODUCTIEHUIS', true);--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'disney', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'PRODUCTIEHUIS', false);--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'newproduction', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'PRODUCTIEHUIS', false);--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'jan', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER', false);--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'tom', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER', false);--paswoord:password


INSERT INTO PROFILE (ID, USER_ID_ID, "LENGTH", EMAIL, BIRTH_DATE, PROFILE_PICTURE, FULL_PICTURE, REWARD_POINTS)
    VALUES (nextval('PROFILE_SEQ'), 2, 1.8, 'r0803085@student.thomasmore.be', '2001-06-23', '/img/profilepicture.png', '/img/fullpicture.png', 1);
INSERT INTO PROFILE (ID, USER_ID_ID, "LENGTH", EMAIL, BIRTH_DATE, PROFILE_PICTURE, FULL_PICTURE, REWARD_POINTS)
    VALUES (nextval('PROFILE_SEQ'), 6, 1.9, 'r0803085@student.thomasmore.be', '2000-08-10', '/img/profilepicture.png', '/img/fullpicture.png', 1);
INSERT INTO PROFILE (ID, USER_ID_ID, "LENGTH", EMAIL, BIRTH_DATE, PROFILE_PICTURE, FULL_PICTURE, REWARD_POINTS)
    VALUES (nextval('PROFILE_SEQ'), 7, 1.85, 'r0803085@student.thomasmore.be', '1997-02-15', '/img/profilepicture.png', '/img/fullpicture.png', 1);

insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,"DATE",DESCRIPTION,END_TIME,"NAME",START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER, TYPE_WANTED)
    VALUES(nextval('EVENT_SEQ'),4,100,true,'2020-05-22','publiek gezocht voor nieuwe show','15:00','Quarantaine Time','14:00', '2800', 'Mechelen', 'Zandpoortvest', '60','publiek');--,'Zandpoortvest 60, 2800 Mechelen'
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,"DATE",DESCRIPTION,END_TIME,"NAME",START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED)
    VALUES(nextval('EVENT_SEQ'),4,50, true,'2020-07-20','figuranten nodig vor restaurant scene','15:00','Bar Fight','13:00', '2000', 'Antwerpen', 'caféstraat', '10','figurant');
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,"DATE",DESCRIPTION,END_TIME,"NAME",START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED)
    VALUES(nextval('EVENT_SEQ'),3,50,true,'2020-06-17','pilot sitcom publiek nodig','15:00','Sitcom','12:00', '1000', 'brussel', 'stadsstraat', '60','publiek');

insert into PRODUCTIEHUIS_PROFILE (ID, description, name_owner, USER_ID_ID)
    values (nextval('productiehuis_Profile_seq'), 'Mickey mouse woont hier', 'Walt Disney', 4);

insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(1, 1);
insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(1, 2);
