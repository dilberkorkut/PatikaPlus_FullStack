### Odev 4 
#### Distinct - Count

1-
SELECT DISTINCT replacement_cost FROM film;

2-
SELECT COUNT(DISTINCT replacement_cost) FROM film;

3-
SELECT COUNT(DISTINCT title) FROM film
WHERE title LIKE 'T%' AND rating='G';

4-
SELECT COUNT(DISTINCT country) FROM country
WHERE country LIKE '_____';

- SELECT COUNT(DISTINCT country) FROM country
  WHERE length (country) = 5; 

5-
SELECT COUNT(DISTINCT city) FROM city
WHERE city LIKE '%r';