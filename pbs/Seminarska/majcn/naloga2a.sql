CREATE VIEW x_view AS
  SELECT n.id, n.x, n.y, i.tid, n.vid, n.village, i.pid, i.player, i.aid, a.alliance, n.population
  FROM naselje n
    JOIN igralec i ON i.pid = n.pid
    JOIN aliansa a ON a.aid = i.aid
  ORDER BY n.id;