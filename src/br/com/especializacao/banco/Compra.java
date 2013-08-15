package br.com.especializacao.banco;


public class Compra {
	private Integer id;
	private String nome;
	private String data_compra;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData_compra() {
		return data_compra;
	}
	public void setData_compra(String data_compra) {
		this.data_compra = data_compra;
	}

	@Override
	public String toString(){
		return nome;
	}

}
