package br.com.especializacao.sevira2;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryList {
	private List<SubCategoryContainer> posts = new ArrayList<SubCategoryContainer>();
	public List<SubCategoryContainer> getSubCategoryContainterList() {
	return posts;
	}
	}

	class PostContainer{
	Posts post;
	public Posts getPost(){
	return post;
	}
	}

	public class Posts {
	String message;
	String time;
	String username;
	}