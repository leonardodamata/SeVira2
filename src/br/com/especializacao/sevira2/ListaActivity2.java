package br.com.especializacao.sevira2;


import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;


import java.util.ArrayList;

 
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;

import android.widget.ListView;



public class ListaActivity2 extends  ListActivity  {

	private ArrayList<Site> sites;
	private MeuAdapter adapter;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_lista);
	    configureActionBar();
	     sites = new ArrayList<Site>();
		adapter = new MeuAdapter (this,R.layout.row_site,sites);
		
		setListAdapter(adapter);
		
		leituraSites();    

	 }


	private void leituraSites(){
		SitesDownloader atualizador = new SitesDownloader(this);
		atualizador.execute();

	}

	@Override
	protected void onListItemClick(ListView  l, View v, int position, long id){
		super.onListItemClick(l,v,position,id);
		
		Intent i = new  Intent(this,NavegadorActivity.class);
		i.putExtra("url",sites.get(position).getUrl());
		startActivity(i);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista, menu);
		return true;
	}
	
	public void atualizaItens(ArrayList<Site> meusSites){
		this.sites.clear();
		this.sites.addAll(meusSites);
		adapter.notifyDataSetChanged();
 
	}


	 private void configureActionBar() {
	        ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
	        actionBar.setTitle(R.string.opcao);
	        actionBar.setHomeAction(new MainAction());
	        actionBar.addAction (new BackAction()); 
	        actionBar.setDisplayHomeAsUpEnabled(true);
	    }


		  public class MainAction extends AbstractAction {

		        public MainAction() {
		            super(R.drawable.home);

		        }

		        @Override
		        public void performAction(View view) {
		        //	 Intent i = new Intent(Main2Activity.this, MainActivity.class);
					//  startActivity(i);

		        }
		    }

		  private class BackAction extends AbstractAction {

		        public BackAction() {
		            super(R.drawable.back);
		        }

		        @Override
				public void performAction(View view) {
		            finish();
		        }
		    }



	}