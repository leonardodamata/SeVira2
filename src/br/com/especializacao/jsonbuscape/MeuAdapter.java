package br.com.especializacao.jsonbuscape;

import java.util.ArrayList;

import br.com.especializacao.sevira2.R;

import android.content.Context;
import android.util.Log;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MeuAdapter  extends ArrayAdapter<Categoria>{

	private Context context;
	private int layoutResourceId;
	private ArrayList<Categoria> categorias= null;
	
	public MeuAdapter(Context context, int layout,ArrayList<Categoria> categorias) {
	
		super(context, layout, categorias);
		this.context = context;
		this.layoutResourceId = layout;
		this.categorias = categorias;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		View linha = convertView;
		
		//Carrega o layout
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		linha = inflater.inflate(layoutResourceId, parent, false);
		
		
		//personaliza o layout
		
		TextView nome = (TextView) linha.findViewById(R.id.nome);
		nome.setText(categorias.get(position).getNome());
		
		TextView id = (TextView) linha.findViewById(R.id.id);
		id.setText(categorias.get(position).getId());
		
	
		//retorno a linha pronta
		
		return linha;
	}
	
	public int getCount(){
		Log.d("DEBUG","Existem "+ categorias.size() + "categorias");
		return categorias.size();
	}
	
	
	
}
