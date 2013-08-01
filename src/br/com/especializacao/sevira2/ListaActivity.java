package br.com.especializacao.sevira2;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ListaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_lista);
		 configureActionBar();
	}

	 private void configureActionBar() {
	        ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
	        actionBar.setTitle(R.string.opcao);
	        actionBar.setHomeAction(new MainAction());
	        actionBar.addAction (new BackAction()); 
	        actionBar.setDisplayHomeAsUpEnabled(true);
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	  public class MainAction extends AbstractAction {
		  
	        public MainAction() {
	            super(R.drawable.home);
	             
	        }
	 
	        @Override
	        public void performAction(View view) {
	        	 Intent i = new Intent(getApplicationContext(), MainActivity.class);
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
