package es.rizos.librerias;

public class ErrorRizosBd extends RuntimeException{
		public ErrorRizosBd(){
			super("ERROR BD");
		}
		public ErrorRizosBd(String str){
			super("ERROR BD"+str);
		}
	}
