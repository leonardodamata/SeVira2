package br.com.especializacao.banco;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
	private static final String BANCO_DADOS = "ListaCompras";
	private static int VERSAO = 1;
	public static final String TABLE_COMPRAS = "compras";
	public static final String TABLE_ITEM = "item";
	public DatabaseHelper(Context context) {
		super(context, BANCO_DADOS, null, VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE compras (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				" nome TEXT, data_compra DATE);");

		db.execSQL("CREATE TABLE item (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				" nome TEXT,  valor DOUBLE," +
				" quantidade DOUBLE" +
				"compras_id INTEGER," +
				" FOREIGN KEY(compras_id) REFERENCES compras(_id));");
	}

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(DatabaseHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPRAS);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
	
	    onCreate(db);
	  }

}
