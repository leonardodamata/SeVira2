package br.com.especializacao.sevira2;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

import java.util.ArrayList;
import java.util.HashMap;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListaActivity extends ListActivity {


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
		        //	 Intent i = new Intent(Main2Activity.this, MainActivity.class);
					//  startActivity(i);
		 
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
