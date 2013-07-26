package br.com.especializacao.sevira2;



import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_main);
	    configureActionBar();
	}
	
	 private void configureActionBar() {
	        ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
	        actionBar.setTitle(R.string.bem_vindo);
	        actionBar.setHomeAction(new MainAction());
	        actionBar.addAction(new EnterAction());
	        actionBar.addAction (new CloseAction());
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
	 
	 
	        }
	    }
	     
	  private class CloseAction extends AbstractAction {
	 
	        public CloseAction() {
	            super(R.drawable.close);
	        }
	 
	        @Override
			public void performAction(View view) {
	            finish();
	        }
	    }
	     
	    private class EnterAction extends AbstractAction {
	 
	        public EnterAction() {
	            super(R.drawable.ok);
	        }
	 
	        @Override
	        public void performAction(View view) {
	            Toast.makeText(view.getContext(), "Entrar no sistema!!", Toast.LENGTH_SHORT).show();    
	        }
	    }

}
