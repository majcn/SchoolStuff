SELECT * FROM
  (
    SELECT * FROM x_view
    UNION ALL
    SELECT * FROM x_world
  ) AS compare_table
GROUP BY id, x, y, tid, vid, village, pid, player, aid, alliance, population
HAVING COUNT(*) = 1
ORDER BY id