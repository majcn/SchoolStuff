select vid, x, y, village, population from naselje
where pid =
(
  select pid from naselje
  group by pid
  order by SUM(population) desc
  limit 1
)
order by population desc