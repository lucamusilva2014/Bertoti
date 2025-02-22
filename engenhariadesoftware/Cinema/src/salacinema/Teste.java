package salacinema;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class Teste {

	@Test
	void test() {
		Cinema cinema = new Cinema();
		cinema.addFilme(new Filme("Vingadores","1"));
		
		assertEquals(cinema.getFilmes().size(),1);
		
		List<Filme> filmesEncontrados = cinema.buscarFilmeNome("Vingadores");
		
		assertEquals(filmesEncontrados.get(0).getId(),"1");
	}

}
