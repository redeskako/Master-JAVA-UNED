package es.uned.master.java.juegoAhorcado.base;


// import lombok.ToString;
import lombok.Getter;

@Getter
// @ToString(includeFieldNames = false)
public enum EstadoAhorcado {
	
    INICIAL(0,
        "+---+\n" +
        "  | |\n" +
        "    |\n" +
        "    |\n" +
        "    |\n" +
        "    |\n" +
        "=======\n"
    ),
    CABEZA(1,
        "+---+\n" +
        "  | |\n" +
        "  O |\n" +
        "    |\n" +
        "    |\n" +
        "    |\n" +
        "=======\n"
    ),
    CUERPO(2,
        "+---+\n" +
        "  | |\n" +
        "  O |\n" +
        "  | |\n" +
        "    |\n" +
        "    |\n" +
        "======\n"
    ),
    BRAZO_DERECHO(3,
        "+---+\n" +
        "  | |\n" +
        "  O |\n" +
        "  |\\|\n" +
        "    |\n" +
        "    |\n" +
        "======\n"
    ),
    BRAZO_IZQUIERDO(4,
        "+---+\n" +
        "  | |\n" +
        "  O |\n" +
        " /|\\|\n" +
        "    |\n" +
        "    |\n" +
        "======\n"
    ),
    PIERNA_DERECHA(5,
        "+---+\n" +
        "  | |\n" +
        "  O |\n" +
        " /|\\|\n" +
        " /  |\n" +
        "    |\n" +
        "======\n"
    ),
    PIERNA_IZQUIERDA(6,
        "+---+\n" +
        "  | |\n" +
        "  O |\n" +
        " /|\\|\n" +
        " / \\|\n" +
        "    |\n" +
        "======\n"
    );


    private int estado;
    
	private String figura;
    
    EstadoAhorcado(int estado, String figura) {
    	this.estado = estado;
    	this.figura = figura;
    }

/*
    public int getEstado() {
    	return this.estado;
    }
    
    public String getFigura() {
    	return this.figura;
    }
*/
    @Override
    public String toString() {
    	return "Estado: "+ this.estado + "\n" + this.figura;
    }
}
