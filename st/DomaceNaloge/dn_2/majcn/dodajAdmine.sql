CREATE TABLE admin
(
  ime VARCHAR(50) NOT NULL ,
  geslo VARCHAR(45) NOT NULL ,
  PRIMARY KEY (ime)
);

INSERT INTO admin (ime, geslo) VALUES ('admin', '6MxWSl6TINbCJkfF5tq1UAW/Hmg=');
INSERT INTO admin (ime, geslo) VALUES ('majcn', '6MxWSl6TINbCJkfF5tq1UAW/Hmg=');

COMMIT;