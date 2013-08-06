package br.com.especializacao.jsonbuscape;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.especializacao.sevira2.ListaActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class CategoriasDownloader extends AsyncTask<Void,Void,ArrayList<Categoria>>{
	
	private ListaActivity uiLista;
	private ProgressDialog dialog;
	
		
	public CategoriasDownloader(ListaActivity uiLista){
		
		this.uiLista = uiLista;
			
	}
	
	@Override
	protected void onPreExecute(){
		super.onPreExecute();
		dialog = ProgressDialog.show(uiLista,"Aguarde","Carregando Categorias");
	}
	@Override
	protected ArrayList<Categoria> doInBackground(Void... params){
		JSONParser parser = new JSONParser();
		JSONObject json = parser.getJSONFromUrl("http://sandbox.buscape.com/service/findCategoryList/4a57456655795158636b673d/?categoryId=0&format=json");
		return parse (json);
	}
	
	private ArrayList<Categoria> parse(JSONObject json){
		ArrayList<Categoria> array = new ArrayList<Categoria>();
		try{
			JSONArray categoriasList = json.getJSONArray("categorias");
			for (int i=0; i < categoriasList.length(); i++){
	
				JSONObject categoriaEmJSON = new JSONObject(categoriasList.getString(i));
				Categoria categoria = new  Categoria(categoriaEmJSON.getString("nome"), categoriaEmJSON.getString("id"));
				array.add(categoria);
				
			}
		}catch (JSONException e ){
			Log.d("PAU", e.getMessage());
		}	
		
		return array;
		
	}
	
	protected void onPostExecute(ArrayList<Categoria> categorias){
		dialog.dismiss();
		uiLista.atualizaItens(categorias);
	}
	
}
