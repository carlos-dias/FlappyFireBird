package br.com.professorisidro.flappyfirebird;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class InfiniteBackground {
	private int x;
	private int y;
	private int width;	
	private int height;
	
	private Bitmap bitmap;
	private Rect   src;
	private Rect   first;
	private Rect   second;	
	private static final int STEP=5;
	
	private static final String TAG="InfiniteBackground";
	
	public InfiniteBackground(){
		try{
			InputStream is = GameParameterSingleton.assetManager.open("cenario1.png");
			bitmap = BitmapFactory.decodeStream(is);
			
			src    = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
			width  = bitmap.getWidth();
			height = bitmap.getHeight();
			
			first = new Rect(0,0,width,height);
			second = new Rect(width-1,0,width+width,height);
		}
		catch(Exception e){
			Log.d(TAG,"Erro ao carregar imagem");
		}
	}
	
	public void update(){
		
		int passoDistorcido = (int)(this.STEP * GameParameterSingleton.DISTORTION);		
		
		first.left   -= passoDistorcido;
		first.right  -= passoDistorcido;
		second.left  -= passoDistorcido;
		second.right -= passoDistorcido;
		
		if (first.right <= 0){
			first.left =  second.right;
			first.right = second.right + width;
		}
		
		if (second.right <= 0){
			second.left  = first.right;
			second.right = first.right + width;
		}
	} 
	public void draw(Canvas canvas){
		canvas.drawBitmap(bitmap, src, first, null);
		canvas.drawBitmap(bitmap, src, second, null);
		
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void updateDistortion(){
		width = (int)(width*GameParameterSingleton.DISTORTION);
		height = (int)(height*GameParameterSingleton.DISTORTION);
		first.right = first.left + width;
		first.bottom = first.top + height;
		second.right = second.left + width;
		second.bottom = second.top + height;
	}
}
