package br.com.especializacao.sevira2;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class SitesDownloader extends AsyncTask<Void,Void,ArrayList<Site>>{
	
	private ListaActivity2 uiLista;
	private ProgressDialog dialog;
	
		
	public SitesDownloader(ListaActivity2 uiLista){
		
		this.uiLista = uiLista;
			
	}
	
	@Override
	protected void onPreExecute(){
		super.onPreExecute();
		dialog = ProgressDialog.show(uiLista,"Aguarde","Carregando Sites");
	}
	@Override
	protected ArrayList<Site> doInBackground(Void... params){
		JSONParser parser = new JSONParser();
		JSONObject json = parser.getJSONFromUrl("https://dl.dropbox.com/s/9rea4tx4aouu1gl/sites.json");
		return parse (json);
	}
	
	private ArrayList<Site> parse(JSONObject json){
		ArrayList<Site> array = new ArrayList<Site>();
		try{
			JSONArray sitesList = json.getJSONArray("sites");
			for (int i=0; i < sitesList.length(); i++){
				JSONObject siteEmJSON = new JSONObject(sitesList.getString(i));
				Site site = new  Site(siteEmJSON.getString("nome"), siteEmJSON.getString("url"),
						 siteEmJSON.getString("descricao"));
				array.add(site);
				
			}
		}catch (JSONException e ){
			Log.d("PAU", e.getMessage());
		}	
		
		return array;
		
	}
	
	@Override
	protected void onPostExecute(ArrayList<Site> sites){
		dialog.dismiss();
		uiLista.atualizaItens(sites);
	}
	
}
