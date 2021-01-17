/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/


package util;

import java.util.List;

public class CasillaInfoTest {
	public static void main(String args[]) {
		CasillaInfoReader reader = new CasillaInfoReader();
		List< CasillaInfo > tableroOcaInfo = reader.leerCasillas( "TableroOca.xml" );
		for ( CasillaInfo casillaInfo : tableroOcaInfo ) {
			System.out.println( casillaInfo );
		}
	}
} 