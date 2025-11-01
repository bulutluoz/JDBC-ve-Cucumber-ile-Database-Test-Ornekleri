package stepdefinitions;


import io.cucumber.java.en.*;
import manageQueries.LoantechQueries;
import org.testng.Assert;
import utilities.ReusableMethods;

import java.sql.SQLException;

public class LoantechStepdefinitions {

    @Given("kullanici loantech database ine baglanir")
    public void kullanici_loantech_database_ine_baglanir() {
        ReusableMethods.getMyConnection();
    }

    @When("{string} tablosunda {string},{string},{string} ve {string} sutunlarina {string}, {string}, {string} ve {string} degerleri ile bir kayit olusturur")
    public void tablosunda_ve_sutunlarina_ve_degerleri_ile_bir_kayit_olusturur(String tabloAdi, String sutun1, String sutun2, String sutun3, String sutun4, String deger1, String deger2, String deger3, String deger4) throws SQLException {
       //     When "users" tablosunda "id","email","username" ve "password" sutunlarina "34", "testNG@deneme.com", "alican" ve "alican34" degerleri ile bir kayit olusturur
      //      INSERT INTO users(id,email,username,password) VALUES ('34','testNG@deneme.com','alican','alican34')
        String query = LoantechQueries.dortSutunBilgisiyleKayitOlusturmaSorgusu(tabloAdi,sutun1,sutun2,sutun3,sutun4,deger1,deger2,deger3,deger4);
        ReusableMethods.createStatement();
        ReusableMethods.executeUpdateStatement(query);
    }

    @Then("{string} tablosunda {string} degeri {string} olan kaydin {string} inin {string} oldugunu test eder")
    public void tablosunda_degeri_olan_kaydin_inin_oldugunu_test_eder(String tabloAdi, String sorguSutunu, String sorguDegeri, String istenenSutun, String expectedDeger) throws SQLException {
        // "users" tablosunda "id" degeri "34" olan kaydin "username" inin "alican" oldugunu test eder
        // SELECT username FROM users WHERE id='34'

        String query = LoantechQueries.istenenTablodanIdIleSutunSorgulama(tabloAdi,sorguDegeri,istenenSutun);
        ReusableMethods.createStatement();
        ReusableMethods.resultSet = ReusableMethods.executeSelectStatement(query);
        ReusableMethods.resultSet.first();
        String actualDeger = ReusableMethods.resultSet.getString(istenenSutun);
        Assert.assertEquals(actualDeger,expectedDeger);
    }

    @Then("database baglantisini kapatir")
    public void database_baglantisini_kapatir() {
        ReusableMethods.closeMyConnection();
    }

    @When("{string} tablosundaki kayitlari sorgular")
    public void tablosundaki_kayitlari_sorgular(String tabloAdi) throws SQLException {

        // SELECT * FROM users
        String query = LoantechQueries.istenenTablodakiTumKayitlariSorgulama(tabloAdi);
        ReusableMethods.createStatement();
        ReusableMethods.resultSet = ReusableMethods.statement.executeQuery(query);
    }

    @Then("tabloda {string} degeri {string} ve {string} degeri {string} olan {int} kayit oldugunu test eder")
    public void tabloda_degeri_ve_degeri_olan_kayit_oldugunu_test_eder(String istenenSutun1, String expectedDeger1, String istenenSutun2, String expectedDeger2, Integer expectedKayitSayisi) throws SQLException {
        //  Then tabloda "id" degeri "34" ve "email" degeri "testNG@deneme.com" olan 1 kayit oldugunu test eder

        int actualKayitSayisi = 0;

        while (ReusableMethods.resultSet.next()){

            String satirdakiIstenenDeger1 = ReusableMethods.resultSet.getString(istenenSutun1);
            String satirdakiIstenenDeger2 = ReusableMethods.resultSet.getString(istenenSutun2);

            if (satirdakiIstenenDeger1.equals(expectedDeger1 ) && satirdakiIstenenDeger2.equals(expectedDeger2)){
                actualKayitSayisi++;
            }
        }

        Assert.assertEquals(actualKayitSayisi,expectedKayitSayisi);
    }

