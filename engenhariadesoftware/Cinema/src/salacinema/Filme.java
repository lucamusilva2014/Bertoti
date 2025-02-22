package salacinema;

public class Filme {
	private String nome;
	private String id;
	
	public Filme(String nome, String id) {
		this.nome = nome;
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void SetNome(String nome) {
		this.nome = nome;
		
	}
	public String getId() {
		return id;
	}
	public void SetId(String id) {
		this.id = id ;
		
	}
	
}
