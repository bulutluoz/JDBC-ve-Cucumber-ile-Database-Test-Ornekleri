Feature: US1002 prepared statement ile users tablosu testleri

  Scenario: TC05 Kayit ekleme testi
      # prepared statement kullanarak
      # Loantech uygulamasındaki users tablosuna
      # id değeri 34, email bilgisi “testNG@deneme.com”,
      # username “alican” ve password “alican34” olan bir kayit ekleyin
      # ve ekleme işleminin gerçekleştiğini doğrulayın.
    Given kullanici loantech database ine baglanir
    When ps users tablosunda id,email,username ve password sutunlarina "34", "testNG@deneme.com", "alican" ve "alican34" degerleri ile bir kayit olusturur
    Then ps users tablosunda id degeri "34" olan kaydin username inin "alican" oldugunu test eder
    And ps database baglantisini kapatir



  Scenario: TC06 Kayit dogrulama testi
      # prepared statement kullanarak
      # Loantech uygulamasındaki users tablosunda
      # id değeri 34, email bilgisi “testNG@deneme.com” olan bir kayit var olduğunu doğrulayın.
    Given kullanici loantech database ine baglanir
    When ps users tablosundaki kayitlari sorgular
    Then resultSet uzerinde id degeri "34" ve email degeri "testNG@deneme.com" olan 1 kayit oldugunu test eder
    And ps database baglantisini kapatir



  Scenario: TC07 Kayit update testi
      # prepared statement kullanarak
      # Loantech uygulamasındaki users tablosunda
      # id değeri 34 olan kaydin email bilgisini “update@deneme.com” yapin
      # ve update işleminin gerçekleştiğini doğrulayın.
    Given kullanici loantech database ine baglanir
    When ps users tablosunda id degeri "34" olan kaydin email bilgisini "update@deneme.com" olarak update eder
    Then ps users tablosunda id degeri "34" olan kaydin email inin "update@deneme.com" oldugunu test eder
    And ps database baglantisini kapatir


  @wip
  Scenario: TC08 Kayit silme testi
      # prepared statement kullanarak
      # Loantech uygulamasındaki users tablosunda id değeri 34 olan kaydi silin
      # ve silme işleminin gerçekleştiğini doğrulayın.
    Given kullanici loantech database ine baglanir
    When ps users tablosunda id degeri "34" olan kaydi siler
    And  ps users tablosundaki kayitlari sorgular
    Then resultSet uzerinde id degeri "34" ve email degeri "update@deneme.com" olan 0 kayit oldugunu test eder
    And ps database baglantisini kapatir

