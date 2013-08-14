package br.com.especializacao.sevira2;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import br.com.especializacao.json.Bebida_alcoolicaDownloader;
import br.com.especializacao.json.Bebida_nao_alcoolicaDownloader;
import br.com.especializacao.json.BomboniereDownloader;
import br.com.especializacao.json.CarnesDownloader;
import br.com.especializacao.json.CongeladosDownloader;
import br.com.especializacao.json.FriosDownloader;
import br.com.especializacao.json.HortifrutigranjeirosDownloader;
import br.com.especializacao.json.LaticiniosDownloader;
import br.com.especializacao.json.MatinaisDownloader;
import br.com.especializacao.json.MerceariaDownloader;
import br.com.especializacao.json.Padaria_confeitariaDownloader;
import br.com.especializacao.json.Papinha_para_bebeDownloader;
import br.com.especializacao.json.Sobremesas_confeitariaDownloader;
import br.com.especializacao.json.Subcategory;
import br.com.especializacao.json.SubcategoryAdapter;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

public class ListaItemActivity extends  ListActivity  {

	private ArrayList<Subcategory> subcategorys;
	private SubcategoryAdapter adapter;
	private String id_category;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_lista_item);

		Intent i = getIntent();
		id_category = i.getStringExtra("id");
		
		configureActionBar();
	    subcategorys = new ArrayList<Subcategory>();
		adapter = new SubcategoryAdapter (this,R.layout.row_subcategory,subcategorys);
		
		setListAdapter(adapter);
		
		leituraSubcategory();    

	}




private void leituraSubcategory(){

	
	if (id_category.equals("935")){
	Bebida_alcoolicaDownloader atualizador = new Bebida_alcoolicaDownloader(this);
	atualizador.execute();
	}
	
	if (id_category.equals("913")){
	Bebida_nao_alcoolicaDownloader atualizador = new Bebida_nao_alcoolicaDownloader(this);
	atualizador.execute();

	}
	
	if (id_category.equals("487")){
		BomboniereDownloader atualizador = new BomboniereDownloader(this);
		atualizador.execute();

		}
	
	if (id_category.equals("918")){
		CarnesDownloader atualizador = new CarnesDownloader(this);
		atualizador.execute();

		}
	if (id_category.equals("930")){
		CongeladosDownloader atualizador = new CongeladosDownloader(this);
		atualizador.execute();

		}
	if (id_category.equals("2016")){
		FriosDownloader atualizador = new FriosDownloader(this);
		atualizador.execute();

		}
	
	if (id_category.equals("912")){
		HortifrutigranjeirosDownloader atualizador = new HortifrutigranjeirosDownloader(this);
		atualizador.execute();

		}
	if (id_category.equals("614")){
		LaticiniosDownloader atualizador = new LaticiniosDownloader(this);
		atualizador.execute();

		}
	
	if (id_category.equals("517")){
		MatinaisDownloader atualizador = new MatinaisDownloader(this);
		atualizador.execute();

		}
	if (id_category.equals("564")){
		MerceariaDownloader atualizador = new MerceariaDownloader(this);
		atualizador.execute();

		}
	if (id_category.equals("917")){
		Padaria_confeitariaDownloader atualizador = new Padaria_confeitariaDownloader(this);
		atualizador.execute();

		}
	if (id_category.equals("500")){
		Papinha_para_bebeDownloader atualizador = new Papinha_para_bebeDownloader(this);
		atualizador.execute();

		}
	if (id_category.equals("910")){
		Sobremesas_confeitariaDownloader atualizador = new Sobremesas_confeitariaDownloader(this);
		atualizador.execute();

		}


}

@Override
protected void onListItemClick(ListView  l, View v, int position, long id){
	super.onListItemClick(l,v,position,id);
		
	Intent i = new  Intent(this,CreateListActivity.class);
	i.putExtra("id",subcategorys.get(position).getId());
	i.putExtra("name",subcategorys.get(position).getName());
	startActivity(i);
	
}
@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.lista, menu);
	return true;
}

public void atualizaItens(ArrayList<Subcategory> meusSubcategorys){
	this.subcategorys.clear();
	this.subcategorys.addAll(meusSubcategorys);
	adapter.notifyDataSetChanged();

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
	          Intent i = new Intent(ListaItemActivity.this, MainActivity.class);
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