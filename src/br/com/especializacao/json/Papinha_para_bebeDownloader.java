package br.com.especializacao.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.especializacao.sevira2.ListaItemActivity;
 
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class Papinha_para_bebeDownloader extends AsyncTask<Void,Void,ArrayList<Subcategory>>{

	private ListaItemActivity uiLista;
	private ProgressDialog dialog;
	public Papinha_para_bebeDownloader(ListaItemActivity listaActivity){
		
		this.uiLista = listaActivity;
			
	}
	@Override
	protected void onPreExecute(){
		super.onPreExecute();
		dialog = ProgressDialog.show(uiLista,"Aguarde","Carregando Papinha para Bebê");
	}
	@Override
	protected ArrayList<Subcategory> doInBackground(Void... params){
		JSONParser parser = new JSONParser();
		JSONObject json = parser.getJSONFromUrl("https://dl.dropbox.com/s/hnrobxw3jstprlt/papinha_bebe.json");
	
		return parse (json);
	}
	
	private ArrayList<Subcategory> parse(JSONObject json){
		ArrayList<Subcategory> array = new ArrayList<Subcategory>();
		try{
			JSONArray subcategoryList = json.getJSONArray("subcategorys");
			for (int i=0; i < subcategoryList.length(); i++){
				JSONObject subcategoryEmJSON = new JSONObject(subcategoryList.getString(i));
				Subcategory subcategory = new  Subcategory(subcategoryEmJSON.getString("id"),     
						subcategoryEmJSON.getString("url"),
						subcategoryEmJSON.getString("name"));
				array.add(subcategory);
				
			}
		}catch (JSONException e ){
			Log.d("PAU", e.getMessage());
		}	
		
		return array;
		
	}
	
	@Override
	protected void onPostExecute(ArrayList<Subcategory> subcategorys){
		dialog.dismiss();
		uiLista.atualizaItens(subcategorys);
	}
	
}
