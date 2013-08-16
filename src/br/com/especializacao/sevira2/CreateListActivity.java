package br.com.especializacao.sevira2;

import java.util.Calendar;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

import br.com.especializacao.banco.DatabaseHelper;

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
import android.widget.Toast;

public class CreateListActivity extends Activity {


	private DatabaseHelper helper;
	private EditText nome, data_compra;
	private String id_produto, id_category;
	private String nome_produto;
	private Integer id_lista;
	private Integer ano, mes, dia;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_create_list);
		configureActionBar();

		Intent i = getIntent();
		id_produto = i.getStringExtra("id");
		nome_produto = i.getStringExtra("name");
		id_category  = i.getStringExtra("id_category");

		nome = (EditText) findViewById(R.id.editNome);
		Calendar calendar = Calendar.getInstance();
		ano = calendar.get(Calendar.YEAR);
		mes = calendar.get(Calendar.MONTH);
		dia = calendar.get(Calendar.DAY_OF_MONTH);
		data_compra = (EditText) findViewById(R.id.editData);
		data_compra.setText(dia + "/" +  (mes+1)  + "/" + ano);
     
		helper = new DatabaseHelper(this);


		// ok button
		Button btnOk = (Button) findViewById(R.id.btn_ok);

		// cancell button
		Button btnCancel = (Button) findViewById(R.id.btn_cancel);

		/**
		 * Handling all button click events
		 * */

		// Listening to Nova lista button click
		btnOk.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View view) {
				
				SQLiteDatabase db = helper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("nome",nome.getText().toString());
				values.put("data_compra",data_compra.getText().toString());
			
				long resultado = db.insert("compras", null, values);

				if(resultado != -1 ){
					Toast.makeText(getApplicationContext(), "Registro Salvo",Toast.LENGTH_SHORT).show();
					id_lista = listarUltimaCompra();
					Intent i = new  Intent(CreateListActivity.this,CreateItemActivity.class);
					i.putExtra("nome_produto",nome_produto);
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
				Intent i = new Intent(CreateListActivity.this, Main2Activity.class);
				startActivity(i);
			}
		});


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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_list, menu);
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
			Intent i = new Intent(CreateListActivity.this, MainActivity.class);
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