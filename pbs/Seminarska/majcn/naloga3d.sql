select tribe as 'Katero pleme je najštevilčnejše?' from pleme
where tid = 
(
  select tid from igralec
  group by tid
  order by COUNT(*) desc
  limit 1
)