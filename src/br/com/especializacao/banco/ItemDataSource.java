package br.com.especializacao.banco;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ItemDataSource {
	// Database fields
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;


	public ItemDataSource(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	public void close() {
		dbHelper.close();
	}
	
	public List<Item> ListAllItem(long id_compra) {
		List<Item> itens = new ArrayList<Item>();
 
		Cursor cursor = database.rawQuery("SELECT _id,  nome, valor,quantidade, compras_id FROM item WHERE compras_id ="+id_compra,null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Item item = cursorToItem(cursor);
			itens.add(item);
			cursor.moveToNext(); 
		}
		// Make sure to close the cursor
		cursor.close();
		return itens;
	}
	private Item cursorToItem(Cursor cursor) {
		Item itens = new Item();
		itens.setId(cursor.getInt(0));
		itens.setNome(cursor.getString(1));
        itens.setValor(cursor.getString(2));
        itens.setQuantidade(cursor.getString(3));
        itens.setCompras_id(cursor.getLong(4));

		return itens;
	}
	
	public Integer qtdeItem(long id_compra) {
		Integer id ;
		Cursor cursor =
				database.rawQuery("SELECT _id  FROM item WHERE compras_id ="+id_compra,null);
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

	public void deleteItem(long id_item) {

		database.delete("item", "_id="+ id_item,null);
	
		database.close();

	}

	public void updateItem(long id_item, String valor) {


		ContentValues values = new ContentValues();
		values.put("_id",id_item);
		values.put("valor",valor.toString());
 	database.update("item", values, "_id = "+ id_item, null);

		database.close();

	}
	
	
	public String nomeItem(long id_item) {
		String nome ="";
		Cursor cursor =
				database.rawQuery("SELECT nome  FROM item WHERE _id ="+id_item,null);
		cursor.moveToFirst();
		if (cursor.getCount() != 0) { 
			nome= cursor.getString(0);
		}
		 

		cursor.close();
		return nome;


	}


}
