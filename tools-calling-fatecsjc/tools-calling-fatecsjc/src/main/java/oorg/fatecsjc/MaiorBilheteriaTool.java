package oorg.fatecsjc;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MaiorBilheteriaTool {

    private MaiorBilheteria maiorBilheteria;

    public MaiorBilheteriaTool() {
        // Instância da classe responsável pela lógica do banco de dados
       maiorBilheteria = new MaiorBilheteria();
    }

    @Tool(name = "Filme com maior bilheteria")
    public void printMaiorBilheteria() {
        // Busca o filme com maior bilheteria e imprime no console
        String maiorBilheteria = MaiorBilheteria.buscarFilmeComMaiorBilheteria();
        System.out.println(maiorBilheteria);
    }
}
