package br.com.especializacao.sevira2;

import br.com.especializacao.banco.DatabaseHelper;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateItemActivity extends Activity {

	private TextView txt_produto;
	private String nome_produto, id_category;
	private Integer id_lista;
	private EditText quantidade, valor;
	private DatabaseHelper helper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_create_item);
		txt_produto = (TextView) this.findViewById(R.id.produto);

		configureActionBar();
		Intent i = getIntent();
		nome_produto = i.getStringExtra("nome_produto");
		id_lista = i.getIntExtra("id_lista", 0);
		id_category = i.getStringExtra("id_category");

		txt_produto.setText(nome_produto);

		quantidade = (EditText) findViewById(R.id.editQuantidade);
		valor = (EditText) findViewById(R.id.editValor);

		helper = new DatabaseHelper(this);


		// ok button
		Button btnOk = (Button) findViewById(R.id.btn_ok);

		// ok e novo button
		Button btnOkNew= (Button) findViewById(R.id.btn_save_continue);

		// cancell button
		Button btnCancel = (Button) findViewById(R.id.btn_cancel);

		/**
		 * Handling all button click events
		 * */
		// Listening to Nova item button click
		btnOk.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				SQLiteDatabase db = helper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("nome",nome_produto.toString());
				values.put("quantidade",quantidade.getText().toString());
				values.put("valor",valor.getText().toString());
				values.put("compras_id",id_lista.toString());


				long resultado = db.insert("item", null, values);

				if(resultado != -1 ){
					Toast.makeText(getApplicationContext(), "Registro Salvo e Retornado a tela principal",Toast.LENGTH_SHORT).show();
					Intent i = new Intent(CreateItemActivity.this, Main2Activity.class);
					startActivity(i); 

				}else{
					Toast.makeText(getApplicationContext(), "Registro Não Salvo",Toast.LENGTH_SHORT).show();
				}

				db.close();

			}
		});

		// Listening to Nova item continuar button click
		btnOkNew.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				id_lista = listarUltimaCompra();

				SQLiteDatabase db = helper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("nome",nome_produto.toString());
				values.put("quantidade",quantidade.getText().toString());
				values.put("valor",valor.getText().toString());
				values.put("compras_id",id_lista);


				long resultado = db.insert("item", null, values);

				if(resultado != -1 ){
					Toast.makeText(getApplicationContext(),  "Registro Salvo e Retornado tela itens",Toast.LENGTH_SHORT).show();
					Intent i = new  Intent(CreateItemActivity.this,ListaSCActivity.class);
					i.putExtra("id_lista",id_lista);
					i.putExtra("id_category",id_category);
					startActivity(i);

				}else{
					Toast.makeText(getApplicationContext(), "Registro Não Salvo",Toast.LENGTH_SHORT).show();
				}

				db.close();


			} 
		});



		// Listening cancel button click
		btnCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				Toast.makeText(getApplicationContext(), "Cancelar", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(CreateItemActivity.this, Main2Activity.class);
				startActivity(i);
			}
		});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_item, menu);
		return true;
	}


	private void configureActionBar() {
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setTitle(R.string.item);
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
			Intent i = new Intent(CreateItemActivity.this, MainActivity.class);
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
	protected void onDestroy() {
		helper.close();
		super.onDestroy();
	}

	private Integer listarUltimaCompra() {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor =
				db.rawQuery("SELECT _id  FROM compras ORDER BY _id DESC ",null);
		cursor.moveToFirst();
		Integer id = cursor.getInt(0);
		db.close();
		return id;


	}

}
