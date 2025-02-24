package salacinema;

import java.util.List;
import java.util.LinkedList;

public class Cinema {
	
	private List<Filme> filmes = new LinkedList<Filme>();
	
	public void addFilme(Filme filme) {
		filmes.add(filme);
		
	}
	
	public List<Filme> getFilmes(){
		return filmes;
	}
	
	public List<Filme> buscarFilmeNome(String nome){
		List<Filme> encontrados = new LinkedList<Filme>();
		for(Filme filme:filmes) {
			if(filme.getNome().equals(nome)) encontrados.add(filme);
		}
		return encontrados;
	}

}