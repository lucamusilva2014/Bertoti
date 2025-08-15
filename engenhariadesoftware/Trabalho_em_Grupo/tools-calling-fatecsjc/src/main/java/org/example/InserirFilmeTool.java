package org.example;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ApplicationScoped
public class InserirFilmeTool {

    @Tool(name = "Inserir Filme")
    public void inserirFilme(String nome, int ano, double notaImdb, double bilheteria) {
        String sql = "INSERT INTO filme (nome, ano, nota_imdb, bilheteria) VALUES (?, ?, ?, ?)";

        try (Connection conexao = Conexao.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setInt(2, ano);
            stmt.setDouble(3, notaImdb);
            stmt.setDouble(4, bilheteria);

            int linhasAfetadas = stmt.executeUpdate();
            System.out.println(linhasAfetadas + " filme(s) inserido(s) com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir filme: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
