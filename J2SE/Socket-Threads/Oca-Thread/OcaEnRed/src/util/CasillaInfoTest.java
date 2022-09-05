/**
* 2014, Antonio Jes�s Arquillos �lvarez
* M�ster en Dise�o y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educaci�n a Distancia
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