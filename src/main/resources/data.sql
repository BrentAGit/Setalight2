INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE)
    VALUES (nextval('USER_SEQ'), 'admin', '$2a$10$9MIX8kYPkuB7uE/H5nHF8.KG6.YdjBA/voOnjSZnZDxLXL/2BIerS', 'ADMIN');--paswoord:admin
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE)
    VALUES (nextval('USER_SEQ'), 'marie', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER');--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE)
    VALUES (nextval('USER_SEQ'), 'VRT', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'PRODUCTIEHUIS');--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE)
    VALUES (nextval('USER_SEQ'), 'disney', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'PRODUCTIEHUIS');--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE)
    VALUES (nextval('USER_SEQ'), 'newproduction', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'PRODUCTIEHUIS');--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE)
    VALUES (nextval('USER_SEQ'), 'jan', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER');--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE)
    VALUES (nextval('USER_SEQ'), 'tom', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER');--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE)
    VALUES (nextval('USER_SEQ'), 'tim', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER');--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE)
    VALUES (nextval('USER_SEQ'), 'jos', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER');--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE)
    VALUES (nextval('USER_SEQ'), 'bert', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER');--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE)
    VALUES (nextval('USER_SEQ'), 'karel', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'USER');--paswoord:password
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE)
    VALUES (nextval('USER_SEQ'), 'VTM', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'PRODUCTIEHUIS');--paswoord:
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE)
    VALUES (nextval('USER_SEQ'), 'Canvas', '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', 'PRODUCTIEHUIS');--paswoord:password

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

insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER, TYPE_WANTED, PICTURE, REWARD_CODE)
    VALUES(nextval('EVENT_SEQ'),4,100,true,'2020-05-22','Publiek gezocht voor nieuwe show','15:00','Quarantaine Time','14:00', '2800', 'Mechelen', 'Zandpoortvest', '60','publiek', '/img/talkshow.jpg','D12345');
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED, PICTURE, REWARD_CODE)
    VALUES(nextval('EVENT_SEQ'),4,50, true,'2020-07-20','Figuranten nodig voor restaurant scene','15:00','Bar Fight','13:00', '2000', 'Antwerpen', 'caféstraat', '10','figurant', '/img/bar.jpg','D12346');
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED, PICTURE, REWARD_CODE)
    VALUES(nextval('EVENT_SEQ'),3,50,true,'2020-06-17','Pilot sitcom publiek nodig','15:00','Sitcom','12:00', '1000', 'Brussel', 'stadsstraat', '60','publiek', '/img/Sitcom.jpg','V12345');
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED, PICTURE, REWARD_CODE)
    VALUES(nextval('EVENT_SEQ'),4,50, true,'2020-07-25','Figuranten nodig','15:00','Thuis','13:00', '2000', 'Antwerpen', 'caféstraat', '10','figurant', '/img/Thuis_logo.png','D12347');
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED, PICTURE, REWARD_CODE)
    VALUES(nextval('EVENT_SEQ'),3,50, true,'2020-07-28','Figuranten nodig','15:00','Familie','13:00', '2000', 'Antwerpen', 'caféstraat', '10','figurant', '/img/FamilieLogo.jpg','V12346');
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED, PICTURE, REWARD_CODE)
    VALUES(nextval('EVENT_SEQ'),3,50, true,'2020-08-11','Publiek nodig ','18:45','De slimste mens','16:00', '2000', 'Antwerpen', 'bosstraat', '17','publiek', '/img/SlimsteMensLogo.jpg','V12347');
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED, PICTURE, REWARD_CODE)
    VALUES(nextval('EVENT_SEQ'),3,50, true,'2020-08-28','Figuranten gezocht voor scene thuis ','18:30','Thuis','16:45', '2000', 'Antwerpen', 'breendestraat', '57 A','figurant', '/img/Thuis_logo.png','V12348');
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED, PICTURE, REWARD_CODE)
    VALUES(nextval('EVENT_SEQ'),3,50, true,'2020-07-28','Publiek gezocht voor premiere Katchoe ','20:45','Katchoe','19:00', '2000', 'Brussel', 'denkelstraat', '5','publiek', '/img/gameshow.jpg','V12349');
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED, PICTURE, REWARD_CODE)
    VALUES(nextval('EVENT_SEQ'),3,50, true,'2020-07-28','Figuranten gezocht voor nieuwe voorstelling','20:45','Familie','19:00', '2000', 'Brussel', 'denkelstraat', '5','figurant', '/img/FamilieLogo.jpg','V12350');
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED, PICTURE, REWARD_CODE)
    VALUES(nextval('EVENT_SEQ'),3,50, true,'2020-07-03','Publiek gezocht voor de show Blokken','21:15','Blokken','19:20', '1000', 'Brussel', 'Bordstraat', '42','publiek', '/img/FamilieLogo.jpg','V12351');
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED, PICTURE, REWARD_CODE)
    VALUES(nextval('EVENT_SEQ'),3,50, true,'2020-07-19','Start nieuwe show Zeg Het Maar, Publiek gezocht!','23:15','Zeg Het Maar','21:45', '2000', 'Antwerpen', 'Breederstraat', '74','publiek', '/img/talkshow.jpg','V12352');
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED, PICTURE, REWARD_CODE)
    VALUES(nextval('EVENT_SEQ'),3,50, true,'2020-08-23','Figuranted gezocht voor scene CSI','22:35','CSI Scene','20:05', '2000', 'Antwerpen', 'Baderlaan', '6','figurant', 'CSI_Logo.png','V12353');
