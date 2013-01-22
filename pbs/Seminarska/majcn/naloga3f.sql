CREATE TABLE temp_rename_pixi(
  id integer default 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO temp_rename_pixi select id from naselje where pid = (select pid from igralec where player = 'Pixi');

SET @row = 0;
UPDATE naselje
SET
  village = concat(@row := @row + 1, '. Pixi')
WHERE id IN (select id from temp_rename_pixi)
order by population desc;

DROP TABLE temp_rename_pixi;