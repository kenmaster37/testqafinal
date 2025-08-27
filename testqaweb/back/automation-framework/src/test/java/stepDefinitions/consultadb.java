package stepDefinitions;

import io.cucumber.java.en.Then;
import java.sql.*;
import org.junit.Assert;

public class consultadb {

    @Then("I list all the users in the giova table")
    public void list_all_users() throws SQLException {
        String url = "jdbc:postgresql://mainline.proxy.rlwy.net:14183/railway";
        String dbUser = "postgres";
        String dbPassword = "GlfxfjlfBzngVOphqcLnWmjjlcLsurYv"; // usa tu contraseña real

        Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
        Statement stmt = conn.createStatement();

        // Ejecutar un SELECT genérico
        ResultSet rs = stmt.executeQuery("SELECT * FROM giova;");

        // Obtener metadata (nombres de columnas)
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        System.out.println("Contenido de la tabla giova:");

        // Imprimir cada fila con columnas dinámicamente
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(meta.getColumnName(i) + ": " + rs.getString(i) + "  ");
            }
            System.out.println(); // salto de línea por fila
        }

        rs.close();
        stmt.close();
        conn.close();
    }
}
