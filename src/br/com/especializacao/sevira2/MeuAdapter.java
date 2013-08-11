package br.com.especializacao.sevira2;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MeuAdapter  extends ArrayAdapter<Site>{

	private Context context;
	private int layoutResourceId;
	private ArrayList<Site> sites= null;
	
	public MeuAdapter(Context context, int layout,ArrayList<Site> sites) {
	
		super(context, layout, sites);
		this.context = context;
		this.layoutResourceId = layout;
		this.sites = sites;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View linha = convertView;
		
		//Carrega o layout
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		linha = inflater.inflate(layoutResourceId, parent, false);
		
		
		//personaliza o layout
		
		TextView nome = (TextView) linha.findViewById(R.id.titulo);
		nome.setText(sites.get(position).getNome());
		
		TextView url = (TextView) linha.findViewById(R.id.url);
		url.setText(sites.get(position).getUrl());
		
	
		//retorno a linha pronta
		
		return linha;
	}
	
	@Override
	public int getCount(){
		Log.d("DEBUG","Existem "+ sites.size() + "sites");
		return sites.size();
	}
	
	
	
}
