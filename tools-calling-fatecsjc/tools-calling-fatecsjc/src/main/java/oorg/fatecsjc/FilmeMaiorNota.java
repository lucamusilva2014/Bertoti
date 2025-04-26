package oorg.fatecsjc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmeMaiorNota {

    public static void main(String[] args) {
        String maiorNota = buscarFilmeComMaiorNota();
        System.out.println(maiorNota); // Exibe o resultado
    }

    public static String buscarFilmeComMaiorNota() {
        String maiorNota = "";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:filmes.db")) {
            String sql = "SELECT nome, ano, genero, nota_imdb FROM filmes ORDER BY nota_imdb DESC LIMIT 1";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    maiorNota = "Filme com maior nota IMDb:\n" +
                            "Nome: " + rs.getString("nome") +
                            ", Ano: " + rs.getInt("ano") +
                            ", Gênero: " + rs.getString("genero") +
                            ", Nota IMDb: " + rs.getDouble("nota_imdb");
                } else {
                    maiorNota = "Nenhum filme encontrado no banco de dados.";
                }
            }
        } catch (SQLException e) {
            maiorNota = "Erro ao buscar o filme: " + e.getMessage();
        }
        return maiorNota;
    }
}
