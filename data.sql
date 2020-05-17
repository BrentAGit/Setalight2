SET DB_CLOSE_DELAY -1;        
;             
CREATE USER IF NOT EXISTS "SA" SALT 'f67dde86ae4c06de' HASH '8fe433182ed7cd247c26e311f86efd0bcf7398c214e52b3b9f61d70ec9246590' ADMIN;         
CREATE SEQUENCE "PUBLIC"."EVENT_SEQ" START WITH 3 MINVALUE 0; 
CREATE SEQUENCE "PUBLIC"."HIBERNATE_SEQUENCE" START WITH 1;   
CREATE SEQUENCE "PUBLIC"."USER_SEQ" START WITH 3 MINVALUE 0;  
CREATE MEMORY TABLE "PUBLIC"."EVENT"(
    "ID" INTEGER NOT NULL,
    "AANTALDEELNEMERS" INTEGER NOT NULL,
    "ADRES" VARCHAR(255),
    "CONTROLE" BOOLEAN NOT NULL,
    "DATUM" TIMESTAMP,
    "DESCRIPTION" VARCHAR(255),
    "END_TIME" TIME,
    "NAME" VARCHAR(255),
    "START_TIME" TIME
);  
ALTER TABLE "PUBLIC"."EVENT" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_3" PRIMARY KEY("ID");        
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.EVENT;   
INSERT INTO "PUBLIC"."EVENT" VALUES
(0, 50, 'Zandpoortvest 60, 2800 Mechelen', TRUE, TIMESTAMP '2020-05-17 00:00:00', 'Test description', TIME '15:00:00', 'testevent', TIME '19:00:00'),
(1, 50, 'Zandpoortvest 60, 2800 Mechelen', TRUE, TIMESTAMP '2020-04-17 00:00:00', 'Test description', TIME '15:00:00', 'testevent', TIME '19:00:00'),
(2, 50, 'Zandpoortvest 60, 2800 Mechelen', TRUE, TIMESTAMP '2020-06-17 00:00:00', 'Test description', TIME '15:00:00', 'testevent', TIME '19:00:00');      
CREATE MEMORY TABLE "PUBLIC"."EVENT_USERS"(
    "EVENT_ID" INTEGER NOT NULL,
    "USERS_ID" INTEGER NOT NULL
);            
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.EVENT_USERS;             
INSERT INTO "PUBLIC"."EVENT_USERS" VALUES
(0, 0),
(0, 1);   
CREATE MEMORY TABLE "PUBLIC"."PROFILE"(
    "ID" INTEGER NOT NULL,
    "HEIGHT" VARCHAR(255),
    "BIRTHDATE" TIMESTAMP,
    "HAIRCOLOR" VARCHAR(255),
    "USER_ID_ID" INTEGER
);      
ALTER TABLE "PUBLIC"."PROFILE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_1" PRIMARY KEY("ID");      
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.PROFILE; 
CREATE MEMORY TABLE "PUBLIC"."USER"(
    "ID" INTEGER NOT NULL,
    "BIRTHDATE" TIMESTAMP,
    "EMAIL" VARCHAR(255),
    "FULLPICTURE" VARCHAR(255),
    "HAIRCOLOR" VARCHAR(255),
    "LENGTH" DOUBLE NOT NULL,
    "NATIONAL_INSURANCE_NUMBER" VARCHAR(255),
    "PASSWORD" VARCHAR(255),
    "PHYSICAL_ATRIBUTES" VARCHAR(255),
    "PROFILEPICTURE" VARCHAR(255),
    "ROLE" VARCHAR(255),
    "USERNAME" VARCHAR(255),
    "VERIFIED" BOOLEAN NOT NULL
);  
ALTER TABLE "PUBLIC"."USER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_2" PRIMARY KEY("ID");         
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.USER;    
INSERT INTO "PUBLIC"."USER" VALUES
(0, NULL, NULL, NULL, NULL, 0.0, NULL, '$2a$10$9MIX8kYPkuB7uE/H5nHF8.KG6.YdjBA/voOnjSZnZDxLXL/2BIerS', NULL, NULL, 'ADMIN', 'admin', FALSE),
(1, TIMESTAMP '1999-05-17 00:00:00', 'r0790860@student.thomasmore.be', '/img/20993.jpg', NULL, 0.0, NULL, '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', NULL, '/img/41.png', 'USER', 'marie', FALSE),
(2, NULL, NULL, NULL, NULL, 0.0, NULL, '$2a$10$pQnldtWyD5CpMBjWJycMW.jmEOY2zKqjSkCo5V2vKqvW44ORrdpAa', NULL, NULL, 'PRODUCTIEHUIS', 'testHuis', FALSE);            
CREATE MEMORY TABLE "PUBLIC"."USER_ERVARING"(
    "USER_ID" INTEGER NOT NULL,
    "ERVARING_ID" INTEGER NOT NULL
);        
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.USER_ERVARING;           
CREATE MEMORY TABLE "PUBLIC"."USER_FRENDS"(
    "USER_ID" INTEGER NOT NULL,
    "FRENDS_ID" INTEGER NOT NULL
);            
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.USER_FRENDS;             
ALTER TABLE "PUBLIC"."EVENT_USERS" ADD CONSTRAINT "PUBLIC"."FKHS210MOO9X8PCDTYVASAHRKMT" FOREIGN KEY("EVENT_ID") REFERENCES "PUBLIC"."EVENT"("ID") NOCHECK;   
ALTER TABLE "PUBLIC"."PROFILE" ADD CONSTRAINT "PUBLIC"."FKS4MT5YFN61QXC8PF0FQ6O8MN9" FOREIGN KEY("USER_ID_ID") REFERENCES "PUBLIC"."USER"("ID") NOCHECK;      
ALTER TABLE "PUBLIC"."USER_ERVARING" ADD CONSTRAINT "PUBLIC"."FKDVIMMKOF0PXGCH7TT13143AIO" FOREIGN KEY("ERVARING_ID") REFERENCES "PUBLIC"."EVENT"("ID") NOCHECK;              
ALTER TABLE "PUBLIC"."USER_FRENDS" ADD CONSTRAINT "PUBLIC"."FKEV883AIIM0GFVSYFIB51L9L6G" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USER"("ID") NOCHECK;     
ALTER TABLE "PUBLIC"."USER_FRENDS" ADD CONSTRAINT "PUBLIC"."FKGY18V27O82CI4CEO1BATUJ8OX" FOREIGN KEY("FRENDS_ID") REFERENCES "PUBLIC"."USER"("ID") NOCHECK;   
ALTER TABLE "PUBLIC"."EVENT_USERS" ADD CONSTRAINT "PUBLIC"."FKL0V7OWUU67Q8OY7HLD3HB7O8M" FOREIGN KEY("USERS_ID") REFERENCES "PUBLIC"."USER"("ID") NOCHECK;    
ALTER TABLE "PUBLIC"."USER_ERVARING" ADD CONSTRAINT "PUBLIC"."FKMGU4BP9SBD75KADX3F4C82SX5" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USER"("ID") NOCHECK;   
