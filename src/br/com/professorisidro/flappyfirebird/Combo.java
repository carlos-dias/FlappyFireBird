package br.com.professorisidro.flappyfirebird;

import android.graphics.Canvas;

public class Combo {
	
	private Obstacle obstacleUp;
	private Obstacle obstacleDown;
	private Gap gap;
	private boolean valid;
	
	public Combo(){
		valid = true;
		obstacleUp = new Obstacle(GameParameterSingleton.SCREEN_WIDTH, 0);
		int y = ((int)(Math.random()*1000))%(obstacleUp.getHeight());
		y = (y>0)?y*-1:y;	
		obstacleUp.setY(y);
		
		obstacleUp.updateDistortion();
		
		gap = new Gap(obstacleUp.getX(), obstacleUp.getY() + obstacleUp.getHeight(), 40, 55 );
		gap.updateDistortion();
		obstacleDown = new Obstacle(gap.getX(), gap.getY()+gap.getHeight());
		obstacleDown.updateDistortion();
		
		obstacleUp.getBoundingBox().setX(obstacleUp.getX());
		obstacleUp.getBoundingBox().setY(obstacleUp.getY());
		obstacleUp.getBoundingBox().setWidth(obstacleUp.getWidth());
		obstacleUp.getBoundingBox().setHeight(obstacleUp.getHeight());
		
		obstacleDown.getBoundingBox().setX(obstacleDown.getX());
		obstacleDown.getBoundingBox().setY(obstacleDown.getY());
		obstacleDown.getBoundingBox().setWidth(obstacleDown.getWidth());
		obstacleDown.getBoundingBox().setHeight(obstacleDown.getHeight());
		
		gap.getBoundingBox().setX(gap.getX());
		gap.getBoundingBox().setY(gap.getY());
		gap.getBoundingBox().setWidth(gap.getWidth());
		gap.getBoundingBox().setHeight(gap.getHeight());
		
	}
	
	public void update(){
		obstacleUp.update();
		obstacleDown.update();
		gap.update();
		
	}
	public void draw(Canvas canvas){
		obstacleUp.draw(canvas);
		gap.draw(canvas);
		obstacleDown.draw(canvas);
		
	}

	public Obstacle getObstacleUp() {
		return obstacleUp;
	}

	
	public Obstacle getObstacleDown() {
		return obstacleDown;
	}
	
	public Gap getGap() {
		return gap;
	}
	
}
