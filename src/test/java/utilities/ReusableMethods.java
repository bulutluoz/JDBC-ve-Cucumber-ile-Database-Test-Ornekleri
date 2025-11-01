package utilities;

import java.sql.*;

public class ReusableMethods {
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;
    public static PreparedStatement preparedStatement;

    public static void getMyConnection(){
        String url = ConfigReader.getProperty("url");
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createStatement(){
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeSelectStatement(String calisacakQuery){
        createStatement();
        try {
            resultSet = statement.executeQuery(calisacakQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }

    public static void closeMyConnection(){

        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeMyPsConnection(){

        try {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int executeUpdateStatement(String calisacakQuery){
        createStatement();

        int etkilenenSatirSayisi=0;

        try {
            etkilenenSatirSayisi = statement.executeUpdate(calisacakQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return etkilenenSatirSayisi;

    }

    public static void createPreparedStatement(String query){

        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}

