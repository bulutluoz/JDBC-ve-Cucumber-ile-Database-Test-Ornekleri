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
        String actualDeger = ReusableMethods.resultSet.getString("username");
        Assert.assertEquals(actualDeger,expectedDeger);
    }

    @Then("database baglantisini kapatir")
    public void database_baglantisini_kapatir() {
        ReusableMethods.closeMyConnection();
    }
}












