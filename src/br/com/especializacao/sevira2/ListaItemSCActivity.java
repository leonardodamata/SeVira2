package br.com.especializacao.sevira2;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import br.com.especializacao.json.Bebida_alcoolicaSCDownloader;
import br.com.especializacao.json.Bebida_nao_alcoolicaSCDownloader;
import br.com.especializacao.json.BomboniereSCDownloader;
import br.com.especializacao.json.CarnesSCDownloader;
import br.com.especializacao.json.CongeladosSCDownloader;
import br.com.especializacao.json.FriosSCDownloader;
import br.com.especializacao.json.HortifrutigranjeirosSCDownloader;
import br.com.especializacao.json.LaticiniosSCDownloader;
import br.com.especializacao.json.MatinaisSCDownloader;
import br.com.especializacao.json.MerceariaSCDownloader;
import br.com.especializacao.json.Padaria_confeitariaSCDownloader;
import br.com.especializacao.json.Papinha_para_bebeSCDownloader;
import br.com.especializacao.json.Sobremesas_confeitariaSCDownloader;
import br.com.especializacao.json.Subcategory;
import br.com.especializacao.json.SubcategoryAdapter;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

public class ListaItemSCActivity extends ListActivity {

	private ArrayList<Subcategory> subcategorys;
	private SubcategoryAdapter adapter;
	private String id_category;
	private Integer id_lista;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_lista_item_sc);
	
		Intent i = getIntent();
		id_lista = i.getIntExtra("id_lista",0);
		id_category = i.getStringExtra("id");
		
		configureActionBar();
	    subcategorys = new ArrayList<Subcategory>();
		adapter = new SubcategoryAdapter (this,R.layout.row_subcategory,subcategorys);
		
		setListAdapter(adapter);
		
		leituraSubcategory();    

	}




private void leituraSubcategory(){

	
	if (id_category.equals("935")){
	Bebida_alcoolicaSCDownloader atualizador = new Bebida_alcoolicaSCDownloader(this);
	atualizador.execute();
	}
	
	if (id_category.equals("913")){
	Bebida_nao_alcoolicaSCDownloader atualizador = new Bebida_nao_alcoolicaSCDownloader(this);
	atualizador.execute();

	}
	
	if (id_category.equals("487")){
		BomboniereSCDownloader atualizador = new BomboniereSCDownloader(this);
		atualizador.execute();

		}
	
	if (id_category.equals("918")){
		CarnesSCDownloader atualizador = new CarnesSCDownloader(this);
		atualizador.execute();

		}
	if (id_category.equals("930")){
		CongeladosSCDownloader atualizador = new CongeladosSCDownloader(this);
		atualizador.execute();

		}
	if (id_category.equals("2016")){
		FriosSCDownloader atualizador = new FriosSCDownloader(this);
		atualizador.execute();

		}
	
	if (id_category.equals("912")){
		HortifrutigranjeirosSCDownloader atualizador = new HortifrutigranjeirosSCDownloader(this);
		atualizador.execute();

		}
	if (id_category.equals("614")){
		LaticiniosSCDownloader atualizador = new LaticiniosSCDownloader(this);
		atualizador.execute();

		}
	
	if (id_category.equals("517")){
		MatinaisSCDownloader atualizador = new MatinaisSCDownloader(this);
		atualizador.execute();

		}
	if (id_category.equals("564")){
		MerceariaSCDownloader atualizador = new MerceariaSCDownloader(this);
		atualizador.execute();

		}
	if (id_category.equals("917")){
		Padaria_confeitariaSCDownloader atualizador = new Padaria_confeitariaSCDownloader(this);
		atualizador.execute();

		}
	if (id_category.equals("500")){
		Papinha_para_bebeSCDownloader atualizador = new Papinha_para_bebeSCDownloader(this);
		atualizador.execute();

		}
	if (id_category.equals("910")){
		Sobremesas_confeitariaSCDownloader atualizador = new Sobremesas_confeitariaSCDownloader(this);
		atualizador.execute();

		}


}

@Override
protected void onListItemClick(ListView  l, View v, int position, long id){
	super.onListItemClick(l,v,position,id);
		
	Intent i = new  Intent(this,CreateItemSCActivity.class);
	i.putExtra("id",subcategorys.get(position).getId());
	i.putExtra("name",subcategorys.get(position).getName());
	i.putExtra("id_category",id_category);
	i.putExtra("id_lista",id_lista);
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
	          Intent i = new Intent(ListaItemSCActivity.this, Main2Activity.class);
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



}