    @When("{string} tablosunda {string} degeri {string} olan kaydin {string} bilgisini {string} olarak update eder")
    public void tablosundaDegeriOlanKaydinBilgisiniOlarakUpdateEder(String tabloAdi, String istenenSutun, String istenenDeger, String updateSutunu, String updateDegeri) {
        // When "users" tablosunda "id" degeri "34" olan kaydin "email" bilgisini "update@deneme.com" olarak update eder
        // UPDATE users SET email = 'update@deneme.com' WHERE id = 34;
        String query = LoantechQueries.istenenTablodaIstenenDegereSahipKaydiUpdateEtme(tabloAdi,istenenSutun,istenenDeger,updateSutunu,updateDegeri);

        ReusableMethods.createStatement();

        ReusableMethods.executeUpdateStatement(query);


    }

    @When("{string} tablosunda {string} degeri {string} olan kaydi siler")
    public void tablosunda_degeri_olan_kaydi_siler(String tabloAdi, String istenenSutun, String istenenDeger) {
        // "users" tablosunda "id" degeri "34" olan kaydi siler
        // DELETE FROM users WHERE id = 34;

        String query = LoantechQueries.istenenDegereSahipkaydiSilme(tabloAdi,istenenSutun,istenenDeger);

        ReusableMethods.createStatement();

        ReusableMethods.executeUpdateStatement(query);

    }

    @Then("{string} tablosunda {string} degeri {string} olan bir kayit bulunmadigini test eder")
    public void tablosunda_degeri_olan_bir_kayit_bulunmadigini_test_eder(String tabloAdi, String sorguSutunu, String expectedDeger) throws SQLException {

        // "users" tablosunda "id" degeri "34" olan bir kayit bulunmadigini test eder

        int actualKayitSayisi = 0;

        while (ReusableMethods.resultSet.next()){
            String satirdakiActualDeger = ReusableMethods.resultSet.getString(sorguSutunu);

            if (satirdakiActualDeger.equals(expectedDeger)){
                actualKayitSayisi++;
            }
        }

        Assert.assertEquals(actualKayitSayisi, 0);
    }

    @When("ps users tablosunda id,email,username ve password sutunlarina {string}, {string}, {string} ve {string} degerleri ile bir kayit olusturur")
    public void ps_users_tablosunda_id_email_username_ve_password_sutunlarina_ve_degerleri_ile_bir_kayit_olusturur(String idDegeri, String emailDegeri, String usernameDegeri, String passwordDegeri) throws SQLException {
        // INSERT INTO users (id,email,username,password) VALUES(idDegeri,emailDegeri,usernameDegeri,passwordDegeri)
        String query = LoantechQueries.userstablosuna4DegerGirerekKayitEklemeSorgusu; // ? var
        // INSERT INTO users (id,email,username,password) VALUES(?,?,?,?)

        ReusableMethods.createPreparedStatement(query); // ? var
        ReusableMethods.preparedStatement.setString(1,idDegeri);
        ReusableMethods.preparedStatement.setString(2,emailDegeri);
        ReusableMethods.preparedStatement.setString(3,usernameDegeri);
        ReusableMethods.preparedStatement.setString(4,passwordDegeri);
        // INSERT INTO users (id,email,username,password) VALUES('34','testNG@deneme.com','alican','alican34')

        ReusableMethods.preparedStatement.executeUpdate();

    }

    @Then("ps users tablosunda id degeri {string} olan kaydin username inin {string} oldugunu test eder")
    public void ps_users_tablosunda_id_degeri_olan_kaydin_username_inin_oldugunu_test_eder(String idDegeri, String expectedusername) throws SQLException {
        // SELECT username FROM users WHERE id = 34;
        String query = LoantechQueries.usersTablosundaPsIdIleUsernameSorgusu;
        // SELECT username FROM users WHERE id = ?

        ReusableMethods.createPreparedStatement(query); // ? var
        ReusableMethods.preparedStatement.setString(1,idDegeri);
        // SELECT username FROM users WHERE id = 34

        ReusableMethods.resultSet = ReusableMethods.preparedStatement.executeQuery();

        // ReusableMethods.resultSet.first(); // prepared statement'da first() calismaz
        ReusableMethods.resultSet.next();

        // resultset ilk satira gelmis oldu
        // simdi o satirdaki username bilgisini alip actual deger olarak kaydedelim
        String actualusername = ReusableMethods.resultSet.getString("username");

        Assert.assertEquals(actualusername,expectedusername);


    }

