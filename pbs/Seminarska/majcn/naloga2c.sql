CREATE TABLE zbirni_igralec(
  pid integer default 0,
  SteviloNaselij integer default 0 NOT NULL,
  SkupnaPopulacija integer default 0 NOT NULL,
  PRIMARY KEY (pid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO zbirni_igralec select pid, COUNT(population) as SteviloNaselij, SUM(population) as SkupnaPopulacija from naselje group by pid order by pid

DELIMITER //
CREATE TRIGGER zbirni_igralec_I
AFTER INSERT ON naselje
FOR EACH ROW
BEGIN
  UPDATE zbirni_igralec
  SET
    SteviloNaselij = SteviloNaselij + 1,
    SkupnaPopulacija = SkupnaPopulacija + NEW.population
  WHERE zbirni_igralec.pid = NEW.pid;
END//

CREATE TRIGGER zbirni_igralec_D
AFTER DELETE ON naselje
FOR EACH ROW
BEGIN
  UPDATE zbirni_igralec
  SET
    SteviloNaselij = SteviloNaselij - 1,
    SkupnaPopulacija = SkupnaPopulacija - OLD.population
  WHERE zbirni_igralec.pid = OLD.pid;
END//

CREATE TRIGGER zbirni_igralec_U
AFTER UPDATE ON naselje
FOR EACH ROW
BEGIN
  IF NEW.pid = OLD.pid THEN
    UPDATE zbirni_igralec
    SET
      SkupnaPopulacija = SkupnaPopulacija + NEW.population - OLD.population
    WHERE zbirni_igralec.pid = NEW.pid;
  ELSE
    UPDATE zbirni_igralec
    SET
      SteviloNaselij = SteviloNaselij - 1,
      SkupnaPopulacija = SkupnaPopulacija - OLD.population
    WHERE zbirni_igralec.pid = OLD.pid;
	
    UPDATE zbirni_igralec
    SET
      SteviloNaselij = SteviloNaselij + 1,
      SkupnaPopulacija = SkupnaPopulacija + NEW.population
    WHERE zbirni_igralec.pid = NEW.pid;
  END IF;
END//