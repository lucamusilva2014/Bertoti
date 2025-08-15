package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL ="jdbc:mysql://localhost:3306/cinema";
    ;

    private static String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection obterConexao() throws SQLException{
        String driverName = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driverName);
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLException("Erro ao conectar " + e.getMessage());
        }
    }

}