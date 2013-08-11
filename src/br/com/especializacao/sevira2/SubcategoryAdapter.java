package br.com.especializacao.sevira2;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SubcategoryAdapter   extends ArrayAdapter<Subcategory>{

	private Context context;
	private int layoutResourceId;
	private ArrayList<Subcategory> subcategorys= null;
	
	public SubcategoryAdapter(Context context, int layout,ArrayList<Subcategory> subcategorys) {
	
		super(context, layout, subcategorys);
		this.context = context;
		this.layoutResourceId = layout;
		this.subcategorys = subcategorys;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View linha = convertView;
		
		//Carrega o layout
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		linha = inflater.inflate(layoutResourceId, parent, false);
		
		
		//personaliza o layout
		
		TextView nome = (TextView) linha.findViewById(R.id.titulo);
		nome.setText(subcategorys.get(position).getName());
		
		TextView url = (TextView) linha.findViewById(R.id.url);
		url.setText(subcategorys.get(position).getUrl());
		
	
		//retorno a linha pronta
		
		return linha;
	}
	
	@Override
	public int getCount(){
		Log.d("DEBUG","Existem "+ subcategorys.size() + "subcategorys");
		return subcategorys.size();
	}
	
	
	
}