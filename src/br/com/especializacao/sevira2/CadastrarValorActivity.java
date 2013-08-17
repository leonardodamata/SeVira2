package br.com.especializacao.sevira2;


import java.util.List;

import br.com.especializacao.banco.Item;
import br.com.especializacao.banco.ItemDataSource;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;


import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CadastrarValorActivity extends Activity {

	private ItemDataSource datasource;
	private long id_item;
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
		

		datasource = new ItemDataSource(this);
		datasource.open();
		
		nome_produto = datasource.nomeItem(id_item);
		Toast.makeText(getApplicationContext(), "nome produto!"+nome_produto,Toast.LENGTH_SHORT).show();
		txt_produto = (TextView) this.findViewById(R.id.produto);
		txt_produto.setText(nome_produto);
		valor = (EditText) findViewById(R.id.editValor);
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
