package br.com.especializacao.sevira2;



import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_main2);
	    configureActionBar();
	    
	    
	 // Dashboard New Lista button
        Button btn_newlista = (Button) findViewById(R.id.btn_new_lista);
        
     // Dashboard Edit Lista button
      Button btn_editlista = (Button) findViewById(R.id.btn_edit_lista);
     
      
   // Dashboard Apagar Lista button
      Button btn_dellista = (Button) findViewById(R.id.btn_del_lista);
        /**
         * Handling all button click events
         * */
         
        // Listening to Nova lista button click
        btn_newlista.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), ListaActivity.class);
                startActivity(i);
            }
        });
        
        // Listening to Edit lista button click
       btn_editlista.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), EditListaActivity.class);
                startActivity(i);
            }
        }); 
       // Listening to del lista button click
       btn_dellista.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), ApagarListaActivity.class);
                startActivity(i);
            }
        }); 
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
	        	 Intent i = new Intent(Main2Activity.this, MainActivity.class);
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
