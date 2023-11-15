### Odev 8
#### Create Table

1. test veritabanınızda employee isimli sütun bilgileri id(INTEGER), name VARCHAR(50), birthday DATE, email VARCHAR(100) olan bir tablo oluşturalım.
CREATE TABLE employee (
id INTEGER,
name VARCHAR(50),
BIRTHDAY DATE,
email VARCHAR(100)
);


2. Oluşturduğumuz employee tablosuna 'Mockaroo' servisini kullanarak 50 adet veri ekleyelim.

SELECT * FROM employee;


3. Sütunların her birine göre diğer sütunları güncelleyecek 5 adet UPDATE işlemi yapalım.

UPDATE employee
SET name = 'Didi',
birthday = '2020-10-12',
email = 'didi@yahoo.com'
WHERE id = 4;


UPDATE employee
SET name = 'Zuzu',
birthday = '2018-03-15',
email = 'zuzu@yahoo.com'
WHERE id = 10;


4.Sütunların her birine göre ilgili satırı silecek 5 adet DELETE işlemi yapalım.

DELETE FROM employee
WHERE id = 7;

DELETE FROM employee
WHERE id = 12;


