package br.com.especializacao.sevira2;

 
import java.util.List;

import br.com.especializacao.banco.Compra;
import br.com.especializacao.banco.CompraDataSource;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EditListaItemActivity extends Activity {

	private long id_lista;
	private TextView txt_produto, txt_data_compra;
	private CompraDataSource datasource;
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
		Toast.makeText(getApplicationContext(), "Compras: "+compra,Toast.LENGTH_SHORT).show();

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
			finish();
		}
	}


}
