package org.coordenadas;

public class Pixel extends Par{
	private int color;
	
	public Pixel(int color, int x, int y){
		super(x,y);
		this.color=color;
	}
	public Pixel(int color, Par p){
		super(p.x(),p.y());
		this.color=color;		
	}
	public Pixel(int color, int x){
		super(x);
		this.color=color;		
	}
	public String toString(){
		return "[" +this.color+"; Par"+super.toString()+"]";
	}
}
