package edu.ib.projekt_szczepienia;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    public Connection databaseLink;

    public Connection getConnection(){
      String databaseName = "szczepienia";
      String databaseUser = "ProjektBDAdmin";
      String databasePassword = "Admin123";
      String url = "jdbc:mysql://localhost/" + databaseName;

      try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);


      }catch (Exception e){
          e.printStackTrace();
          e.getCause();
      }
      return databaseLink;
    };
}
