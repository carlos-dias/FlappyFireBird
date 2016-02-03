package br.com.professorisidro.flappyfirebird;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Bird extends GameCoiso{
	private static final String TAG="Bird";
	private Bitmap bitmap;
	private int    spriteColumn;
	private Rect   src;
	private Rect   dst;
	private int    imageWidth;
	private int    imageHeight;
	
	private int    direcao;
	public static final int SOBE=1;
	public static final int DESCE=0;
	
	public static final int STEP_SOBE  = 3;
	public static final int STEP_DESCE = 6;	
	
	private Paint paint;
	
	public Bird(){
		try{
			/* ler o arquivo de imagem */
			InputStream is = GameParameterSingleton.assetManager.open("bird.png");
			bitmap = BitmapFactory.decodeStream(is);
			
			/* ajustar parametros de altura e largura */
			imageWidth = bitmap.getWidth() / 10;
			imageHeight = bitmap.getHeight();
			src = new Rect(0,0,imageWidth,imageHeight);
			setWidth(imageWidth);
			setHeight(imageHeight);
			
			// ajustando o bounding box;
			spriteColumn = 0;
			dst = new Rect();
			
			paint = new Paint();
			paint.setColor(Color.BLACK);
			
			
		}
		catch(Exception e){
			Log.d(TAG,"Erro ao carregar Sprite");
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
				
		if (direcao == SOBE){
			setY(getY()- (int)(STEP_SOBE*GameParameterSingleton.DISTORTION));
			getBoundingBox().setY(getBoundingBox().getY()- (int)(STEP_SOBE*GameParameterSingleton.DISTORTION));
		}
		else{
			setY(getY() + (int)(STEP_DESCE*GameParameterSingleton.DISTORTION));
		    getBoundingBox().setY(getBoundingBox().getY()+ (int)(STEP_DESCE*GameParameterSingleton.DISTORTION));
		}				
		src.left = spriteColumn*imageWidth;
		src.right = src.left + imageWidth;
		
		spriteColumn = (spriteColumn+1)%10;
		
		dst.left   = super.getX();
		dst.top    = super.getY();
		dst.right  = dst.left + super.getWidth();
		dst.bottom = dst.top + super.getHeight();
	}

	public int getDirecao() {
		return direcao;
	}

	public void setDirecao(int direcao) {
		this.direcao = direcao;
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
				
		/*canvas.drawRect(getBoundingBox().getX(),
				        getBoundingBox().getY(),
				        getBoundingBox().getX() + getBoundingBox().getWidth(),
				        getBoundingBox().getY() + getBoundingBox().getHeight(),paint);*/
		canvas.drawBitmap(bitmap, src, dst, null);
		//Log.d(TAG,getBoundingBox().toString());
	}

}
