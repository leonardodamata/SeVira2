package br.com.especializacao.sevira2;

import java.util.List;

import br.com.especializacao.banco.Item;
import br.com.especializacao.banco.ItemDataSource;

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


public class ListaItemListActivity extends ListActivity {

	private ItemDataSource datasource;
	private long id_lista;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_lista_item_list);
		configureActionBar();
		
		Intent i = getIntent();
		id_lista = i.getLongExtra("id_lista",0);
	
		datasource = new ItemDataSource(this);
		datasource.open();
		
		List<Item> values = datasource.ListAllItem(id_lista);
		
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
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
			Intent i = new Intent(ListaItemListActivity.this, MainActivity.class);
			startActivity(i);

		}
	}

	private class BackAction extends AbstractAction {

		public BackAction() {
			super(R.drawable.back);
		}

		@Override
		public void performAction(View view) {
			Intent i = new Intent(getApplicationContext(), EditListaActivity.class);
			startActivity(i);
		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_item_list, menu);
		return true;
	}
	
/*	@Override
	protected void onListItemClick(ListView  l, View v, int position, long id){
		super.onListItemClick(l,v,position,id);

		Item item = null;
		item = (Item) getListAdapter().getItem(position);
		
		datasource.deleteItem(item.getId());
		Toast.makeText(getApplicationContext(), "Apagado com sucesso!",Toast.LENGTH_SHORT).show();
		Intent i = new Intent(getApplicationContext(), ListaItemListActivity.class);
		startActivity(i);
		

	}*/

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
