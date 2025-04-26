package oorg.fatecsjc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaiorBilheteria {

    public static void main(String[] args) {
        String maiorBilheteria = buscarFilmeComMaiorBilheteria();
        System.out.println(maiorBilheteria); // Exibe o resultado
    }

    public static String buscarFilmeComMaiorBilheteria() {
        String maiorBilheteria = "";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:filmes.db")) {
            String sql = "SELECT nome, ano, genero, bilheteria FROM filmes ORDER BY bilheteria DESC LIMIT 1";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    maiorBilheteria = "Filme com maior bilheteria:\n" +
                            "Nome: " + rs.getString("nome") +
                            ", Ano: " + rs.getInt("ano") +
                            ", Gênero: " + rs.getString("genero") +
                            ", Bilheteria: $" + rs.getDouble("bilheteria") + "M";
                } else {
                    maiorBilheteria = "Nenhum filme encontrado no banco de dados.";
                }
            }
        } catch (SQLException e) {
            maiorBilheteria = "Erro ao buscar o filme: " + e.getMessage();
        }
        return maiorBilheteria;
    }
}
