package br.com.especializacao.banco;

public class Item {
	private long id;
	private String nome;
	private String valor;
	private String quantidade;
	private long compras_id;
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
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public long getCompras_id() {
		return compras_id;
	}
	public void setCompras_id(long compras_id) {
		this.compras_id = compras_id;
	}
	@Override
	public String toString() {
		return "nome=" + nome + ", valor=" + valor + ", quantidade="
				+ quantidade;
	}
	
	
}
