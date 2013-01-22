DELIMITER //
CREATE FUNCTION ST_IGRALCEV(centerx INTEGER, centery INTEGER, razdalja INTEGER) RETURNS INTEGER
BEGIN
  DECLARE x INTEGER;
  DECLARE minx INTEGER;
  DECLARE maxx INTEGER;
  DECLARE miny INTEGER;
  DECLARE maxy INTEGER;
  
  SET minx = centerx-razdalja;
  SET maxx = centerx+razdalja;
  SET miny = centery-razdalja;
  SET maxy = centery+razdalja;
  IF minx < -400 THEN SET minx = -400; END IF;
  IF maxx > 400 THEN SET maxx = 400; END IF;
  IF miny < -400 THEN SET miny = -400; END IF;
  IF maxy > 400 THEN SET maxy = 400; END IF;
  select count(*) into x from
  (
    select distinct pid from naselje n
    where (n.x between minx and maxx) and (n.y between miny and maxy)
  ) as tnaselja;
  RETURN x;
END//