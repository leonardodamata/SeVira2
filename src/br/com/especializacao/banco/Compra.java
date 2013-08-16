package br.com.especializacao.banco;


public class Compra {
	private long id;
	private String nome;
	private long data_compra;


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getData_compra() {
		return data_compra;
	}
	public void setData_compra(long data_compra) {
		this.data_compra = data_compra;
	}

	
	@Override
	public String toString() {
		return "Compra: id=" + id + ", nome=" + nome + ", data_compra="
				+ data_compra;
	}
	
	
	
	

}
