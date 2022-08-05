package es.uned.master.java.kwic.logica.modelo;
import es.uned.master.java.kwic.controlador.KwicFormArea;

@SuppressWarnings("serial")
public class KwicException extends RuntimeException{
    public KwicException(){
            super("Kwic Excepcion:\n");
    }
    public KwicException(String str){
            super("Kwic Excepcion:\n"+str);
    }

}
