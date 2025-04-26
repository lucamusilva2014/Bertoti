package oorg.fatecsjc;

import java.time.LocalDateTime;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MaiorNotaTool {

    private FilmeMaiorNota filmemaiornota;

    public MaiorNotaTool() {
        // Instância da classe responsável pela lógica do banco de dados
        filmemaiornota = new FilmeMaiorNota();
    }

    @Tool(name = "Filme com maior nota")
    public void printMaiorNota() {
        // Busca o filme com maior nota e imprime no console
        String maiorNota = FilmeMaiorNota.buscarFilmeComMaiorNota();
        System.out.println(maiorNota);
    }
}
