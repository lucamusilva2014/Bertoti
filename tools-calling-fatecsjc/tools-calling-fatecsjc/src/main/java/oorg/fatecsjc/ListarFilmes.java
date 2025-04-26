package oorg.fatecsjc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListarFilmes {

    public static void main(String[] args) {
        String filmes = listarTodosOsFilmes();
        System.out.println(filmes); // Exibe o resultado
    }

    public static String listarTodosOsFilmes() {
        StringBuilder filmes = new StringBuilder();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:filmes.db")) {
            String sql = "SELECT nome, ano, genero, nota_imdb, bilheteria FROM filmes";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                filmes.append("Lista de filmes no banco de dados:\n");
                while (rs.next()) {
                    filmes.append("Nome: ").append(rs.getString("nome"))
                            .append(", Ano: ").append(rs.getInt("ano"))
                            .append(", Gênero: ").append(rs.getString("genero"))
                            .append(", Nota IMDb: ").append(rs.getDouble("nota_imdb"))
                            .append(", Bilheteria: $").append(rs.getDouble("bilheteria")).append("M\n");
                }
                if (filmes.toString().equals("Lista de filmes no banco de dados:\n")) {
                    filmes.append("Nenhum filme encontrado no banco de dados.");
                }
            }
        } catch (SQLException e) {
            filmes = new StringBuilder("Erro ao listar os filmes: " + e.getMessage());
        }
        return filmes.toString();
    }
}
