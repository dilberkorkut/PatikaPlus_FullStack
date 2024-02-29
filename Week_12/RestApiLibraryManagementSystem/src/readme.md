# Kütüphane Yönetim Sistemi

Bu proje, CRUD operasyonları yapabilen bir Kütüphane Yönetim Sistemi REST API'sini içermektedir.


## Teknolojiler

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- ModelMapper
- Postman


## Proje Yapısı

Proje aşağıdaki bileşenleri içermektedir:

- **ENTITIES**
- **DTO**
- **DAO**
- **CORE**
- **BUSINESS**
- **API**

İş Mantığı ;
- Kategori silme işleminde kitap kontrolü.
- Kitap ödünç alınırken stok kontrolü.



## Uygulama Başlatma

Proje Spring Boot ile geliştirildiği için, uygulamayı ayağa kaldırmak için aşağıdaki adımları izleyebilirsiniz:

1. Proje kaynak kodlarını bilgisayarınıza indirin.
2. PostgreSQL veritabanınızı oluşturun ve bağlantı bilgilerini `application.properties` dosyasında güncelleyin.
3. Projeyi bir IDE'de (IntelliJ IDEA, Eclipse vb.) açın.
4. `RestAbiLibraryManagemenSystem` sınıfını bulun ve çalıştırın.

Uygulama başlatıldığında, [http://localhost:8080](http://localhost:8080) adresinden API'ye erişebilirsiniz.

## Endpoints

Aşağıda, API'nin sunduğu temel endpoint'lerin bir listesi bulunmaktadır:

| Endpoint              | HTTP Metodu | Açıklama                                                                        |
|-----------------------|-------------|---------------------------------------------------------------------------------|
| `/v1/books`           | GET         | Tüm kitapları listele                                                   |
| `/v1/books/{id}`      | GET         | Belirtilen ID'ye sahip kitabı getir                                             |
| `/v1/books`           | POST        | Yeni kitap ekle                                                                 |
| `/v1/books`           | PUT         | Belirtilen ID'ye sahip kitabı güncelle                                          |
| `/v1/books/{id}`      | DELETE      | Belirtilen ID'ye sahip kitabı sil                                               |
| `/v1/categories/{id}` | DELETE      | Belirtilen ID'ye sahip kategoriyi sil, ancak kategoriye bağlı kitap varsa silme. |
| `/v1/borrowings`      | POST        | Yeni kitap ödünç alınması işlemi                                                |
| `/v1/authors/{id}`    | GET         | Belirtilen ID'ye sahip yazarı getir                                             ||
| `/v1/authors`         | GET         | Tüm yazarları listele  ...                                                      | ...         | ...                                                                              |
| `/v1/publısher`       | GET         | Tüm publisherlari listele   ...                                                 | ...         | ...                                                                              |
| `/v1/categorıes`      | GET         | Tüm kategorileri listele  ...                                                   | ...         | ...                                                                              |
| ...                   | ...         | ...                                                                             |

Her endpoint'in detayları için ilgili controller sınıflarını inceleyebilirsiniz.

