package br.com.especializacao.jsonbuscape;

public class Categoria {
	private String nome;
	///private String url_imagem;
	private String id;

	public Categoria(String nome,  String id) {
		super();
		this.nome = nome;
	
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	

}
