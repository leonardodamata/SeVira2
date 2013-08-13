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
import android.widget.Toast;



public class ListaActivity extends  ListActivity  {

	private ArrayList<Subcategory> subcategorys;
	private SubcategoryAdapter adapter;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_lista);
	    configureActionBar();
	    subcategorys = new ArrayList<Subcategory>();
		adapter = new SubcategoryAdapter (this,R.layout.row_subcategory,subcategorys);
		
		Toast.makeText(getApplicationContext(), "Exemplo Toast", Toast.LENGTH_SHORT).show();
		setListAdapter(adapter);
		
		leituraSubcategory();    

	 }


	private void leituraSubcategory(){
		SubcategoryDownloader atualizador = new SubcategoryDownloader(this);
		atualizador.execute();

	}

	@Override
	protected void onListItemClick(ListView  l, View v, int position, long id){
		super.onListItemClick(l,v,position,id);
		Toast.makeText(getApplicationContext(), "Exemplo Toast", Toast.LENGTH_SHORT).show();
		//Intent i = new  Intent(this,SubCategoryItensActivity.class);
		Intent i = new  Intent(this,NavegadorActivity.class);
		i.putExtra("url",subcategorys.get(position).getUrl());
		startActivity(i);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista, menu);
		return true;
	}
	
	public void atualizaItens(ArrayList<Subcategory> meusSubcategorys){
		this.subcategorys.clear();
		this.subcategorys.addAll(meusSubcategorys);
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
		          Intent i = new Intent(ListaActivity.this, MainActivity.class);
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