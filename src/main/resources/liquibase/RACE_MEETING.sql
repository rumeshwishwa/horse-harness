CREATE TABLE RACE_MEETING(
   RACE_MEETING_CODE VARCHAR(9) NOT NULL PRIMARY KEY
  ,SCHEDULED_DATE    DATE  NOT NULL
  ,TRACK_NAME        VARCHAR(21) NOT NULL
);
INSERT INTO RACE_MEETING(RACE_MEETING_CODE,SCHEDULED_DATE,TRACK_NAME) VALUES ('BA010920','2020-09-01','BALLARAT');
INSERT INTO RACE_MEETING(RACE_MEETING_CODE,SCHEDULED_DATE,TRACK_NAME) VALUES ('SP010920','2020-09-01','SHEPPARTON');
INSERT INTO RACE_MEETING(RACE_MEETING_CODE,SCHEDULED_DATE,TRACK_NAME) VALUES ('KI010920','2020-09-01','KILMORE');
INSERT INTO RACE_MEETING(RACE_MEETING_CODE,SCHEDULED_DATE,TRACK_NAME) VALUES ('GP010920','2020-09-01','GLOUCESTER PARK');
-- INSERT INTO RACE_MEETING(RACE_MEETING_CODE,SCHEDULED_DATE,TRACK_NAME) VALUES ('AP010920N','2020-09-01','ALBION PARK');
INSERT INTO RACE_MEETING(RACE_MEETING_CODE,SCHEDULED_DATE,TRACK_NAME) VALUES ('AP010920','2020-09-01','ALBION PARK');
INSERT INTO RACE_MEETING(RACE_MEETING_CODE,SCHEDULED_DATE,TRACK_NAME) VALUES ('PC010920','2020-09-01','TABCORP PARK MENANGLE');
INSERT INTO RACE_MEETING(RACE_MEETING_CODE,SCHEDULED_DATE,TRACK_NAME) VALUES ('DU010920','2020-09-01','DUBBO');
INSERT INTO RACE_MEETING(RACE_MEETING_CODE,SCHEDULED_DATE,TRACK_NAME) VALUES ('PK010920T','2020-09-01','PARKES');
INSERT INTO RACE_MEETING(RACE_MEETING_CODE,SCHEDULED_DATE,TRACK_NAME) VALUES ('EY010920','2020-09-01','RIVERINA PACEWAY');
