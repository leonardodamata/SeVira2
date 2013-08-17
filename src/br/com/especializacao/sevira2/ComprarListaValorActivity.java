package br.com.especializacao.sevira2;

import java.util.List;

import br.com.especializacao.banco.Compra;
import br.com.especializacao.banco.Item;
import br.com.especializacao.banco.ItemDataSource;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;



import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public  class ComprarListaValorActivity  extends Activity implements OnItemClickListener  {

	private ItemDataSource datasource;
	private long id_lista;
	private ListView lViewChekBox;
	List<Item> values;
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

		/*if(qdte==0){
			Toast.makeText(getApplicationContext(), "Nenhum Registro encontrado",Toast.LENGTH_SHORT).show();
			Intent ic = new Intent(getApplicationContext(), ComprarListaActivity.class);

			startActivity(ic);

		}else{

			Toast.makeText(getApplicationContext(), "Carregando itens!",Toast.LENGTH_SHORT).show();


		} 
*/
		List<Item> values = datasource.ListAllItem(id_lista);



		lViewChekBox = (ListView) findViewById(android.R.id.list);
		lViewChekBox.setAdapter(new ArrayAdapter<Item>(this,android.R.layout.simple_list_item_multiple_choice,  values) );   
		lViewChekBox.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


		lViewChekBox.setOnItemClickListener(this);
		lViewChekBox.setActivated(false) ;


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comprar_lista_valor, menu);
		return true;
	}


	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


		Item item = null;
		item = (Item) lViewChekBox.getItemAtPosition(position);


		//// Chamar a activity tela de compras
		if(lViewChekBox.isActivated()== false)
		{

			Intent i = new Intent(getApplicationContext(), CadastrarValorActivity.class);
			i.putExtra("id",item.getId());
			i.putExtra("id_lista",id_lista);
			startActivity(i);  

			Toast.makeText(getApplicationContext(), "marcando!",Toast.LENGTH_SHORT).show();
			lViewChekBox.setActivated(true) ;
		}
		else
		{

			Toast.makeText(getApplicationContext(), "desmarcando!",Toast.LENGTH_SHORT).show();
			lViewChekBox.setActivated(false) ;

			/*valor = valor - listaDeValor2[position];
			total = total - listaDeQuantidades2[position];	
			listaDeValor2[position] = 0;
			status[position] = false; 


			posicao = position;

			status2[posicao] = false;

			totalValor.setText("Valor Total: R$" + String.valueOf(valor+",00"));
			totalPeso.setText("Total Itens: " + String.valueOf(total));


			onResume();*/

		}

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
		super.onResume();
	
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}




}
