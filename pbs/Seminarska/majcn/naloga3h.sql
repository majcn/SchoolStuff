DELIMITER //
CREATE PROCEDURE POPULACIJA(x1 INTEGER, y1 INTEGER, x2 INTEGER, y2 INTEGER)
BEGIN
  IF x1 < -400 THEN SET x1 = -400; END IF;
  IF y1 < -400 THEN SET y1 = -400; END IF;
  IF x2 > 400 THEN SET x2 = 400; END IF;
  IF y2 > 400 THEN SET y2 = 400; END IF;
  
  select SUM(population) from naselje n
  where (n.x between x1 and x2) and (n.y between y1 and y2);
END//