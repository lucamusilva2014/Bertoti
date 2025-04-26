package oorg.fatecsjc;

import dev.langchain4j.service.SystemMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(tools = {MaiorNotaTool.class, MaiorBilheteriaTool.class})
public interface FATECService {

    @SystemMessage("Mostra o filme cadastrado no banco de dados com a maior nota no imdb")
    String input(String input);

}