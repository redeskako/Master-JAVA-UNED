package es.uned.master.java;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Alquiler {
    private LocalDate inicio = LocalDate.now();
    private LocalDate fin;
    private Categoria categoria;

    public LocalDate getFechaInicio() {
        return this.inicio;
    }

    public Alquiler(Categoria categoria) {
        this.categoria = categoria;
    }

    public void Finalizar() {
        this.fin = LocalDate.now();
    }

    public double getPrecioAlquiler() {
        return this.fin != null ? this.categoria.getPrecioAlquilerDiario() * (double)(ChronoUnit.DAYS.between(this.inicio, this.fin) + 1L) : 0.0;
    }
}
