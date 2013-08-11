package br.com.especializacao.sevira2;

public class Site {

	private String nome;
	private String url;
	private String descricao;
	
	public Site(String nome, String url, String descricao) {
		super();
		this.nome = nome;
		this.url = url;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
