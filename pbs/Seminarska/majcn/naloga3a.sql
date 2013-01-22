select player as 'Kateri igralec ima največje naselje?' from igralec
where pid =
(
  select pid from naselje
  order by population desc
  limit 1
)