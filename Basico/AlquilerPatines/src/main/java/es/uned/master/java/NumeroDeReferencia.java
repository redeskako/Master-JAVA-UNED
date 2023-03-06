package es.uned.master.java;

import java.time.LocalDate;

public class NumeroDeReferencia {
    private static final int SECUENCIA_MAXIMA = 2;
    private static short ultimoAño = (short)LocalDate.now().getYear();
    private static int[] ultimaSecuencia = new int[Categoria.values().length];
    private final String codigo;

    public NumeroDeReferencia(Categoria categoria) throws Exception {
        LocalDate hoy = LocalDate.now();
        if (ultimaSecuencia[categoria.ordinal()] == 2) {
            throw new Exception("Se ha alcanzado el número máximo de referencias para la categoria " + categoria.toString());
        } else {
            this.codigo = String.format("%s%04d%05d", categoria.toString(), hoy.getYear(), ++ultimaSecuencia[categoria.ordinal()]);
        }
    }

    public String toString() {
        return this.codigo;
    }
}
