package br.com.especializacao.sevira2;

import java.util.List;

import br.com.especializacao.banco.Compra;
import br.com.especializacao.banco.CompraDataSource;


import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

import android.app.ListActivity;
import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ApagarListaActivity extends ListActivity {
	
	private CompraDataSource datasource;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_apagar_lista);
		configureActionBar();
		

		datasource = new CompraDataSource(this);
		datasource.open();

		Integer qdte = datasource.qtdeCompra();

		if(qdte==0){
			Toast.makeText(getApplicationContext(), "Nenhum Registro encontrado",Toast.LENGTH_SHORT).show();
			Intent i = new Intent(ApagarListaActivity.this, Main2Activity.class);
			startActivity(i); 

		}else{

			List<Compra> values = datasource.ListAllCompra();

			// Use the SimpleCursorAdapter to show the
			// elements in a ListView
			ArrayAdapter<Compra> adapter = new ArrayAdapter<Compra>(this,
					android.R.layout.simple_list_item_1, values);
			setListAdapter(adapter);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.apagar_lista, menu);
		return true;
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
			Intent i = new Intent(ApagarListaActivity.this, Main2Activity.class);
			startActivity(i);

		}
	}
	
	@Override
	protected void onListItemClick(ListView  l, View v, int position, long id){
		super.onListItemClick(l,v,position,id);

		Compra compra = null;
		compra = (Compra) getListAdapter().getItem(position);
		
		datasource.deleteLista(compra.getId());
		Toast.makeText(getApplicationContext(), "Apagado com sucesso!",Toast.LENGTH_SHORT).show();
		Intent i = new Intent(getApplicationContext(), ApagarListaActivity.class);
		startActivity(i);
		

	}

	private class BackAction extends AbstractAction {

		public BackAction() {
			super(R.drawable.back);
		}

		@Override
		public void performAction(View view) {
			Intent i = new Intent(getApplicationContext(), Main2Activity.class);
			startActivity(i);
		}
	}



	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	} 


}
