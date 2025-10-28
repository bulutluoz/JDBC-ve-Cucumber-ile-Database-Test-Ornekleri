package manageQueries;

public class LoantechQueries {


    public static String dortSutunBilgisiyleKayitOlusturmaSorgusu(String tabloAdi, String sutun1, String sutun2, String sutun3, String sutun4, String deger1, String deger2, String deger3, String deger4){
        // INSERT INTO users(id,email,username,password) VALUES ('34','testNG@deneme.com','alican','alican34')

        String query = "INSERT INTO "+tabloAdi+"("+sutun1+","+sutun2+","+sutun3+","+sutun4+") VALUES ('"+deger1+"','"+deger2+"','"+ deger3+"','"+deger4+"')";

        return query;
    }


    public static String istenenTablodanIdIleSutunSorgulama(String tabloAdi, String sorguDegeri, String istenenSutun){
        // "users" tablosunda "id" degeri "34" olan kaydin "username" inin "alican" oldugunu test eder
        // SELECT username FROM users WHERE id='34'

        String query = "SELECT "+istenenSutun+" FROM "+tabloAdi+" WHERE id='"+sorguDegeri+"'";

        return query;
    }


}
