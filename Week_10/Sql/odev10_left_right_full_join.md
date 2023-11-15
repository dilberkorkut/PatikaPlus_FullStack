### odev 10
#### Left - Right - Full Join

1. city tablosu ile country tablosunda bulunan şehir (city) ve ülke (country) isimlerini birlikte görebileceğimiz LEFT JOIN sorgusunu yazınız.

SELECT city.city, country.country FROM city
LEFT JOIN country ON city.country_id = country.country_id;

2. customer tablosu ile payment tablosunda bulunan payment_id ile customer tablosundaki first_name ve last_name isimlerini birlikte görebileceğimiz RIGHT JOIN sorgusunu yazınız.

SELECT p.payment_id, c.first_name, c.last_name FROM customer c
INNER JOIN payment p ON c.customer_id = p.customer_id;

SELECT p.payment_id, c.first_name, c.last_name FROM customer c
RIGHT JOIN payment p ON c.customer_id = p.customer_id;

3. customer tablosu ile rental tablosunda bulunan rental_id ile customer tablosundaki first_name ve last_name isimlerini birlikte görebileceğimiz FULL JOIN sorgusunu yazınız.

SELECT r.rental_id, c.first_name, c.last_name FROM rental r
FULL JOIN customer c ON r.customer_id = c.customer_id;