    @Then("ps database baglantisini kapatir")
    public void ps_database_baglantisini_kapatir() {
        ReusableMethods.closeMyPsConnection();

    }

    @When("ps users tablosundaki kayitlari sorgular")
    public void ps_users_tablosundaki_kayitlari_sorgular() throws SQLException {
        // SELECT * FROM users
        String query = LoantechQueries.usersTablosundakiTumKayitlariSorgulama;

        ReusableMethods.createPreparedStatement(query);

        ReusableMethods.resultSet = ReusableMethods.preparedStatement.executeQuery();

    }


    @Then("resultSet uzerinde id degeri {string} ve email degeri {string} olan {int} kayit oldugunu test eder")
    public void resultsetUzerindeIdDegeriVeEmailDegeriOlanKayitOldugunuTestEder(String idDegeri, String emailDegeri, int expectedKayitSayisi) throws SQLException {

        int actualKayitsayisi = 0 ;

        while( ReusableMethods.resultSet.next()){

            String satirdakiIdDegeri = ReusableMethods.resultSet.getString("id");
            String satirdakiEmailDegeri = ReusableMethods.resultSet.getString("email");

            if (satirdakiIdDegeri.equals(idDegeri) && satirdakiEmailDegeri.equals(emailDegeri)){
                actualKayitsayisi++;
            }

        }

        Assert.assertEquals(actualKayitsayisi,expectedKayitSayisi);

    }

    @When("ps users tablosunda id degeri {string} olan kaydin email bilgisini {string} olarak update eder")
    public void ps_users_tablosunda_id_degeri_olan_kaydin_email_bilgisini_olarak_update_eder(String idDegeri, String emailDegeri) throws SQLException {
        // UPDATE users SET email = emailDegeri WHERE id = idDegeri ;

        String query = LoantechQueries.usersTablosundaPsIdIleEmailUpdateSorgusu;
        // UPDATE users SET email = ? WHERE id = ?

        ReusableMethods.createPreparedStatement(query);

        ReusableMethods.preparedStatement.setString(1,emailDegeri);
        ReusableMethods.preparedStatement.setString(2,idDegeri);


        ReusableMethods.preparedStatement.executeUpdate();

    }

    @Then("ps users tablosunda id degeri {string} olan kaydin email inin {string} oldugunu test eder")
    public void ps_users_tablosunda_id_degeri_olan_kaydin_email_inin_oldugunu_test_eder(String idDegeri, String expectedEmailDegeri) throws SQLException {

        // SELECT email FROM users WHERE id = 34
        String query = LoantechQueries.usersTablosundaPsIdIleEmailSorgulama;
        // SELECT email FROM users WHERE id = ?

        ReusableMethods.createPreparedStatement(query);

        ReusableMethods.preparedStatement.setString(1,idDegeri);
        // SELECT email FROM users WHERE id = 34

        ReusableMethods.resultSet = ReusableMethods.preparedStatement.executeQuery();

        ReusableMethods.resultSet.next();
        String actualEmaildegeri = ReusableMethods.resultSet.getString("email");

        Assert.assertEquals(actualEmaildegeri,expectedEmailDegeri);
    }


    @When("ps users tablosunda id degeri {string} olan kaydi siler")
    public void psUsersTablosundaIdDegeriOlanKaydiSiler(String idDegeri) throws SQLException {

        // DELETE FROM users WHERE id = ?

        String query = LoantechQueries.usersTablosundaPsIdIleKayitSilmeSorgusu; // ? var

        ReusableMethods.createPreparedStatement(query);

        ReusableMethods.preparedStatement.setString(1,idDegeri);

        ReusableMethods.preparedStatement.executeUpdate();

    }
}












