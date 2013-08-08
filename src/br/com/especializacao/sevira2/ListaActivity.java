package br.com.especializacao.sevira2;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ListaActivity extends Activity {

	  TextView txtViewParsedValue;
	    private JSONObject jsonObject;
	 
	    String strParsedValue = null;
	 
	    private String strJSONValue = "{\"FirstObject\":{\"attr1\":\"one value\" ,\"attr2\":\"two value\","
	            +"\"sub\": { \"sub1\":[ {\"sub1_attr\":\"sub1_attr_value\" },{\"sub1_attr\":\"sub2_attr_value\" }]}}}";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_lista);
	    configureActionBar();
	    
	    txtViewParsedValue = (TextView) findViewById(R.id.textView1);
	    
        try {
            parseJSON();
 
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
		 
	
	}
	
	
	 public void parseJSON() throws JSONException
	    {
	        jsonObject = new JSONObject(strJSONValue);
	 
	        JSONObject object = jsonObject.getJSONObject("FirstObject");
	        String attr1 = object.getString("attr1");
	        String attr2 = object.getString("attr2");
	 
	        strParsedValue="Attribute 1 value => "+attr1;
	        strParsedValue+="\n Attribute 2 value => "+attr2;
	 
	        JSONObject subObject = object.getJSONObject("sub");
	        JSONArray subArray = subObject.getJSONArray("sub1");
	 
	        strParsedValue+="\n Array Length => "+subArray.length();
	 
	        for(int i=0; i<subArray.length(); i++)
	        {
	            strParsedValue+="\n"+subArray.getJSONObject(i).getString("sub1_attr").toString();
	        }
	 
	        txtViewParsedValue.setText(strParsedValue);
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
