package org.example;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ApplicationScoped
public class RemoverFilmeTool {

    @Tool(name = "Remover Filme")
    public void removerFilme(int id) {
        String sql = "DELETE FROM filme WHERE id = ?";

        try (Connection conexao = Conexao.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Filme removido com sucesso.");
            } else {
                System.out.println("Nenhum filme encontrado com o ID especificado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover filme: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
