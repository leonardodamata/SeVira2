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
import android.widget.TextView;
import android.widget.Toast;

public  class ComprarListaValorActivity  extends ListActivity {

	private ItemDataSource datasource; 
	private ItemDataSource datasource2;
	private long id_lista;
	List<Item> values;
	private TextView txt_quantidade, txt_valor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_comprar_lista_valor);

		configureActionBar();

		Intent i = getIntent();
		id_lista = i.getLongExtra("id",0);

		datasource = new ItemDataSource(this);
		datasource.open();

		Integer qdte = datasource.qtdeItem(id_lista);

		if(qdte==0){
			Toast.makeText(getApplicationContext(), "Nenhum Registro encontrado",Toast.LENGTH_SHORT).show();
			Intent ic = new Intent(getApplicationContext(), ComprarListaActivity.class);

			startActivity(ic);

		}else{

			Toast.makeText(getApplicationContext(), "Carregando itens!",Toast.LENGTH_SHORT).show();


		} 

		List<Item> values = datasource.ListAllItem(id_lista);


		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);


		txt_quantidade = (TextView) this.findViewById(R.id.quantidade);
		Integer countQtde =  datasource.countItem(id_lista);
		txt_quantidade.setText(countQtde.toString());
		txt_valor = (TextView) this.findViewById(R.id.valor2);
		 
		datasource2 = new ItemDataSource(this);
		datasource2.open();
		double sumQtde =  datasource2.sumVallorItem(id_lista);
		//Integer test = datasource2.sumVallorItem2(id_lista);
		 txt_valor.setText(String.valueOf(sumQtde));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comprar_lista_valor, menu);
		return true;
	}

	@Override
	protected void onListItemClick(ListView  l, View v, int position, long id){
		super.onListItemClick(l,v,position,id);

		Item item = null;
		item = (Item) getListAdapter().getItem(position);

		Intent i = new Intent(getApplicationContext(), CadastrarValorActivity.class);
		i.putExtra("id",item.getId());
		i.putExtra("id_lista",id_lista);
		startActivity(i);  



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
			Intent i = new Intent(ComprarListaValorActivity.this, Main2Activity.class);
			startActivity(i);

		}
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
		datasource2.open();
		super.onResume();



	}

	@Override
	protected void onPause() {
		datasource.close();
		datasource2.close();
		super.onPause();
	}




}
