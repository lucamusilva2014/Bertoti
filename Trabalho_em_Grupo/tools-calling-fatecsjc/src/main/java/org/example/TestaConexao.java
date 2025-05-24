package org.example;

import java.sql.Connection;

public class TestaConexao {
    public static void main(String[] args) {
        try (Connection conexao = Conexao.obterConexao()) {
            System.out.println("Conexão bem-sucedida!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}