CREATE TABLE pleme(
  tid integer default 0,
  tribe varchar(20) default '',
  PRIMARY KEY (tid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO pleme VALUES (1,'Rimljani'),(2,'Tevtoni'),(3,'Galci'),(4,'Narava'),(5,'Natarji');

CREATE TABLE aliansa(
  aid integer default 0,
  alliance varchar(100) default '',
  PRIMARY KEY (aid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO aliansa SELECT DISTINCT aid, alliance FROM x_world ORDER BY aid;

CREATE TABLE igralec(
  pid integer default 0 NOT NULL,
  player varchar(100) default '' NOT NULL,
  tid integer default 0 NOT NULL,
  aid integer default 0 NOT NULL,
  PRIMARY KEY (pid),
  FOREIGN KEY (tid) REFERENCES pleme(tid) ON UPDATE CASCADE,
  FOREIGN KEY (aid) REFERENCES aliansa(aid) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO igralec SELECT DISTINCT pid, player, tid, aid FROM x_world ORDER BY pid;

CREATE TABLE naselje(
  id integer default 0 NOT NULL,
  x integer default 0 NOT NULL,
  y integer default 0 NOT NULL,
  vid integer default 0 NOT NULL,
  village varchar(100) default '' NOT NULL,
  population integer default 0 NOT NULL,
  pid integer default 0 NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (pid) REFERENCES igralec(pid) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO naselje SELECT id, x, y, vid, village, population, pid FROM x_world;