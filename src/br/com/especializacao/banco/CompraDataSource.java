package br.com.especializacao.banco;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class CompraDataSource {
	// Database fields
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;


	public CompraDataSource(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	public void close() {
		dbHelper.close();
	}

	public List<Compra> ListAllCompra() {
		List<Compra> compras = new ArrayList<Compra>();

		Cursor cursor = database.rawQuery("SELECT _id,  nome, data_compra FROM compras ORDER BY _id DESC ",null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Compra compra = cursorToCompra(cursor);
			compras.add(compra);
			cursor.moveToNext(); 
		}
		// Make sure to close the cursor
		cursor.close();
		return compras;
	}
	private Compra cursorToCompra(Cursor cursor) {
		Compra compras = new Compra();
		compras.setId(cursor.getInt(0));
		compras.setNome(cursor.getString(1));
		compras.setData_compra(cursor.getString(2)); 

		return compras;
	}

	public Integer qtdeCompra() {
		Integer id ;
		Cursor cursor =
				database.rawQuery("SELECT _id FROM compras",null);
		cursor.moveToFirst();
		if (cursor.getCount() != 0) { 
			id= 1;
		}
		else
		{
			id= 0;
		}

		cursor.close();
		return id;


	}

	public Compra ListCompra(long id_compra) {
		Compra compras = new Compra();

		Cursor cursor =				
				database.rawQuery("SELECT _id,  nome, data_compra FROM compras  WHERE _id="+id_compra,null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {

			compras.setId(cursor.getInt(0));
			compras.setNome(cursor.getString(1));
			compras.setData_compra(cursor.getString(2)); 

			cursor.moveToNext(); 
		}
		// Make sure to close the cursor
		cursor.close();
		return compras;
	}
	public void updateLista(long id_compra, String nome, String  data_compra) {


		ContentValues values = new ContentValues();
		values.put("_id",id_compra);
		values.put("nome",nome.toString());
		values.put("data_compra",data_compra.toString());



		database.update("compras", values, "_id = "+ id_compra, null);

		database.close();

	}
	
	public void deleteLista(long id_compra) {


	//	ContentValues values = new ContentValues();
	//	values.put("_id",id_compra);
	//	 db.delete("viagem", "_id = ?", where);

		database.delete("item", "compras_id="+ id_compra,null);
		database.delete("compras", "_id="+ id_compra,null);

		database.close();

	}



}
