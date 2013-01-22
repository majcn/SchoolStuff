select COUNT(*) as 'število nadpovprečno močnih alians' from
(
  select id from naselje n
    join igralec i on i.pid = n.pid
  group by i.aid
  having SUM(population) >
  (
    select AVG(populacija) FROM
    (
      select SUM(population) as populacija from naselje n
        join igralec i on i.pid = n.pid
      group by i.aid
    ) as tavg
  )
) as tcount