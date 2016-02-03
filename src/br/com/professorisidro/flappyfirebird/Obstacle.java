package br.com.professorisidro.flappyfirebird;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Obstacle extends GameCoiso {
	private static final String TAG="Obstacle";	
	private Bitmap figura;
	private Rect   src;
	private Rect   dst;
	private int    spriteColumn;
	
	private int    spriteWidth;
	private int    spriteHeigth;
	
	private Paint paint;
	
	private static final int STEP=5;
	
	public Obstacle(int x, int y){
		try{
			paint = new Paint();
			paint.setColor(Color.BLACK);
			InputStream is = GameParameterSingleton.assetManager.open("obstaculo2.png");
			figura = BitmapFactory.decodeStream(is);
			setX(x);
			setY(y);
			spriteWidth = figura.getWidth()/4;
			spriteHeigth = figura.getHeight();
			setWidth(spriteWidth);
			setHeight(spriteHeigth);
			src = new Rect(0,0,getWidth(), getHeight());
			dst = new Rect();
		}
		catch(Exception e){
			Log.d(TAG,"Error on loading Image");
		}
	}
	
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		int passoDistorcido = (int)(this.STEP * GameParameterSingleton.DISTORTION);
		setX(getX()-passoDistorcido);
		getBoundingBox().setX(getX() - passoDistorcido);
		
		src.left = spriteColumn*spriteWidth;
		src.right = src.left + spriteWidth;
		src.top = 0;
		src.bottom = spriteHeigth;
		
		dst.left = getX();
		dst.right = dst.left + getWidth();
		dst.top = getY();
		dst.bottom = getY() + getHeight();
		
		spriteColumn = (spriteColumn+1)%4;
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(figura,  src,  dst, null);
		/*canvas.drawRect(getBoundingBox().getX(), 
				        getBoundingBox().getY(),  
				        getBoundingBox().getX()+getBoundingBox().getWidth(), 
				        getBoundingBox().getY()+ getBoundingBox().getHeight(),
				        paint);*/
		
	}

}
