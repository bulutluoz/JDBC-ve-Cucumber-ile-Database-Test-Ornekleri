
  Feature: US1001 users tablosu testleri
    @wip
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



    Scenario: TC03 Kayit update testi
      # Loantech uygulamasındaki users tablosunda
      # id değeri 34 olan kaydin email bilgisini “update@deneme.com” yapin
      # ve update işleminin gerçekleştiğini doğrulayın.


    Scenario: TC04 Kayit silme testi
      # Loantech uygulamasındaki users tablosunda id değeri 34 olan kaydi silin
      # ve silme işleminin gerçekleştiğini doğrulayın.