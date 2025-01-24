-- SELECT a.id , a.title , count(c.id) as numOfComment
-- FROM articles a, comments c
-- WHERE a.id = c.article AND a.isLiked = true
-- GROUP BY a.id 
-- ORDER BY numOfComment DESC
-- LIMIT 3;


SELECT a.id , a.title , count(c.id) as numOfComment
FROM articles a, comments c
WHERE a.id = c.article AND a.isLiked = true
GROUP BY a.id 
ORDER BY numOfComment DESC
LIMIT 3;