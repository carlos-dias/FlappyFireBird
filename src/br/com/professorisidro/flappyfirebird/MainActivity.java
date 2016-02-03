package br.com.professorisidro.flappyfirebird;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	MinhaView minhaView;
	Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* Ajustando parâmetros globais na Activity;
		 */
		setGlobalParameters();
		handler = new Handler(){
			public void handleMessage(Message msg){
			   super.handleMessage(msg);
			   if (msg.what == 100){
				   mostraViewFinish();
			   }
			}
		};
		
		minhaView = new MinhaView(this);
		minhaView.setHandler(handler);
		
		setContentView(minhaView);
			
		Thread t = new Thread(minhaView);
		t.start();
	}
			
	public void setGlobalParameters(){
		GameParameterSingleton.ORIENTATION   = GameParameterSingleton.PORTRAIT;
		GameParameterSingleton.SCREEN_WIDTH  = getWindowManager().getDefaultDisplay().getWidth();
		GameParameterSingleton.SCREEN_HEIGHT = getWindowManager().getDefaultDisplay().getHeight();
		
		
		GameParameterSingleton.assetManager  = getAssets();
		
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // retrato
        
        /* ajustando outros parâmetros de tela */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);  // tela cheia
        requestWindowFeature(Window.FEATURE_NO_TITLE);                     // sem a barrinha de título
	}
	
	public void onPause(){
		super.onPause();
		this.finish();
	}
	
	public void mostraViewFinish(){
		setContentView(R.layout.activity_main);
		TextView txtPontos = (TextView)findViewById(R.id.txtPontos);
		txtPontos.setText(String.valueOf(GameParameterSingleton.PONTOS));
		
		Button btnAgain = (Button)findViewById(R.id.btnAgain);
		Button btnFinish = (Button)findViewById(R.id.btnFinish);
		
		btnAgain.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setContentView(minhaView);
				minhaView.init();
			}
		});
		
		btnFinish.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	
}
