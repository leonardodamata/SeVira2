package br.com.especializacao.sevira2;

import java.util.ArrayList;

import br.com.especializacao.jsonbuscape.Categoria;
import br.com.especializacao.jsonbuscape.CategoriasDownloader;
import br.com.especializacao.jsonbuscape.MeuAdapter;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class ListaActivity extends ListActivity {

	private ArrayList<Categoria> categorias;
	private MeuAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_lista);
		 configureActionBar();
		 categorias = new ArrayList<Categoria>();
			adapter = new MeuAdapter (this,R.layout.row_site,categorias);
			
			setListAdapter(adapter);
			
			leituraCategorias();
	}

	 private void configureActionBar() {
	        ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
	        actionBar.setTitle(R.string.opcao);
	        actionBar.setHomeAction(new MainAction());
	        actionBar.addAction (new BackAction()); 
	        actionBar.setDisplayHomeAsUpEnabled(true);
	    }
	 
		private void leituraCategorias(){
			CategoriasDownloader atualizador = new CategoriasDownloader(this);
			atualizador.execute();

		}

		@Override
		protected void onListItemClick(ListView  l, View v, int position, long id){
			super.onListItemClick(l,v,position,id);
			
		/////////	Intent i = new  Intent(this,NavegadorActivity.class);
		//	i.putExtra("url",sites.get(position).getUrl());
		//(i);
			
		}
	
		
		public void atualizaItens(ArrayList<Categoria> minhasCategorias){
			this.categorias.clear();
			this.categorias.addAll(minhasCategorias);
			adapter.notifyDataSetChanged();
	 
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	  public class MainAction extends AbstractAction {
		  
	        public MainAction() {
	            super(R.drawable.home);
	             
	        }
	 
	        @Override
	        public void performAction(View view) {
	        	 Intent i = new Intent(getApplicationContext(), MainActivity.class);
				  startActivity(i);
	 
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
