package br.com.especializacao.sevira2;

import java.util.Calendar;
 
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class AgendarHoraActivity extends Activity {

	private int dia;
	private int mes;
	private int ano;
	private long id_lista;
	private TimePicker tp;
	private int hora;
	private int minuto;
	PendingIntent pendingIntent1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		setContentView(R.layout.activity_agendar_hora);
		configureActionBar();
		
		Intent i = getIntent();
		id_lista = i.getLongExtra("id_lista",0);
		dia = i.getIntExtra("dia", 0);
		mes = i.getIntExtra("mes", 0);
		ano = i.getIntExtra("ano", 0);
		
		mes = mes+1;

		Button btn_ok = (Button) findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			tp = (TimePicker) findViewById(R.id.timePicker1);
			hora = tp.getCurrentHour();
			minuto = tp.getCurrentMinute();
			
			 Toast.makeText(getApplicationContext(), "id_lista "+id_lista+" dia "+dia+" mes "+mes+" ano "+ ano,Toast.LENGTH_SHORT).show();
			 Toast.makeText(getApplicationContext(), "hora "+hora+" minuto "+minuto,Toast.LENGTH_SHORT).show();
			Toast.makeText(getApplicationContext(), "Agendamento Hora!",Toast.LENGTH_SHORT).show();
		//	aciona();
		}
	});
	
	// cancell button
	Button btnCancel = (Button) findViewById(R.id.btn_cancel);
	
	// Listening cancel button click
			btnCancel.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {

					Toast.makeText(getApplicationContext(), "Cancelar", Toast.LENGTH_SHORT).show();
					Intent i = new Intent(AgendarHoraActivity.this, AgendarDataActivity.class);
					startActivity(i);
				}
			});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agendar_hora, menu);
		return true;
	}
	
	private void aciona() {

	//	Intent mainAux = getIntent();		
		Intent myIntent1 = new Intent(AgendarHoraActivity.this, ComprarListaValorActivity.class);
		
		//id não id_lista
		myIntent1.putExtra("id",id_lista);
	  	    
	    pendingIntent1 = PendingIntent.getActivity(this, 0, myIntent1, 0);
	    AlarmManager alarmManager1 = (AlarmManager)getSystemService(ALARM_SERVICE);
	    Calendar calendar1 = Calendar.getInstance();
	    calendar1.set(ano,mes,dia,hora,minuto,0);
	    alarmManager1.set(AlarmManager.RTC, calendar1.getTimeInMillis(), pendingIntent1);
	    finish();
		//Intent i = new Intent(getApplicationContext(), Main2Activity.class);
	//	startActivity(i);
	}
	
	/*
	private void aciona() {

		Intent mainAux = getIntent();		
		Intent myIntent1 = new Intent(AlarmeActivity.this, ListaFeitaActivity.class);
	    myIntent1.putExtra("listaDeProdutos",mainAux.getStringArrayExtra("listaDeProdutos"));
	    myIntent1.putExtra("listaDeQuantidades",mainAux.getLongArrayExtra("listaDeQuantidades"));
	    myIntent1.putExtra("listaDeUnidadesDeMedidas",mainAux.getStringArrayExtra("listaDeUnidadesDeMedidas"));	    
	    pendingIntent1 = PendingIntent.getActivity(this, 0, myIntent1, 0);
	    AlarmManager alarmManager1 = (AlarmManager)getSystemService(ALARM_SERVICE);
	    Calendar calendar1 = Calendar.getInstance();
	    calendar1.set(ano,mes,dia,hora,minuto,0);
	    alarmManager1.set(AlarmManager.RTC, calendar1.getTimeInMillis(), pendingIntent1);
	    finish();
	}
	*/
	
	private void configureActionBar() {
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setTitle(R.string.hora2);
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
			Intent i = new Intent(AgendarHoraActivity.this, MainActivity.class);
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
