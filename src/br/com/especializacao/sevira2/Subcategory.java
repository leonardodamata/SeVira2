package br.com.especializacao.sevira2;

public class Subcategory {
	

	private String id;
	private String url;
	private String name;

	public Subcategory(String id, String url, String name) {
		super();
		this.id = id;
		this.url = url;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
