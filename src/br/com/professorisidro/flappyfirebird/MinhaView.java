package br.com.professorisidro.flappyfirebird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MinhaView extends View implements Runnable {

	private boolean running;
	// private int i;
	// private Paint paint;
	private Thread thread;
	private Bird bird;
	private InfiniteBackground bg;
	private boolean update;
	private Combo combo;	
	private static final String TAG="MinhaView";
	
	private Handler handler;
	
	private boolean colidiuGap=false;	
	private Paint paint;
	

	public MinhaView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
		

	}

	public void init() {
		// TODO Auto-generated method stub
		
		GameParameterSingleton.PONTOS = 0;	
		running = true;
		update = true;
		bg = new InfiniteBackground();
		GameParameterSingleton.DISTORTION = (float) GameParameterSingleton.SCREEN_HEIGHT
				/ bg.getHeight();
		
		
		Log.d(TAG,"Distortion = "+GameParameterSingleton.DISTORTION);
		bg.updateDistortion();
		
		bird = new Bird();
		Log.d("TAG","Bird = "+bird.getWidth()+","+bird.getHeight());
	
	
		combo = new Combo();

		bird.setX(50);
		bird.setY(50);
		
		
		bird.updateDistortion();
		Log.d("TAG","BirdD = "+bird.getWidth()+","+bird.getHeight());

		// ajustando bounding box do bird;
		bird.getBoundingBox().setWidth((int)(bird.getWidth()*0.10f));
		bird.getBoundingBox().setHeight((int)(bird.getHeight()*0.10));
		bird.getBoundingBox().setX(bird.getX() + (int)((bird.getWidth() - bird.getBoundingBox().getWidth())*.10f));
		bird.getBoundingBox().setY(bird.getY() + (int)((bird.getHeight() - bird.getBoundingBox().getHeight())*0.10f));
		
		
		Log.d(TAG,bird.getBoundingBox().toString());
		
		Log.d(TAG,"SCREEN "+GameParameterSingleton.SCREEN_HEIGHT+","+GameParameterSingleton.SCREEN_WIDTH);
		
		
		
		paint = new Paint();
		paint.setColor(Color.WHITE);
		
		//thread = new Thread();
		//thread.start();

	}

	protected void onDraw(Canvas canvas) {
		bg.draw(canvas);
		combo.draw(canvas);
		bird.draw(canvas);
		
		canvas.drawText("Pontos = "+GameParameterSingleton.PONTOS, GameParameterSingleton.SCREEN_WIDTH-100, 50, paint);
		
		
	}

	public void run() {

		while (running) {
			this.postInvalidate();
			try {
				update();
				Thread.sleep(50);
			} catch (Exception e) {

			}
		}
	}

	public boolean onTouchEvent(MotionEvent evt) {
		if (!running) {
			running = true;
			thread.start();
			return true;
		}
		if (evt.getAction() == MotionEvent.ACTION_DOWN) {
			bird.setDirecao(Bird.SOBE);
			return true;
		}
		if (evt.getAction() == MotionEvent.ACTION_UP) {
			bird.setDirecao(Bird.DESCE);
			return true;

		}

		return false;
	}

	public void update() {
		if (update) {
			bird.update();
			bg.update();
			combo.update();
			
			if (bird.getBoundingBox().getY() <= 0) {
				notifyFinish();
			}
			else if (bird.getBoundingBox().getY() + bird.getBoundingBox().getHeight() >= GameParameterSingleton.SCREEN_HEIGHT) {
				notifyFinish();
			}
			
			else if (GameParameterSingleton.detectColision(bird, combo.getObstacleUp())){
				notifyFinish();
			}
			
			else if (GameParameterSingleton.detectColision(bird, combo.getObstacleDown())){
				notifyFinish();
			}
			
			if (!colidiuGap){
				if (GameParameterSingleton.detectColision(bird, combo.getGap())){
					colidiuGap = true;
				}
			}
			else{
				if (!GameParameterSingleton.detectColision(bird, combo.getGap())){
					colidiuGap = false;
					GameParameterSingleton.PONTOS++;
					Log.d(TAG,"Pontos = "+GameParameterSingleton.PONTOS);
				}
			}
			
			
						
			//regra para ficar criando cada vez mais obstaculos
			if (combo.getGap().getX()+combo.getGap().getWidth() <= 0){
				combo = new Combo();
			}			
		}
	}
		
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
    
	public void notifyFinish(){
		update = false;		
		Message msg = new Message();
		msg.what = 100;  // tipo, acabou aquele jogo
		handler.sendMessage(msg);
	}


	
	
	
}
