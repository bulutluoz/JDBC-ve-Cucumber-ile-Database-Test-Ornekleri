
  Feature: US1001 users tablosu testleri

    Scenario: TC01 Kayit ekleme testi
      # Loantech uygulamasındaki users tablosuna
      # id değeri 34, email bilgisi “testNG@deneme.com”,
      # username “alican” ve password “alican34” olan bir kayit ekleyin
      # ve ekleme işleminin gerçekleştiğini doğrulayın.
    Given kullanici loantech database ine baglanir
    When "users" tablosunda "id","email","username" ve "password" sutunlarina "34", "testNG@deneme.com", "alican" ve "alican34" degerleri ile bir kayit olusturur
    Then "users" tablosunda "id" degeri "34" olan kaydin "username" inin "alican" oldugunu test eder
    And database baglantisini kapatir



    Scenario: TC02 Kayit dogrulama testi
      # Loantech uygulamasındaki users tablosunda
      # id değeri 34, email bilgisi “testNG@deneme.com” olan bir kayit var olduğunu doğrulayın.
    Given kullanici loantech database ine baglanir
    When "users" tablosundaki kayitlari sorgular
    Then tabloda "id" degeri "34" ve "email" degeri "testNG@deneme.com" olan 1 kayit oldugunu test eder
    And database baglantisini kapatir



    Scenario: TC03 Kayit update testi
      # Loantech uygulamasındaki users tablosunda
      # id değeri 34 olan kaydin email bilgisini “update@deneme.com” yapin
      # ve update işleminin gerçekleştiğini doğrulayın.
    Given kullanici loantech database ine baglanir
    When "users" tablosunda "id" degeri "34" olan kaydin "email" bilgisini "update@deneme.com" olarak update eder
    Then "users" tablosunda "id" degeri "34" olan kaydin "email" inin "update@deneme.com" oldugunu test eder
    And database baglantisini kapatir


    @wip
    Scenario: TC04 Kayit silme testi
      # Loantech uygulamasındaki users tablosunda id değeri 34 olan kaydi silin
      # ve silme işleminin gerçekleştiğini doğrulayın.
    Given kullanici loantech database ine baglanir
    When "users" tablosunda "id" degeri "34" olan kaydi siler
    And  "users" tablosundaki kayitlari sorgular
    Then "users" tablosunda "id" degeri "34" olan bir kayit bulunmadigini test eder
    And database baglantisini kapatir