package oorg.fatecsjc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FilmesDatabase {

    private static final String DB_URL = "jdbc:sqlite:filmes.db"; // Caminho do banco de dados

    // Método para criar a tabela de filmes
    public void criarTabelaFilmes() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "CREATE TABLE IF NOT EXISTS filmes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT NOT NULL, " +
                    "ano INTEGER NOT NULL, " +
                    "genero TEXT NOT NULL, " +
                    "nota_imdb REAL, " +
                    "bilheteria REAL)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para adicionar um filme ao banco de dados
    public void adicionarFilme(String nome, int ano, String genero, double notaImdb, double bilheteria) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO filmes (nome, ano, genero, nota_imdb, bilheteria) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nome);
                pstmt.setInt(2, ano);
                pstmt.setString(3, genero);
                pstmt.setDouble(4, notaImdb);
                pstmt.setDouble(5, bilheteria);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


// Método para adicionar 20 filmes automaticamente
    public void adicionarVinteFilmes() {
        adicionarFilme("O Poderoso Chefão", 1972, "Drama", 9.2, 245.0);
        adicionarFilme("Forrest Gump", 1994, "Drama", 8.8, 678.2);
        adicionarFilme("Inception", 2010, "Ficção Científica", 8.8, 836.8);
        adicionarFilme("Matrix", 1999, "Ação", 8.7, 463.5);
        adicionarFilme("Avatar", 2009, "Ficção Científica", 7.8, 2788.0);
        adicionarFilme("Titanic", 1997, "Romance", 7.9, 2187.5);
        adicionarFilme("Clube da Luta", 1999, "Drama", 8.8, 100.9);
        adicionarFilme("Star Wars: Episódio IV", 1977, "Ficção Científica", 8.6, 775.4);
        adicionarFilme("Os Vingadores", 2012, "Ação", 8.0, 1518.8);
        adicionarFilme("O Senhor dos Anéis: O Retorno do Rei", 2003, "Fantasia", 9.0, 1142.0);
        adicionarFilme("Os Vingadores", 2012, "Ação", 8.0, 1518.8);
        adicionarFilme("Vingadores: Ultimato", 2019 ,"Ação", 8.4, 2797.5 );
    }

    // Método para listar todos os filmes cadastrados (para testar)
    public void listarFilmes() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT * FROM filmes";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                var rs = pstmt.executeQuery();
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") +
                            ", Nome: " + rs.getString("nome") +
                            ", Ano: " + rs.getInt("ano") +
                            ", Gênero: " + rs.getString("genero") +
                            ", Nota IMDb: " + rs.getDouble("nota_imdb") +
                            ", Bilheteria: $" + rs.getDouble("bilheteria") + "M");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
