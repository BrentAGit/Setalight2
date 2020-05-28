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
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'tim', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER', false);--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'jos', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER', false);--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'bert', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER', false);--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, VERIFIED)
    VALUES (nextval('USER_SEQ'), 'karel', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER', false);--paswoord:password


INSERT INTO PROFILE (ID, USER_ID_ID, LENGTH, EMAIL, BIRTH_DATE, PROFILE_PICTURE, FULL_PICTURE, REWARD_POINTS)
    VALUES (nextval('PROFILE_SEQ'), 2, 1.8, 'r0803085@student.thomasmore.be', '2001-06-23', '/img/profilepicture.png', '/img/fullpicture.png', 1000);
INSERT INTO PROFILE (ID, USER_ID_ID, LENGTH, EMAIL, BIRTH_DATE, PROFILE_PICTURE, FULL_PICTURE, REWARD_POINTS)
    VALUES (nextval('PROFILE_SEQ'), 6, 1.9, 'r0803085@student.thomasmore.be', '2000-08-10', '/img/profilepicture.png', '/img/fullpicture.png', 1);
INSERT INTO PROFILE (ID, USER_ID_ID, LENGTH, EMAIL, BIRTH_DATE, PROFILE_PICTURE, FULL_PICTURE, REWARD_POINTS)
    VALUES (nextval('PROFILE_SEQ'), 7, 1.85, 'r0803085@student.thomasmore.be', '1997-02-15', '/img/profilepicture.png', '/img/fullpicture.png', 1);
INSERT INTO PROFILE (ID, USER_ID_ID, LENGTH, EMAIL, BIRTH_DATE, PROFILE_PICTURE, FULL_PICTURE, REWARD_POINTS)
    VALUES (nextval('PROFILE_SEQ'), 8, 1.8, 'r0803085@student.thomasmore.be', '2001-06-23', '/img/profilepicture.png', '/img/fullpicture.png', 1);
INSERT INTO PROFILE (ID, USER_ID_ID, LENGTH, EMAIL, BIRTH_DATE, PROFILE_PICTURE, FULL_PICTURE, REWARD_POINTS)
    VALUES (nextval('PROFILE_SEQ'), 9, 1.9, 'r0803085@student.thomasmore.be', '2000-08-10', '/img/profilepicture.png', '/img/fullpicture.png', 1);
INSERT INTO PROFILE (ID, USER_ID_ID, LENGTH, EMAIL, BIRTH_DATE, PROFILE_PICTURE, FULL_PICTURE, REWARD_POINTS)
    VALUES (nextval('PROFILE_SEQ'), 10, 1.85, 'r0803085@student.thomasmore.be', '1997-02-15', '/img/profilepicture.png', '/img/fullpicture.png', 1);
INSERT INTO PROFILE (ID, USER_ID_ID, LENGTH, EMAIL, BIRTH_DATE, PROFILE_PICTURE, FULL_PICTURE, REWARD_POINTS)
    VALUES (nextval('PROFILE_SEQ'), 11, 1.85, 'r0803085@student.thomasmore.be', '1997-02-15', '/img/profilepicture.png', '/img/fullpicture.png', 1);

insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER, TYPE_WANTED)
    VALUES(nextval('EVENT_SEQ'),4,100,true,'2020-06-22','publiek gezocht voor nieuwe show','15:00','Quarantaine Time','14:00', '2800', 'Mechelen', 'Zandpoortvest', '60','publiek');
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED)
    VALUES(nextval('EVENT_SEQ'),4,50, true,'2020-07-20','figuranten nodig vor restaurant scene','15:00','Bar Fight','13:00', '2000', 'Antwerpen', 'caféstraat', '10','figurant');
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED)
    VALUES(nextval('EVENT_SEQ'),3,50,true,'2020-06-17','pilot sitcom publiek nodig','15:00','Sitcom','12:00', '1000', 'brussel', 'stadsstraat', '60','publiek');
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED)
    VALUES(nextval('EVENT_SEQ'),4,50, true,'2020-07-25','figuranten nodig ','15:00','thuis','13:00', '2000', 'Antwerpen', 'caféstraat', '10','figurant');
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED)
    VALUES(nextval('EVENT_SEQ'),3,50, true,'2020-07-28','figuranten nodig ','15:00','familie','13:00', '2000', 'Antwerpen', 'caféstraat', '10','figurant');

insert into PRODUCTIEHUIS_PROFILE (ID, description, name_owner, USER_ID_ID,name_company,company_number,province,city,street,postal_code,house_number)
    values (nextval('productiehuis_Profile_seq'), 'Mickey mouse woont hier', 'Walt Disney', 4,'Disney','BE 0123.456.789','Brussel','Brussel','disneystraat','1000','121');
insert into PRODUCTIEHUIS_PROFILE (ID, description, name_owner, USER_ID_ID,name_company,company_number,province,city,street,postal_code,house_number)
    values (nextval('productiehuis_Profile_seq'), 'vlaamse tv', 'VRT eigenaar', 3,'VRT television','BE 0123.456.789','Brussel','Brussel','vrtstraat','1000','121');

insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(1,6);
insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(1, 2);
insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(1, 7);
insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(1, 8);
insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(2, 6);
insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(2, 9);
insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(2, 10);
insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(3, 11);

insert into REWARD(ID, NAME, PICTURE, POINTS)
    values(nextval('reward_seq'), 'Macbook Pro 2018', '/img/MacBook-Pro-2018.jpg', 800);
insert into REWARD(ID, NAME, PICTURE, POINTS)
    values(nextval('reward_seq'), 'Goodiebag Setalight', '/img/goodiebag.png', 100);
insert into REWARD(ID, NAME, PICTURE, POINTS)
    values(nextval('reward_seq'), 'IPhone 10', '/img/IPhone10.jpg', 700);
insert into REWARD(ID, NAME, PICTURE, POINTS)
    values(nextval('reward_seq'), 'Couponcode Coolblue -15%', '/img/Logo-coolblue.jpg', 200);
insert into REWARD(ID, NAME, PICTURE, POINTS)
    values(nextval('reward_seq'), 'e-bike', '/img/ebike.jpg', 1200);