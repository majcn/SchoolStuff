CREATE table UPORABNIKI (
    ID_UPO         INTEGER NOT NULL
                PRIMARY KEY GENERATED ALWAYS AS IDENTITY
                (START WITH 1, INCREMENT BY 1),
    UPOIME    VARCHAR(50),
    GESLO   VARCHAR(50)
   );




INSERT INTO UPORABNIKI (UPOIME,GESLO)
 VALUES ('admin','6MxWSl6TINbCJkfF5tq1UAW/Hmg=');

INSERT INTO UPORABNIKI (UPOIME,GESLO)
 VALUES ('admin1','ul/cciN6e3Qc1Pmfpaa976hX4aQ=');