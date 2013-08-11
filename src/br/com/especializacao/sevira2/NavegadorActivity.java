package br.com.especializacao.sevira2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NavegadorActivity extends Activity {

	private WebView meuWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navegador);
		
		meuWebView = (WebView) findViewById(R.id.webView1);
		meuWebView.getSettings().setJavaScriptEnabled(true);
		meuWebView.setWebViewClient(new CustomViewClient());
		meuWebView.loadUrl(getIntent().getStringExtra("url"));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.navegador, menu);
		return true;
	}

}

 class CustomViewClient extends WebViewClient{
	
	 
	 public boolean shouldOverrideUrlLoanding(WebView view, String url){
		 return false;
	 }
}
