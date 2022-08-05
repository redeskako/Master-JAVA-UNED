package antonio.j2se.practica4bbdd.operaciones;

import java.util.HashMap;

/**
 * Enumeracion que representa las posibles operaciones de BBDD que se pueden realizar
 * Maneja un HashMap donde se mapea la operacion con su sentencia SQL
 * @author  Antonio Sánchez Antón
 * @since  1.0
 */
public enum OperacionesBBDD {
	     ALLPAISES,ALLPAISESCOUNT,ALLINDICADORES,ALLINDICADORESCOUNT,INDICADORESBYPAIS,INDICADORESBYPAISCOUNT,PAISESBYINDICADOR,PAISESBYINDICADORCOUNT,ESTADISTICASBYINDICADORPAIS;
	     protected static HashMap<OperacionesBBDD, String> queryOperacion;
    
	 static{
    	 queryOperacion=new HashMap<OperacionesBBDD, String>();
    	 queryOperacion.put(ALLPAISES, "SELECT * FROM PAIS ORDER BY COUNTRY_CODE LIMIT ? OFFSET ?");
    	 queryOperacion.put(ALLPAISESCOUNT, "SELECT COUNT(1) AS CONTADOR FROM PAIS");
    	 queryOperacion.put(ALLINDICADORES, "SELECT * FROM INDICADOR ORDER BY INDICATOR_CODE LIMIT ? OFFSET ?");
    	 queryOperacion.put(ALLINDICADORESCOUNT, "SELECT COUNT(1) AS CONTADOR FROM INDICADOR");
    	 queryOperacion.put(INDICADORESBYPAIS, "SELECT PAIS.*,INDICADOR.* FROM PAIS,INDICADORPAIS,INDICADOR WHERE PAIS.COUNTRY_CODE=INDICADORPAIS.COUNTRY_CODE AND INDICADORPAIS.INDICATOR_CODE=INDICADOR.INDICATOR_CODE AND PAIS.COUNTRY_CODE=? ORDER BY PAIS.COUNTRY_CODE,INDICADOR.INDICATOR_CODE LIMIT ? OFFSET ?");
    	 queryOperacion.put(INDICADORESBYPAISCOUNT, "SELECT COUNT(1) AS CONTADOR FROM PAIS,INDICADORPAIS,INDICADOR WHERE PAIS.COUNTRY_CODE=INDICADORPAIS.COUNTRY_CODE AND INDICADORPAIS.INDICATOR_CODE=INDICADOR.INDICATOR_CODE AND PAIS.COUNTRY_CODE=?");
    	 queryOperacion.put(PAISESBYINDICADOR, "SELECT PAIS.*,INDICADOR.* FROM PAIS,INDICADORPAIS,INDICADOR WHERE PAIS.COUNTRY_CODE=INDICADORPAIS.COUNTRY_CODE AND INDICADORPAIS.INDICATOR_CODE=INDICADOR.INDICATOR_CODE AND INDICADOR.INDICATOR_CODE=? ORDER BY PAIS.COUNTRY_CODE,INDICADOR.INDICATOR_CODE LIMIT ? OFFSET ?");
    	 queryOperacion.put(PAISESBYINDICADORCOUNT, "SELECT COUNT(1) AS CONTADOR FROM PAIS,INDICADORPAIS,INDICADOR WHERE PAIS.COUNTRY_CODE=INDICADORPAIS.COUNTRY_CODE AND INDICADORPAIS.INDICATOR_CODE=INDICADOR.INDICATOR_CODE AND INDICADOR.INDICATOR_CODE=?");
    	 queryOperacion.put(ESTADISTICASBYINDICADORPAIS, "SELECT * FROM ESTADISTICA WHERE INDICATOR_CODE=? AND COUNTRY_CODE=? ORDER BY YEAR");
     }
     /**
      * Metodo que devuelve la operacion asociada a un numero
      * @param numero
      * @return OperacionesBBDD asociado
      */
     public static OperacionesBBDD getOperacion(int numero){
    	 OperacionesBBDD retorno=null; 
    	switch (numero) {
		case 0:
			retorno=OperacionesBBDD.ALLPAISES;
			break;
    	case 1:
			retorno=OperacionesBBDD.ALLINDICADORES;
			break;
    	case 2:
			retorno=OperacionesBBDD.INDICADORESBYPAIS;
			break;
    	case 3:
			retorno=OperacionesBBDD.PAISESBYINDICADOR;
			break;
    	case 4:
			retorno=OperacionesBBDD.ESTADISTICASBYINDICADORPAIS;
			break;
    	case 5:
			retorno=OperacionesBBDD.ALLPAISESCOUNT;
			break;
    	case 6:
			retorno=OperacionesBBDD.ALLINDICADORESCOUNT;
			break;
    	case 7:
			retorno=OperacionesBBDD.INDICADORESBYPAISCOUNT;
			break;
    	case 8:
			retorno=OperacionesBBDD.PAISESBYINDICADORCOUNT;
			break;

     	}
      	return retorno;
     }
     

     /**
      * Metodo que devuelve el numero asociado a una operacion
      * @param operacion
      * @return numero
      */

     public static int getOperacion(OperacionesBBDD operacion){
    	 int retorno=-1; 
    	switch (operacion) {
		case ALLPAISES:
			retorno=0;
			break;
		case ALLINDICADORES:
			retorno=1;
			break;
		case INDICADORESBYPAIS:
			retorno=2;
			break;			
		case PAISESBYINDICADOR:
			retorno=3;
			break;			
		case ESTADISTICASBYINDICADORPAIS:
			retorno=4;
			break;			
		case ALLPAISESCOUNT:
			retorno=5;
			break;			
		case ALLINDICADORESCOUNT:
			retorno=6;
			break;			
		case INDICADORESBYPAISCOUNT:
			retorno=7;
			break;			
		case PAISESBYINDICADORCOUNT:
			retorno=8;
			break;			

		}
    	return retorno;
     }
     

     /**
      * Metodo que devuelve el SQL a lanzar
      * @param operacion
      * @return sentencia
      */
     public static String getSQLByOperacion(OperacionesBBDD operacion){
    	String retorno=null; 
		retorno= queryOperacion.get(operacion);
    	return retorno;
     }


}
