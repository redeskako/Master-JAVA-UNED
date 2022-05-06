package es.uned.master.java.kwic.pruebas;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import es.uned.master.java.kwic.logica.modelo.TituloKwic;

@RunWith(Parameterized.class)//Clase de prueba
public class TituloKwicTest {
	private String frase="Erase una vez un barquito chiquitito";//Frase original
	private String palabra;//Conjunto de palabras individuales a procesar
	private String resultado;//Resultado que se deberia obtener tras procesar las palabras

	
	private static TituloKwic prueba;
	
	public TituloKwicTest(String palabra, String resultado){//Constructor con los argumentos a comprobar
		this.palabra=palabra;
		this.resultado=resultado;
	}
	/*Creamos una bateria de datos*/
	@Parameters
	public static Collection<Object[]> datos(){//Metodo que devuelve una coleccion con todos los valores a comprobar
		return Arrays.asList(new Object[][]{
				{"Erase", "... una vez un barquito chiquitito "},
				{"una", "Erase ... vez un barquito chiquitito "},
				{"vez", "Erase una ... un barquito chiquitito "},
				{"un", "Erase una vez ... barquito chiquitito "},
				{"barquito", "Erase una vez un ... chiquitito "},
				{"chiquitito", "Erase una vez un barquito ... "}
		});
	}
	
	@Test
	public void testReemplaza(){	
		prueba = new TituloKwic(palabra);	
		assertEquals("No son iguales", resultado, prueba.reemplaza(frase));
	}

}
