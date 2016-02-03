package br.com.professorisidro.flappyfirebird;

import android.graphics.Canvas;

public abstract class GameCoiso {
	private int x;
	private int y;
	private int width;
	private int height;
	
	private BoundingBox boundingBox;

	public GameCoiso() {
		super();
		boundingBox = new BoundingBox();
	}
	
	public GameCoiso(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		boundingBox.setX(x);
		boundingBox.setY(y);
		boundingBox.setWidth(width);
		boundingBox.setHeight(height);
		
		
	}	
	
	public abstract void update();
	public abstract void draw(Canvas canvas);
	
	
	public void updateDistortion(){
		setHeight((int)(getHeight()* GameParameterSingleton.DISTORTION));
		setWidth((int)(getWidth() * GameParameterSingleton.DISTORTION));
		
        //boundingBox.setWidth((int)(boundingBox.getWidth()*GameParameterSingleton.DISTORTION));
        //boundingBox.setHeight((int)(boundingBox.getHeight()*GameParameterSingleton.DISTORTION));
	}
	
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;		
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;		
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
	
	
		

}