insert into event(ID,CREATED_BY_ID,AMOUNT_OF_PARTICIPANTS,CONTROL,DATE,DESCRIPTION,END_TIME,NAME,START_TIME, POSTCODE, CITY, STREET, HOUSE_NUMBER,TYPE_WANTED, PICTURE, REWARD_CODE)
    VALUES(nextval('EVENT_SEQ'),3,50, true,'2020-07-18','Publiek nodig voor de slimste mens','20:55','De slimste mens','19:00', '2000', 'Antwerpen', 'bosstraat', '17','publiek', '/img/SlimsteMensLogo.jpg','V12354');

insert into PRODUCTIEHUIS_PROFILE (ID, description, name_owner, USER_ID_ID,name_company,company_number,province,city,street,postal_code,house_number, VERIFIED)
    values (nextval('productiehuis_Profile_seq'), 'Mickey mouse woont hier', 'Walt Disney', 4,'Disney','BE 0123.456.789','Brussel','Brussel','disneystraat','1000','121', false);
insert into PRODUCTIEHUIS_PROFILE (ID, description, name_owner, USER_ID_ID,name_company,company_number,province,city,street,postal_code,house_number, VERIFIED)
    values (nextval('productiehuis_Profile_seq'), 'vlaamse tv', 'VRT eigenaar', 3,'VRT television','BE 0123.456.789','Brussel','Brussel','vrtstraat','1000','121', false);
insert into PRODUCTIEHUIS_PROFILE (ID, description, name_owner, USER_ID_ID,name_company,company_number,province,city,street,postal_code,house_number, VERIFIED)
    values (nextval('productiehuis_Profile_seq'), 'vlaamse televisie', 'VTM', 12,'VTM televisie','BE 0123.456.789','Brussel','Brussel','VTMstraat','1000','121', false);
insert into PRODUCTIEHUIS_PROFILE (ID, description, name_owner, USER_ID_ID,name_company,company_number,province,city,street,postal_code,house_number, VERIFIED)
    values (nextval('productiehuis_Profile_seq'), '24/7 vlaamse programmas', 'Canvas', 13,'Canvas','BE 0123.456.789','Brussel','Brussel','Canvasstraat','1000','121', true);
-- insert into PRODUCTIEHUIS_PROFILE (ID, description, name_owner, USER_ID_ID,name_company,company_number,province,city,street,postal_code,house_number, VERIFIED)
--     values (nextval('productiehuis_Profile_seq'), 'regionale televisie', 'provintie Antwerpen', 14,'regionale Antwerpse tv','BE 0123.456.789','Antwerpen','Antwerpen','Rijfstraat','2018','2', true);


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
    values(nextval('reward_seq'), 'Goodiebag Setalight', '/img/goodiebag.png', 80);
insert into REWARD(ID, NAME, PICTURE, POINTS)
    values(nextval('reward_seq'), 'IPhone 10', '/img/IPhone10.jpg', 700);
insert into REWARD(ID, NAME, PICTURE, POINTS)
    values(nextval('reward_seq'), 'Couponcode Coolblue -15%', '/img/Logo-coolblue.jpg', 200);
insert into REWARD(ID, NAME, PICTURE, POINTS)
    values(nextval('reward_seq'), 'e-bike', '/img/ebike.jpg', 1200);
insert into REWARD(ID, NAME, PICTURE, POINTS)
    values(nextval('reward_seq'), 'Cannon SX540', '/img/Cannon.jpg', 280);
insert into REWARD(ID, NAME, PICTURE, POINTS)
    values(nextval('reward_seq'), 'Een dag Walibi (2 Personen)', '/img/Walibi.jpg', 200);
insert into REWARD(ID, NAME, PICTURE, POINTS)
    values(nextval('reward_seq'), 'Waardebon Kinepolis 20 EUR', '/img/Kinepolis.jpg', 60);
insert into REWARD(ID, NAME, PICTURE, POINTS)
    values(nextval('reward_seq'), 'Bluetooth Speaker T&G', '/img/Speaker.jpg', 300);
insert into REWARD(ID, NAME, PICTURE, POINTS)
    values(nextval('reward_seq'), 'Headset Logitec Pro', '/img/Headset.png', 260);