package stepDefinitions;

import io.cucumber.java.en.Then;
import java.sql.*;
import org.junit.Assert;

public class databasetest {

   @Then("I verify the user {string} exists in the database")
   public void verify_user_exists(String username) throws SQLException {
       String url = "jdbc:postgresql://mainline.proxy.rlwy.net:14183/railway";
       String dbUser = "postgres";
       String dbPassword = "GlfxfjlfBzngVOphqcLnWmjjlcLsurYv";

       Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);

       String query = "SELECT * FROM giova WHERE TRIM(LOWER(username)) = TRIM(LOWER(?))";
       PreparedStatement ps = conn.prepareStatement(query);
       ps.setString(1, username);

       ResultSet rs = ps.executeQuery();
       boolean exists = rs.next();

       if (exists) {
           System.out.println("✅ Usuario '" + username + "' encontrado en la base de datos.");
       } else {
           System.out.println("❌ Usuario '" + username + "' NO encontrado.");
           // Mostrar lo que hay en la tabla para depurar
           Statement s = conn.createStatement();
           ResultSet all = s.executeQuery("SELECT * FROM giova;");
           System.out.println("Contenido actual de giova:");
           while (all.next()) {
               System.out.println("ID: " + all.getInt(1) + ", username: '" + all.getString(2) + "'");
           }
       }

       rs.close();
       ps.close();
       conn.close();

       Assert.assertTrue("User '" + username + "' not found in DB", exists);
   }
}

