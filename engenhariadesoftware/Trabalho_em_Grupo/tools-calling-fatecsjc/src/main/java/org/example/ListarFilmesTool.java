package org.example;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class ListarFilmesTool {

    @Tool(name = "Listar Filmes")
    public void listarFilmes() {
        String sql = "SELECT id, nome, ano, nota_imdb, bilheteria FROM filme";

        try (Connection conexao = Conexao.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            System.out.println("Lista de Filmes:");
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                int ano = resultado.getInt("ano");
                double notaImdb = resultado.getDouble("nota_imdb");
                double bilheteria = resultado.getDouble("bilheteria");

                System.out.printf("ID: %d | Nome: %s | Ano: %d | IMDb: %.1f | Bilheteria: %.2f%n",
                        id, nome, ano, notaImdb, bilheteria);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar filmes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
