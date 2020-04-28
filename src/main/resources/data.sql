insert into user(ID, USER_NAME, PASSWORD)
    values (0, 'test username', 'dit is geen password');
insert into event(ID,AANTALDEELNEMERS,ADRES,CONTROLE,DATUM,DESCRIPTION,END_TIME,"NAME",START_TIME)
    VALUES(0,50,'Zandpoortvest 60, 2800 Mechelen',true,'2020-05-17','Test description','15:00','testevent','19:00');
insert into EVENT_USERS(EVENT_ID, USERS_ID)
    values(0, 0);
