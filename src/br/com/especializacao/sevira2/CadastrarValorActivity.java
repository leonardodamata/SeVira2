package br.com.especializacao.sevira2;


import java.util.List;

import br.com.especializacao.banco.Item;
import br.com.especializacao.banco.ItemDataSource;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;


import android.app.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.view.Menu;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CadastrarValorActivity extends Activity {

	private ItemDataSource datasource;
	private long id_item;
	private long id_lista;
	private TextView txt_produto;
	private EditText  valor; 
	private String nome_produto;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_cadastrar_valor);

		configureActionBar();

		Intent i = getIntent();
		id_item = i.getLongExtra("id",0);
		id_lista = i.getLongExtra("id_lista", 0);


		datasource = new ItemDataSource(this);
		datasource.open();

		nome_produto = datasource.nomeItem(id_item);
		Toast.makeText(getApplicationContext(), "nome produto!"+nome_produto,Toast.LENGTH_SHORT).show();
		txt_produto = (TextView) this.findViewById(R.id.produto);
		txt_produto.setText(nome_produto);
		valor = (EditText) findViewById(R.id.editValor);

		// ok button
		Button btnOk = (Button) findViewById(R.id.btn_ok);

		// Listening to Nova item button click
		btnOk.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				datasource.updateItem(id_item, valor.getText().toString());

				Toast.makeText(getApplicationContext(), "Registro Salvo com sucesso",Toast.LENGTH_SHORT).show();
				Intent ic = new Intent(CadastrarValorActivity.this, ComprarListaValorActivity.class);
				ic.putExtra("id",id_lista);
				startActivity(ic); 
 
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastrar_valor, menu);
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
			Intent i = new Intent(CadastrarValorActivity.this, Main2Activity.class);
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
