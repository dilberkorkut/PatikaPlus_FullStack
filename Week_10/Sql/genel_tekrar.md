### GENEL TEKRAR

1. Isminde en az 4 tane e veya E bulunan kac film vardir?

SELECT COUNT(*) FROM film
WHERE title ILIKE '%e%e%e%e%';

2. Kategori isimleri ve kategori basina dusen film sayisi?

SELECT COUNT(*), category.name
FROM category
JOIN film_category ON film_category.category_id = category.category_id
JOIN film ON film.film_id = film_category.film_id
GROUP BY category.name;

3. En cok film bulunan raiting kategorisi?

SELECT COUNT (*), rating FROM film
GROUP BY rating
ORDER BY COUNT(*) DESC
LIMIT 1;

4. Film tablosunda 'K' ile baslayan en uzun ve replacement_cost en az olan 3 filmi siralayiniz?

SELECT title, length, replacement_cost FROM film
WHERE title LIKE 'K%'
ORDER BY length DESC, replacement_cost ASC
LIMIT 3;

5. En cok alisveris yapan musterinin adi?

SELECT SUM(amount), customer.first_name, customer.last_name FROM payment
JOIN customer ON customer.customer_id = payment.customer_id
GROUP BY payment.customer_id, customer.first_name, customer.last_name
ORDER BY SUM(amount) DESC
LIMIT 1;


