package br.com.especializacao.banco;

import java.util.ArrayList;
import java.util.List;
 
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
	    compras.setData_compra(cursor.getLong(2)); 

		return compras;
	}
	
	public Integer qtdeCompra() {
		Integer id ;
		Cursor cursor =
				database.rawQuery("SELECT _id FROM compras",null);
		cursor.moveToFirst();
		  if (cursor.getCount() != 0) { //HERE IS THE PROBLEM
	          id= 1;
	        }
		  else
		  {
			  id= 0;
		  }
		
		cursor.close();
		return id;


	}

	
}
