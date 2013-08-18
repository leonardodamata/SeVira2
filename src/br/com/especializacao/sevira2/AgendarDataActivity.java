package br.com.especializacao.sevira2;


import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import android.widget.Toast;


public class AgendarDataActivity extends Activity {

	private DatePicker dp;
	private int dia;
	private int mes;
	private int ano;
	private long id_lista;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_agendar_data);
		configureActionBar();

		Intent i = getIntent();
		id_lista = i.getLongExtra("id_lista",0);

		Button btn_ok = (Button) findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dp = (DatePicker) findViewById(R.id.datePicker1);
				dia = dp.getDayOfMonth();
				mes = dp.getMonth();
				ano = dp.getYear();
				
			//	Toast.makeText(getApplicationContext(), "id_lista "+id_lista+" dia "+dia+" mes "+mes+" ano "+ ano,Toast.LENGTH_SHORT).show();
				
				Toast.makeText(getApplicationContext(), "Agendamento Data!",Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getApplicationContext(), AgendarHoraActivity.class);
				i.putExtra("id_lista",id_lista);
				i.putExtra("dia",dia);
				i.putExtra("mes",mes);
				i.putExtra("ano",ano);
				startActivity(i);
			}
		});
		
		// cancell button
		Button btnCancel = (Button) findViewById(R.id.btn_cancel);
		
		// Listening cancel button click
				btnCancel.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View view) {

						Toast.makeText(getApplicationContext(), "Cancelar", Toast.LENGTH_SHORT).show();
						Intent i = new Intent(AgendarDataActivity.this, AgendarListaActivity.class);
						startActivity(i);
					}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agendar_data, menu);
		return true;
	}
	private void configureActionBar() {
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setTitle(R.string.data2);
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
			Intent i = new Intent(AgendarDataActivity.this, MainActivity.class);
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
