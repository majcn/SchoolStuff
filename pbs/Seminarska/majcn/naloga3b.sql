select player as 'Kateri igralec ima največ populacije?' from igralec
where pid =
(
  select pid from naselje
  group by pid
  order by SUM(population) desc
  limit 1
)