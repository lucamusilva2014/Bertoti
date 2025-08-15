package org.example;

import dev.langchain4j.service.SystemMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(tools = {InserirFilmeTool.class, ListarFilmesTool.class, RemoverFilmeTool.class})
public interface CinemaService {


    @SystemMessage("Insira os dados do filmes (nome, ano, nota_imdb, bilheteria) ou escolha mostrar os filmes")
    String input(String input);

}