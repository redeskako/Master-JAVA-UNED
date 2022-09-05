package Supension_Reanudacion_Finalizacion_Hilos;
/**
 * Todos los programas tienen un unico hilo que cuando finaliza el programa termina
 * Con java existe al posibilidad de crear hilos adicionales
 * Este programa muestra como crear dos hilos hijos, c�mo sincronizarlos
 * 
 * Un hilo ha de dise�arse de manera que el metodo run() comprueba periodicamente
 * si el hilo ha de suspender, reanudar o detener su propia ejecuci�n
 * Normalmente esto se realiza estableciendo una variable de marca que indique el 
 * estado de ejecuci�n del hilo. mientras esta variable tenga asignado el valor runnnig,
 * el m�todo run() deber� continuar dejando que el hilo se ejecute.
 * 
 * Si se asigna a esta variable el valor suspend, el hilo ha de parar; y si se le asigna 
 * el valor stop, el hilo ha de terminar. 
 * 
 * El siguiente ejemplo muestra como los metodos wait(), y modify() heredados de Object, 
 * pueden utilizarse para controlar la ejecuci�n de un hilo. 
 * 
 * La clase NewThread contiene una variable de instancia booleana llamada suspendFlag, 
 * que se utiliza para controlar la ejecuci�n del hilo. Se inicializa con el valor false
 * que le da el constructor. El metodo run() contiene un bloque de sentencia syschronized 
 * que examina suspedFlag. Si esta vairable tiene valor true, se llama al m�todo wait()
 * para suspender la ejecuci�n del hilo. El m�todo mysuspend() asigna a la variable 
 * suspendFlag el valor ture. El metodo myresume() asigna a la variable suspendFlag el valor 
 * false y llama a notify() para reactivar el hilo. En el m�todo main se llama a los metodos 
 * mysyspend() y myresume()
 * @author Paco
 *
 */
public class NewThread implements Runnable {
	String name; //Nombre del hilo
	Thread t;
	boolean suspendFlag;
	
	NewThread(String treadname){
		name = treadname;
		t = new Thread(this, name);
		System.out.println("New tread: " + t);
		suspendFlag = false;
		t.start(); //Comienzo del hilo
	}
	
	//Este es el punto de entrada del hilo.
	public void run(){
		
		try{
			for(int i=15; i > 0; i--){
				System.out.println(name + ": " + i);
				Thread.sleep(200);
				synchronized(this){
					while(suspendFlag){
						wait();
					}
				}
			}
		}catch (InterruptedException e){
			System.out.println(name + " interrupted");
		}
		System.out.println(name + " existing.");
	}
	
	void mysuspend(){
		suspendFlag = true;
	}
	
	synchronized void myresume(){
		suspendFlag = false;
		notify();
	}
}
