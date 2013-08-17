package br.com.especializacao.sevira2;

 

import br.com.especializacao.banco.Compra;
import br.com.especializacao.banco.CompraDataSource;
import br.com.especializacao.banco.ItemDataSource;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditListaItemActivity extends Activity {

	private long id_lista;
	private TextView txt_produto, txt_data_compra;
	private CompraDataSource datasource;
	private ItemDataSource datasourceI;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_edit_lista_item);
		configureActionBar();

		Intent i = getIntent();
		id_lista = i.getLongExtra("id",0);



		datasource = new CompraDataSource(this);
		datasource.open();

		Compra compra = datasource.ListCompra(id_lista);


		txt_produto = (TextView) this.findViewById(R.id.editNome);
		txt_data_compra = (TextView) this.findViewById(R.id.editData);

		txt_produto.setText(compra.getNome());
		txt_data_compra.setText( compra.getData_compra());

		
		datasourceI = new ItemDataSource(this);
		datasourceI.open();
		// btn salvar
		Button btn_salvar = (Button) findViewById(R.id.btn_ok);

		// Listening to salvar button click
		btn_salvar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				datasource.updateLista(id_lista, txt_produto.getText().toString(), txt_data_compra.getText().toString());
				Toast.makeText(getApplicationContext(), "Editado com sucesso!",Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getApplicationContext(), EditListaActivity.class);
				startActivity(i);
			}
		});
		// btn deletar
		Button btn_apagar = (Button) findViewById(R.id.btn_del);

		// Listening to salvar button click
		btn_apagar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				datasource.deleteLista(id_lista);
				Toast.makeText(getApplicationContext(), "Apagado com sucesso!",Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getApplicationContext(), EditListaActivity.class);
				startActivity(i);
			}
		});

		// btn cancelar
		Button btn_cancelar = (Button) findViewById(R.id.btn_cancel);

		// Listening to cancelar button click
		btn_cancelar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Toast.makeText(getApplicationContext(), "Retornado para Lista!",Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getApplicationContext(), Main2Activity.class);
				startActivity(i);
			}
		});	
		
		// btn item
		Button btn_item = (Button) findViewById(R.id.btn_plus);

		// Listening to item button click
		btn_item.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				
				
				Integer qdte = datasourceI.qtdeItem(id_lista);

			 	if(qdte==0){
					Toast.makeText(getApplicationContext(), "Nenhum Registro encontrado",Toast.LENGTH_SHORT).show();
					
				}else{

					Toast.makeText(getApplicationContext(), "Indo para os itens da lista!",Toast.LENGTH_SHORT).show();
					Intent i = new Intent(getApplicationContext(), ListaItemListActivity.class);
					i.putExtra("id_lista",id_lista);
					startActivity(i);
			
				} 
			}
		});		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_lista_item, menu);
		return true;
	}



	private void configureActionBar() {
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setTitle(R.string.lista);
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
			Intent i = new Intent(EditListaItemActivity.this, MainActivity.class);
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


}
