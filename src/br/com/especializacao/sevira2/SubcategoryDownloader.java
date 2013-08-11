package br.com.especializacao.sevira2;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class SubcategoryDownloader extends AsyncTask<Void,Void,ArrayList<Subcategory>>{

	private ListaActivity uiLista;
	private ProgressDialog dialog;
	
		
	public SubcategoryDownloader(ListaActivity listaActivity){
		
		this.uiLista = listaActivity;
			
	}@Override
	protected void onPreExecute(){
		super.onPreExecute();
		dialog = ProgressDialog.show(uiLista,"Aguarde","Carregando Categorias");
	}
	@Override
	protected ArrayList<Subcategory> doInBackground(Void... params){
		JSONParser parser = new JSONParser();
		JSONObject json = parser.getJSONFromUrl("http://sandbox.buscape.com/service/findCategoryList/4a57456655795158636b673d/?categoryId=0&format=json");
		return parse (json);
	}
	
	private ArrayList<Subcategory> parse(JSONObject json){
		ArrayList<Subcategory> array = new ArrayList<Subcategory>();
		try{
			JSONArray subcategoryList = json.getJSONArray("subcategory");
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
