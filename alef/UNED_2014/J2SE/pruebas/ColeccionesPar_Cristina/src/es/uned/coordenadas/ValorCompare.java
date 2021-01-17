package es.uned.coordenadas;

public enum ValorCompare {

	MAYOR(1), IGUAL(0), MENOR(-1);
	int valorInt;
	
	ValorCompare(int i){
		valorInt = i;
	}
}
